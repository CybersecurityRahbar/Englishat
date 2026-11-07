# Englishat — Project Context

_Last updated: 2026-11-07_

## 1. Project identity and ownership

- Repository: https://github.com/CybersecurityRahbar/Englishat
- Default branch: main
- Product: Arabic-first Android application for learning English.
- This project is being developed as a personal app for the repository owner's friend. It is separate from the owner's advanced cybersecurity, OSINT, and monitoring projects; do not transfer assumptions, scope, or branding from those projects into Englishat.
- Product ambition: a polished, structured, useful English-learning experience inspired by leading language-learning products. Avoid shipping a thin WebView wrapper around the supplied HTML prototypes.

## 2. Supplied prototype review

The repository initially contained a minimal README and five HTML prototypes. All five were reviewed before the native app scaffold was added.

### english_learning_studio_v02.html
- General English-learning dashboard prototype with a dark blue/purple hero, progress summary, lesson cards, vocabulary dictionary, practice questions, and a final quiz.
- Demonstrates three starter topics: Verb to Be, Personal Pronouns, and A / An / The.
- Uses browser localStorage for completed lessons and latest score.
- Uses browser speechSynthesis for English pronunciation.
- Useful product ideas: clear learning journey, progress indicator, lesson cards, immediate quiz feedback.
- Limitations: only three demo lessons; progress is browser-local; no native Android architecture or robust content model.

### english_learning_studio_v03.html
- A detailed A1 / American English File 1A lesson prototype.
- Sections: overview, greetings/dialogue, grammar, pronunciation, vocabulary, practice, and an eight-question lesson quiz.
- Covers introductions, am / is / are, subject pronouns, six vowel examples/IPA cues, days of the week, and numbers 0–20.
- Useful ideas: coherent lesson flow, Arabic explanations, English examples, pronunciation support, and question-by-question practice.
- Content note: Arabic phonetic spellings are approximate and must not replace listening to a reliable English audio source.

### english_learning_studio_v04.html
- A smaller, more focused beginner prototype branded around a single A1 unit.
- Includes a home overview, five greeting expressions, a Verb to Be explanation, and a five-question translation game with score and replay.
- Useful ideas: simple beginner-friendly lesson structure and a game loop with instant correctness feedback.
- Limitations: tiny vocabulary list; most learning content is static; no persisted progress or vocabulary review.

### english_learning_studio_v05.html
- Topic-based vocabulary browser with 12 groups and approximately 200 word records.
- Topics include descriptions/adjectives, people/relationships, home/furniture, tourism/places, restaurant/food, education/work/society, technology, arts/music/entertainment, nature/weather/health, daily life/actions, problems/risks/feelings, and time/quantity/general concepts.
- Each record generally has an English term, Arabic meaning, approximate Arabic pronunciation, and example/translation.
- Useful ideas: topic navigation and contextual examples.
- Content QA required: validate translations, naturalness of example sentences, spelling, parts of speech, and pronunciation before importing this large vocabulary set.

### english_learning_studio_v06.html
- Vocabulary browser organized into four curriculum groups: Unit 8, Unit 9, Unit 10, and Units 11–12.
- Contains approximately 111 vocabulary records.
- Records include word, part of speech, Arabic meaning, Arabic pronunciation approximation, word family, example, and example translation.
- Useful ideas: unit-based vocabulary, word families, and grammatical labels.
- Content QA required: several examples/translations need proofreading and context checks before broad release.

## 3. Product principles and learning approach

1. Native Android UI first; do not place the HTML prototypes inside a WebView as the product.
2. Arabic is the instructional language and the app UI is RTL. English examples, vocabulary, and sentences should remain visually LTR.
3. Organize content into a progression of CEFR-aligned levels (Pre-A1/A1/A2/B1 as content is produced). Do not label a learner's level as formally assessed until a diagnostic assessment exists.
4. Integrate the four core skills over time: reading, writing, listening, and speaking.
5. Each lesson should have a measurable objective, concise explanation, contextual examples, audio/listening, guided practice, and a mastery check.
6. Use spaced vocabulary review and retrieval practice rather than only displaying long word lists.
7. Reward progress without making points/streaks more important than learning.
8. Make core content work offline. Network features should be optional and not block lessons or saved progress.
9. Keep learner progress, favorites, and settings on-device by default until a genuine account/sync requirement is agreed.
10. Use original or appropriately licensed learning content. The prototypes mention American English File; do not copy full copyrighted textbook passages, recordings, or answer keys into the app unless the project owner has the rights or permission.

## 4. Research notes (reviewed 2026-11-07)

- Duolingo product pages emphasize short lessons, practice across reading/writing/listening/speaking, personalized progression, and habit-building features: https://www.duolingo.com/learn
- Busuu describes short focused lessons around everyday topics and practice/corrections; its Conversations feature adds scenario-based speaking practice tied to CEFR levels: https://help.busuu.com/hc/en-us/articles/15936615354641-What-is-Busuu and https://help.busuu.com/hc/en-us/articles/21862192336402-What-are-Busuu-Conversations-and-how-can-they-help-me-learn-a-language
- British Council LearnEnglish apps provide focused experiences for grammar, videos/listening, pronunciation, vocabulary games, and podcasts with transcripts/exercises: https://yemen.britishcouncil.org/en/english/apps
- Council of Europe CEFR descriptors provide a basis for measurable can-do learning objectives and expanded descriptors for listening, reading, phonological competence, mediation, and interaction: https://www.coe.int/en/web/common-european-framework-reference-languages/cefr-descriptors
- Android official offline-first guidance recommends showing local data immediately and keeping core functionality usable without reliable connectivity: https://developer.android.com/topic/architecture/data-layer/offline-first
- Android architecture recommendations favor a clear data layer/repository, UI driven by state, and Jetpack Compose for modern UI: https://developer.android.com/topic/architecture/recommendations
- Compose BOM setup and compiler configuration reviewed: https://developer.android.com/develop/ui/compose/setup-compose-dependencies-and-compiler
- AGP 9.1.1 compatibility notes specify Gradle 9.3.1 and JDK 17: https://developer.android.com/build/releases/agp-9-1-0-release-notes
- DataStore stable release documentation reviewed: https://developer.android.com/jetpack/androidx/releases/datastore

## 5. Initial native implementation committed

- Kotlin Android project initialized using Jetpack Compose and Material 3.
- Main navigation: Home, Learn, Practice, Vocabulary, Profile.
- First A1 lesson path includes greetings/introduction, Verb to Be, personal pronouns, and A/An/The.
- Vocabulary browser has search, topic filters, favorites, example translations, and Android TextToSpeech playback.
- Added an eight-question practice session with immediate feedback.
- Added Preferences DataStore persistence for lesson completion, XP, study streak, and favorite words.
- Added GitHub Actions workflow for building and uploading a debug APK.
- Original HTML prototypes are preserved unchanged as references.

## 6. Current limitations and next priorities

- This is the first native foundation, not a finished app or a claim of parity with major commercial products.
- The prototype's full approximately 200 topic vocabulary records and approximately 111 unit vocabulary records have not yet been imported; first pass includes a curated subset while content structure and UX are established.
- No recorded native audio assets, speech-recognition assessment, pronunciation scoring, listening passages, writing correction, placement test, user accounts, cloud sync, or content management backend yet.
- Browser speech synthesis has been replaced with Android TextToSpeech. Voice availability and quality depend on the device's installed English voice data.
- Textbook-derived content needs ownership/licensing review and editorial QA.
- The first GitHub Actions run (2026-11-07, run 42373948824) stopped in the Android SDK setup action before Gradle ran: that action tried to install the obsolete SDK package `tools`, which is no longer published. The workflow was changed to request only `platform-tools` from the setup action; the next run must confirm the Android SDK install and actual Kotlin/Gradle compilation.
- This first CI failure was an environment setup problem, not evidence of a Kotlin compilation error; the build task was skipped, so app compilation remains unverified.
- Add automated unit/UI tests and accessibility checks.
- Expand lessons and CEFR can-do objectives; add spaced-repetition scheduling for vocabulary; add listening and speaking practice.
- Design later A2/B1 content only after A1 content is reviewed and assessed.

## 7. Decisions and workflow

- Work directly on main unless explicitly instructed otherwise.
- Preserve the five original HTML prototypes; treat them as references, not production UI.
- Keep this file cumulative: append important decisions, implementation changes, test results, discovered issues, and next steps after each project work session.
- Be precise about build/test status. A workflow file being added does not mean the APK has been successfully built; report success only after checking the actual run.


## 8. CI validation follow-up — 2026-11-07

- Inspected the first push-triggered Actions run (ID 42373948824). Checkout, JDK 17 setup, and Gradle 9.3.1 setup succeeded.
- The run failed inside `android-actions/setup-android@v3` because its default package list included the retired SDK package `tools`; `sdkmanager` returned “Failed to find package 'tools'”. The explicit SDK install and Gradle build steps were skipped.
- Updated the workflow input to `packages: platform-tools` so the setup action no longer requests the obsolete package. The next workflow run is required to verify SDK installation and the actual APK compilation.
- Do not claim that an APK is available until the workflow finishes successfully and the uploaded artifact is confirmed.
