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

data class LearningProgress(
    val completedLessons: Set<String> = emptySet(),
    val favorites: Set<String> = emptySet(),
    val xp: Int = 0,
    val streak: Int = 0
)

class ProgressStore(context: Context) {
    private val store: DataStore<Preferences> = context.applicationContext.englishatDataStore
    private val completedKey = stringSetPreferencesKey("completed_lessons")
    private val favoritesKey = stringSetPreferencesKey("favorite_words")
    private val xpKey = intPreferencesKey("xp")
    private val streakKey = intPreferencesKey("streak")
    private val lastStudyDayKey = longPreferencesKey("last_study_day")

    val progress: Flow<LearningProgress> = store.data
        .catch { error ->
            if (error is IOException) emit(emptyPreferences()) else throw error
        }
        .map { prefs ->
            LearningProgress(
                completedLessons = prefs[completedKey] ?: emptySet(),
                favorites = prefs[favoritesKey] ?: emptySet(),
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

    private fun updateStudyStreak(prefs: androidx.datastore.preferences.core.MutablePreferences) {
        val today = LocalDate.now().toEpochDay()
        val previous = prefs[lastStudyDayKey] ?: -1L
        val currentStreak = prefs[streakKey] ?: 0
        if (previous != today) {
            prefs[streakKey] = if (previous == today - 1) currentStreak + 1 else 1
            prefs[lastStudyDayKey] = today
        }
    }
}
