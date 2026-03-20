## Android Studio (MAD Lab) Client

This folder contains a lightweight Android Studio starter you can drop into a **Mobile Application Development (MAD) lab** assignment. It calls the Flask ML backend (`/api/ask`) added in this repo and displays the top 3 answers.

### How to use
1. In Android Studio, create a new **Empty Activity** project named `AIChatClient` (Kotlin, minSdk 24+ so the included OkHttp 4.x dependency works without desugaring). If the wizard uses a lower API level, set `minSdkVersion 24` (`minSdk = 24` for Kotlin DSL) in `app/build.gradle`.
2. Replace the generated files with the snippets in this folder:
   - `app/src/main/java/com/example/aichatclient/MainActivity.kt`
   - `app/src/main/res/layout/activity_main.xml`
   - `app/src/main/AndroidManifest.xml`
3. Add these dependencies to `app/build.gradle`:
   ```gradle
   implementation "androidx.appcompat:appcompat:1.6.1"
   implementation "com.google.android.material:material:1.11.0"
   implementation "com.squareup.okhttp3:okhttp:4.12.0"
   ```
4. Run the Flask backend locally:
   ```bash
   pip install -r requirements.txt
   python app.py  # starts on http://localhost:5000
   ```
5. Update `baseUrl` in `MainActivity.kt`:
   - Emulator: `http://10.0.2.2:5000/api/ask`
   - Physical device on same Wi-Fi: `http://<your-lan-ip>:5000/api/ask`
6. Build and run the app. Type a question, tap **Ask**, and the ML backend will return the top 3 answers with cosine similarity scores.

### Files provided
- `MainActivity.kt` – Kotlin code that posts to `/api/ask` and renders the answers.
- `activity_main.xml` – Simple UI with an input box, button, and results area.
- `AndroidManifest.xml` – Includes the required Internet permission.

This keeps the ML backend, MAD lab Android client, and Android Studio workflow together in one repo for quick college submissions.
