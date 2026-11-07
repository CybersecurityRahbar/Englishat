# Englishat

Englishat is an Arabic-first Android app for learning English through short lessons, bilingual explanations, vocabulary review, pronunciation playback, and interactive practice.

## Current implementation

- Native Android app built with Kotlin and Jetpack Compose.
- Arabic right-to-left interface with English examples kept readable.
- A1 starter path: greetings, Verb to Be, personal pronouns, and articles.
- Searchable vocabulary cards with categories, favorites, Arabic meanings, and example sentences.
- Device text-to-speech for English words and examples.
- Interactive eight-question practice session with immediate feedback.
- Local progress, completed lessons, XP, streaks, and favorite words stored with Preferences DataStore.
- GitHub Actions builds a debug APK and uploads it as an artifact.

## Build

Open the repository in a recent Android Studio installation with JDK 17 and Android SDK Platform 36 installed. The project uses Android Gradle Plugin 9.1.1, Gradle 9.3.1, Kotlin 2.2.10, and Compose BOM 2026.09.00.

The GitHub Actions workflow installs Gradle directly and runs:

    gradle --no-daemon :app:assembleDebug

The APK is published as the englishat-debug-apk workflow artifact.

## Prototype references

The five original HTML prototypes (english_learning_studio_v02.html through english_learning_studio_v06.html) are intentionally retained as product and content references. They are not used as WebView screens in the native application.
