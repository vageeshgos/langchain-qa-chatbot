# 🤖 LangChain QA Chatbot

A Q&A Chatbot built with **LangChain**, **RAG (Retrieval-Augmented Generation)** pipeline, and **LLM integration**.

---

## 📌 What is this project?

This chatbot takes documents as input, stores them as embeddings in a vector database, and lets you ask natural language questions. It retrieves the most relevant context and generates accurate answers using a large language model.

---

## 🗂️ Project Structure

| Folder / File | Description |
|---|---|
| `ai-knowledge-assistant/` | Knowledge base assistant module |
| `ai-support-ticket-system/` | Support ticket AI module |
| `gyani-voice-assistant/` | Voice assistant integration |
| `android-sample/` | Android Studio (MAD lab) starter client that calls the ML backend |
| `templates/` | HTML templates for the UI |
| `app.py` | Main application entry point |
| `requirements.txt` | Python dependencies |
| `style.css` | Styling for the web interface |

---

## 🛠️ Tech Stack

- Python
- LangChain
- RAG Pipeline
- HuggingFace / OpenAI LLM
- Streamlit
- FAISS / ChromaDB (Vector Store)

---

## 🚀 How to Run

```bash
pip install -r requirements.txt
streamlit run app.py
# or
python app.py  # Flask server with / and /api/ask
```

### API (for Android / external clients)
```bash
curl -X POST http://localhost:5000/api/ask \
  -H "Content-Type: application/json" \
  -d '{"query":"What is machine learning?"}'
```
Returns top 3 answers with similarity scores.

---

## 🎓 College assignment bundle (ML + MAD Lab + Android Studio)

- **ML backend**: `app.py` exposes `/api/ask` (JSON) using sentence-transformer embeddings and cosine similarity.
- **MAD lab / Android Studio client**: See `android-sample/` for Kotlin code, layout, and manifest. Point `baseUrl` to your running Flask host (use `10.0.2.2` on emulator).
- **Quick flow**:
  1. `pip install -r requirements.txt && python app.py`
  2. Open `android-sample/README.md` and paste the provided files into a new Android Studio project.
  3. Run the Android app, ask a question, and see ML results streamed back from the Flask backend.

---

## 👤 Author

**Vageesh Goswami** — AI/ML Developer from Jaipur, India
[LinkedIn](https://www.linkedin.com/in/vageesh-goswami/) | [Portfolio](https://vageesh-goswami.netlify.app)
