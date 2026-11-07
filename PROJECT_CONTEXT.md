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
- The first GitHub Actions run (2026-11-07, run 42373948824) stopped during Android SDK setup because the action's default package list requested the retired SDK package `tools`; fixed by explicitly requesting only `platform-tools` from the setup action.
- The second run (ID 42374071869) passed Android SDK setup but the runner's repository did not expose `platforms;android-37`; changed `compileSdk` and CI installation to Android 36.
- The third run (ID 42374146211) installed Android 36 and reached Gradle, then failed applying `org.jetbrains.kotlin.android`: AGP 9 already provides built-in Kotlin, so the extra plugin caused a duplicate `kotlin` extension. Removed the redundant plugin from root and app build files and removed the deprecated `android.kotlinOptions` block. AGP built-in Kotlin defaults its JVM target to Java `targetCompatibility`.
- The fourth run (ID 42374327528) passed Gradle configuration and SDK installation. Its dependency metadata check then showed that Compose 1.12.1 (BOM 2026.09.00) requires compileSdk 37, while the runner's stable SDK repository only supplied platform 36. Kotlin source compilation ran with deprecation warnings, not Kotlin errors. Switched to the prior stable Compose BOM 2026.06.01 (Compose 1.11.4 release line) to keep the app on stable API 36 and removed the deprecated non-mirrored MenuBook/VolumeUp icon references.
- Android's current Compose guidance states that Compose 1.12.0+ requires compileSdk 37; the August 2026 release notes confirm the requirement. References: https://developer.android.com/develop/ui/compose/setup-compose-dependencies-and-compiler and https://android-developers.googleblog.com/2026/08/jetpack-compose-august-2026-release.html. APK packaging remains unverified until the next run succeeds.
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


## 9. Android SDK compatibility adjustment — 2026-11-07

- The second CI run (ID 42374071869) confirmed the obsolete `tools` package issue was fixed and Android SDK setup completed.
- The explicit SDK install then failed with `Failed to find package 'platforms;android-37'`; no Gradle build was attempted.
- To align the build with an SDK platform actually available from the runner's configured repository, changed `compileSdk` and the CI SDK install to Android 36. `targetSdk` and Build Tools stay at 36.
- The next Actions run must establish whether Android 36 installs successfully and expose any real Kotlin or dependency compilation errors.


## 10. AGP 9 built-in Kotlin migration — 2026-11-07

- Third CI run (ID 42374146211) passed Android SDK package installation and reached Gradle.
- Gradle failed while applying `org.jetbrains.kotlin.android` with: `Cannot add extension with name 'kotlin', as there is an extension already registered with that name.`
- Root cause confirmed against Android's current migration guide: AGP 9.0+ includes Kotlin support by default; the separate `org.jetbrains.kotlin.android` plugin conflicts with it. Removed that plugin from root and app plugin declarations. Retained `org.jetbrains.kotlin.plugin.compose` 2.2.10 to configure the Compose compiler.
- Removed the old `android.kotlinOptions` configuration. The Java target is 17; AGP built-in Kotlin defaults its JVM target to `android.compileOptions.targetCompatibility`.
- Official reference: https://developer.android.com/build/migrate-to-built-in-kotlin
- The next CI run must confirm that Gradle configuration, dependency resolution, Kotlin compilation, packaging, and APK artifact upload all succeed.


## 11. Compose/API-level compatibility — 2026-11-07

- Fourth run (ID 42374327528) confirmed SDK 36 installation and AGP built-in Kotlin configuration now work.
- The build then failed at `:app:checkDebugAarMetadata`: the Compose 1.12.1 artifacts selected by BOM 2026.09.00 require `compileSdk 37`; runner stable SDK repository has Android 36 platform only. The Kotlin source compilation task emitted warnings about non-auto-mirrored icons but no Kotlin source errors.
- Changed the Compose BOM to `2026.06.01`, from the stable Compose 1.11.4 line, which predates the Compose 1.12/API 37 requirement. Kept `compileSdk 36` and `targetSdk 36`.
- Replaced `Icons.Filled.MenuBook` and `Icons.Filled.VolumeUp` with AutoMirrored variants to avoid RTL-direction deprecation warnings.
- The next workflow run must verify dependency metadata, source compilation, APK packaging, and artifact upload.


## 12. First successful Android build — 2026-11-07

- Successful Actions run: `42374515936` — [Build result](https://github.com/CybersecurityRahbar/Englishat/actions/runs/42374515936).
- Branch commit built: `2eac6d034eb34fa79ae2e6e387294a12614d7b82`.
- All main workflow steps completed successfully: checkout, Java 17, Gradle 9.3.1, Android SDK setup, Android 36 / Build Tools 36 installation, `:app:assembleDebug`, and artifact upload.
- GitHub Actions artifact: `englishat-debug-apk` (artifact ID `13575431211`), ZIP size 18,814,438 bytes, retained until 2026-11-21T12:56:02Z.
- Downloaded and inspected the workflow ZIP in the working environment. It contains `app-debug.apk` (19,423,252 bytes). ZIP integrity check passed; file was identified as an Android APK.
- APK SHA-256: `c8d862b429ebf476ad8384e95905dfcc1890e526cc5639400baa408fcc419c83`.
- Artifact ZIP SHA-256: `f5a8f7f6811f581b235ca3c3705b623117d5b01f6813920097bfec4061fe38c8`.
- This confirms a successful debug APK build and artifact upload. It does not yet constitute an emulator/device installation test, UI interaction test, or release-signed APK.
- Future next steps: install the debug APK on a test Android device; verify RTL layout, TTS voice, lesson completion/favorites/progress persistence, and practice scoring; then improve the lesson/curriculum depth, add the full prototype vocabulary after editorial QA, build spaced repetition and listening/speaking exercises, and add automated tests. Add a Gradle Wrapper so local builds do not depend on manually installed Gradle.


## 13. Product expansion and brand refresh — 2026-11-07

The user tested the first APK and approved the direction as a good start. This iteration responds to the request for a more advanced and competitive experience, removal of the white sparkle/Gemini-like mark, and a proper application icon.

### Brand and UI
- Removed the white sparkle character from the Englishat in-app hero wordmark.
- Added a native Android adaptive launcher icon: an original open-book mark paired with a small conversation bubble, on a navy background with gold and blue accents. No third-party logo or copied sparkle is used.
- Bumped the app to versionName 0.2.0 / versionCode 2 to support update-install over the previous build when signed with the same CI debug key.

### Learning improvements
- Expanded the A1 path from four to eight lessons: Present Simple, Have/Has, Question Words, and Prepositions of Place were added with Arabic explanations, contextual English examples, and takeaways.
- Added 24 curated vocabulary entries covering routines, verbs, home items, time expressions, and place prepositions. Existing prototype-derived vocabulary remains subject to editorial QA before bulk import.
- Expanded the practice question pool from eight to sixteen items.
- Added daily flashcard review with batches of up to ten due/new words. Learners reveal the meaning and example, listen to pronunciation, and rate recall as difficult, remembered, or easy.
- Review state is persisted locally in Preferences DataStore. Successful recall increases intervals; difficult words are scheduled to return the same day.
- These additions remain offline-first; no account or server dependency was introduced.

### Research reviewed for this iteration
- Duolingo emphasizes short lessons, progress rewards, habit loops, and practice across reading, writing, listening, and speaking: https://www.duolingo.com/learn
- Busuu's February 2026 support article describes scenario-based speaking practice linked to CEFR levels: https://help.busuu.com/hc/en-us/articles/21862192336402-What-are-Busuu-Conversations-and-how-can-they-help-me-learn-a-language
- British Council Yemen's app catalogue highlights grammar, videos/listening, pronunciation, podcasts with transcripts, vocabulary, and timed quizzes: https://yemen.britishcouncil.org/en/english/apps
- British Council LearnEnglish Grammar describes practice tasks including fill-in-the-blanks, word ordering, and labelling: https://learnenglish.britishcouncil.org/apps/learnenglish-grammar
- CEFR descriptors support measurable can-do goals across language activities: https://www.coe.int/en/web/common-european-framework-reference-languages/cefr-descriptors

### Validation still required
- The new GitHub Actions build must pass before the new APK is offered.
- Device retest is required for launcher-icon masking, update-install, RTL layout, voice playback, quiz behavior, and spaced-review persistence.
- The review scheduler is an initial offline spaced-repetition implementation; it is not a full adaptive learning engine or pronunciation assessment.
