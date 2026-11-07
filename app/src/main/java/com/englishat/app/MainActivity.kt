package com.englishat.app

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.englishat.app.data.LearningProgress
import com.englishat.app.data.ProgressStore
import com.englishat.app.data.ReviewRating
import com.englishat.app.ui.EnglishatApp
import com.englishat.app.ui.theme.EnglishatTheme
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var progressStore: ProgressStore
    private var textToSpeech: TextToSpeech? = null
    private var speechReady = false
    private var pendingSpeech: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        progressStore = ProgressStore(applicationContext)

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech?.setLanguage(Locale.US)
                speechReady = result != TextToSpeech.LANG_MISSING_DATA &&
                    result != TextToSpeech.LANG_NOT_SUPPORTED
                if (speechReady) {
                    pendingSpeech?.let { speak(it) }
                    pendingSpeech = null
                }
            }
        }

        setContent {
            EnglishatTheme {
                val progress by progressStore.progress.collectAsState(initial = LearningProgress())
                val scope = rememberCoroutineScope()
                EnglishatApp(
                    progress = progress,
                    onSpeak = ::speak,
                    onCompleteLesson = { id -> scope.launch { progressStore.completeLesson(id) } },
                    onToggleFavorite = { word -> scope.launch { progressStore.toggleFavorite(word) } },
                    onPracticeCompleted = { correct -> scope.launch { progressStore.recordPractice(correct) } },
                    onReviewWord = { word, rating -> scope.launch { progressStore.recordReview(word, rating) } }
                )
            }
        }
    }

    private fun speak(text: String) {
        if (!speechReady) {
            pendingSpeech = text
            return
        }
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "englishat-" + System.currentTimeMillis())
    }

    override fun onDestroy() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
        super.onDestroy()
    }
}
