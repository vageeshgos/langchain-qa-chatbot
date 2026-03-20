package com.example.aichatclient

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // For local development; use HTTPS endpoints for production deployments.
    private val baseUrl = "http://10.0.2.2:5000/api/ask" // Android emulator loopback to host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queryInput = findViewById<TextInputEditText>(R.id.queryInput)
        val askButton = findViewById<Button>(R.id.askButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        askButton.setOnClickListener {
            val query = queryInput.text.toString().trim()
            if (query.isEmpty()) {
                resultText.text = "Please enter a question."
            } else {
                resultText.text = "Loading..."
                sendQuery(query) { result -> runOnUiThread { resultText.text = result } }
            }
        }
    }

    private fun sendQuery(query: String, onResult: (String) -> Unit) {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val payload = JSONObject().put("query", query)
        val body = payload.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(baseUrl)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onResult("Error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        onResult("Server error: ${it.code}")
                        return
                    }

                    val bodyString = it.body?.string().orEmpty()
                    val json = JSONObject(bodyString)
                    val resultsArray = json.optJSONArray("results")

                    if (resultsArray == null || resultsArray.length() == 0) {
                        onResult("No answers returned.")
                        return
                    }

                    val builder = StringBuilder()
                    for (i in 0 until resultsArray.length()) {
                        val item = resultsArray.getJSONObject(i)
                        builder.append("• ")
                            .append(item.optString("answer"))
                            .append(" (score: ")
                            .append(String.format("%.4f", item.optDouble("score")))
                            .append(")")
                            .append("\n\n")
                    }
                    onResult(builder.toString().trim())
                }
            }
        })
    }

    companion object {
        private val client: OkHttpClient by lazy { OkHttpClient() }
    }
}
