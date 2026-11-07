package com.englishat.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.LocalDate

private val Context.englishatDataStore by preferencesDataStore(name = "englishat_progress")

data class ReviewCardState(val stage: Int, val dueEpochDay: Long)
enum class ReviewRating { AGAIN, GOOD, EASY }

data class LearningProgress(
    val completedLessons: Set<String> = emptySet(),
    val favorites: Set<String> = emptySet(),
    val reviewCards: Map<String, ReviewCardState> = emptyMap(),
    val xp: Int = 0,
    val streak: Int = 0
)

class ProgressStore(context: Context) {
    private val store: DataStore<Preferences> = context.applicationContext.englishatDataStore
    private val completedKey = stringSetPreferencesKey("completed_lessons")
    private val favoritesKey = stringSetPreferencesKey("favorite_words")
    private val reviewCardsKey = stringSetPreferencesKey("review_cards")
    private val xpKey = intPreferencesKey("xp")
    private val streakKey = intPreferencesKey("streak")
    private val lastStudyDayKey = longPreferencesKey("last_study_day")

    val progress: Flow<LearningProgress> = store.data
        .catch { error -> if (error is IOException) emit(emptyPreferences()) else throw error }
        .map { prefs ->
            val cards = (prefs[reviewCardsKey] ?: emptySet()).mapNotNull { record ->
                val parts = record.split('|')
                val stage = parts.getOrNull(1)?.toIntOrNull()
                val dueDay = parts.getOrNull(2)?.toLongOrNull()
                if (parts.size == 3 && stage != null && dueDay != null) parts[0] to ReviewCardState(stage, dueDay) else null
            }.toMap()
            LearningProgress(
                completedLessons = prefs[completedKey] ?: emptySet(),
                favorites = prefs[favoritesKey] ?: emptySet(),
                reviewCards = cards,
                xp = prefs[xpKey] ?: 0,
                streak = prefs[streakKey] ?: 0
            )
        }

    suspend fun completeLesson(lessonId: String) {
        store.edit { prefs ->
            val completed = prefs[completedKey] ?: emptySet()
            if (lessonId !in completed) {
                prefs[completedKey] = completed + lessonId
                prefs[xpKey] = (prefs[xpKey] ?: 0) + 20
            }
            updateStudyStreak(prefs)
        }
    }

    suspend fun toggleFavorite(word: String) {
        store.edit { prefs ->
            val favorites = prefs[favoritesKey] ?: emptySet()
            prefs[favoritesKey] = if (word in favorites) favorites - word else favorites + word
        }
    }

    suspend fun recordPractice(correctAnswers: Int) {
        store.edit { prefs ->
            prefs[xpKey] = (prefs[xpKey] ?: 0) + (correctAnswers.coerceAtLeast(0) * 5)
            updateStudyStreak(prefs)
        }
    }

    suspend fun recordReview(word: String, rating: ReviewRating) {
        val today = LocalDate.now().toEpochDay()
        val goodIntervals = longArrayOf(1, 3, 7, 14, 30, 60)
        val easyIntervals = longArrayOf(3, 7, 14, 30, 60, 90)
        store.edit { prefs ->
            val records = prefs[reviewCardsKey] ?: emptySet()
            val previous = records.firstOrNull { it.substringBefore('|') == word }
            val stage = previous?.split('|')?.getOrNull(1)?.toIntOrNull()?.coerceIn(0, 5) ?: 0
            val nextStage = when (rating) {
                ReviewRating.AGAIN -> 0
                ReviewRating.GOOD, ReviewRating.EASY -> (stage + 1).coerceAtMost(5)
            }
            val interval = when (rating) {
                ReviewRating.AGAIN -> 0L
                ReviewRating.GOOD -> goodIntervals[stage]
                ReviewRating.EASY -> easyIntervals[stage]
            }
            val nextRecord = word + "|" + nextStage + "|" + (today + interval)
            prefs[reviewCardsKey] = records.filterNot { it.substringBefore('|') == word }.toSet() + nextRecord
            prefs[xpKey] = (prefs[xpKey] ?: 0) + when (rating) {
                ReviewRating.AGAIN -> 1
                ReviewRating.GOOD -> 3
                ReviewRating.EASY -> 5
            }
            updateStudyStreak(prefs)
        }
    }

    private fun updateStudyStreak(prefs: androidx.datastore.preferences.core.MutablePreferences) {
        val today = LocalDate.now().toEpochDay()
        val previous = prefs[lastStudyDayKey] ?: -1L
        val streak = prefs[streakKey] ?: 0
        if (previous != today) {
            prefs[streakKey] = if (previous == today - 1) streak + 1 else 1
            prefs[lastStudyDayKey] = today
        }
    }
}
