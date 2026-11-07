# Englishat

Englishat is an Arabic-first native Android app for learning English through structured lessons, contextual vocabulary, pronunciation playback, quick practice, and spaced vocabulary review.

## Current features

- Native Kotlin + Jetpack Compose UI with Arabic RTL navigation and readable English examples.
- Eight A1 starter lessons: greetings, Verb to Be, personal pronouns, articles, Present Simple, Have/Has, Question Words, and Prepositions of Place.
- Searchable bilingual dictionary with category filters, favorites, example sentences, and device Text-to-Speech.
- Interactive practice quiz with immediate explanations.
- Daily flashcard review with difficulty ratings and a locally persisted spaced-repetition schedule.
- On-device progress, completed lessons, favorites, XP, and study streaks with Preferences DataStore.
- Original Android adaptive launcher icon for Englishat.
- GitHub Actions builds a debug APK and uploads it as an artifact.

## Build

Open the repository in a recent Android Studio installation with JDK 17 and Android SDK Platform 36 installed. The project uses Android Gradle Plugin 9.1.1 with built-in Kotlin support, Gradle 9.3.1, Kotlin Compose compiler plugin 2.2.10, and Compose BOM 2026.06.01.

The GitHub Actions workflow installs Gradle and Android SDK packages, then runs:

    gradle --no-daemon :app:assembleDebug

The debug APK is uploaded as the `englishat-debug-apk` Actions artifact.

## Prototype references

The five original HTML prototypes (english_learning_studio_v02.html through english_learning_studio_v06.html) remain unchanged as product and content references. They are not used as WebView screens in the native application.
