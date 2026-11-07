package com.englishat.app.data

data class LearningExample(val english: String, val arabic: String)
data class Lesson(
    val id: String,
    val title: String,
    val subtitle: String,
    val level: String,
    val duration: String,
    val icon: String,
    val objective: String,
    val explanation: String,
    val examples: List<LearningExample>,
    val takeaways: List<String>
)
data class VocabularyWord(
    val word: String,
    val meaning: String,
    val pronunciation: String,
    val type: String,
    val category: String,
    val example: String,
    val exampleArabic: String
)
data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

object LearningContent {
    val lessons = listOf(
        Lesson(
            id = "greetings", title = "Greetings & Introductions", subtitle = "التحية والتعارف",
            level = "A1", duration = "8 دقائق", icon = "💬",
            objective = "تبدأ محادثة قصيرة، وتعرّف بنفسك، وتسأل عن اسم شخص آخر.",
            explanation = "ابدأ بتحية مناسبة، ثم اسأل عن الاسم وقدّم نفسك. بعد التعارف يمكنك أن تقول إنك سعيد بلقاء الشخص، وتسأله عن حاله.",
            examples = listOf(
                LearningExample("Hello! / Hi!", "مرحباً! / أهلاً!"),
                LearningExample("What's your name?", "ما اسمك؟"),
                LearningExample("My name is Ali.", "اسمي علي."),
                LearningExample("Nice to meet you.", "سعيد بلقائك."),
                LearningExample("How are you? — I'm fine, thanks.", "كيف حالك؟ — أنا بخير، شكراً.")
            ),
            takeaways = listOf(
                "Hi أكثر عفوية من Hello في المحادثات اليومية.",
                "استخدم My name is... أو I'm... لتقديم نفسك.",
                "يمكنك إنهاء الحديث بعبارة See you later أو Goodbye."
            )
        ),
        Lesson(
            id = "verb_be", title = "Verb to Be", subtitle = "am · is · are",
            level = "A1", duration = "10 دقائق", icon = "🔤",
            objective = "تستخدم am و is و are لبناء جمل بسيطة عن نفسك والآخرين.",
            explanation = "يأتي فعل be في المضارع بثلاث صور أساسية. اختر الصورة وفق الضمير الذي يسبقها: I يأخذ am، وHe أو She أو It تأخذ is، بينما You وWe وThey تأخذ are.",
            examples = listOf(
                LearningExample("I am a student.", "أنا طالب."),
                LearningExample("She is happy.", "هي سعيدة."),
                LearningExample("They are friends.", "هم أصدقاء."),
                LearningExample("We are ready.", "نحن مستعدون."),
                LearningExample("He isn't tired.", "هو ليس متعباً.")
            ),
            takeaways = listOf(
                "I → am  |  He / She / It → is",
                "You / We / They → are",
                "في النفي نضيف not بعد فعل be: She is not tired.",
                "الاختصارات الشائعة: I'm · You're · He's · She's · We're · They're."
            )
        ),
        Lesson(
            id = "pronouns", title = "Personal Pronouns", subtitle = "I · You · He · She · It · We · They",
            level = "A1", duration = "7 دقائق", icon = "👥",
            objective = "تختار الضمير المناسب بدلاً من تكرار أسماء الأشخاص والأشياء.",
            explanation = "الضمير يحل محل الاسم في الجملة. نستخدم He للذكر، وShe للأنثى، وIt غالباً للأشياء والحيوانات عندما لا نحدد جنسها. أما You فتصلح للمفرد والجمع.",
            examples = listOf(
                LearningExample("I am Ali.", "أنا علي."),
                LearningExample("He is my brother.", "هو أخي."),
                LearningExample("She is a teacher.", "هي معلمة."),
                LearningExample("It is a book.", "إنه كتاب."),
                LearningExample("We are students.", "نحن طلاب.")
            ),
            takeaways = listOf(
                "I = أنا · You = أنت / أنتم",
                "He = هو · She = هي · It = لغير العاقل غالباً",
                "We = نحن · They = هم / هن."
            )
        ),
        Lesson(
            id = "articles", title = "A / An / The", subtitle = "أدوات التعريف والتنكير",
            level = "A1", duration = "9 دقائق", icon = "🧩",
            objective = "تختار أداة مناسبة قبل الاسم في الجمل البسيطة.",
            explanation = "نستخدم a أو an مع اسم مفرد غير محدد. الاختيار بينهما يعتمد على الصوت الأول في الكلمة لا على الحرف المكتوب وحده. ونستخدم the عندما يكون الشيء محدداً أو معروفاً للمتحدث والمستمع.",
            examples = listOf(
                LearningExample("I have a car.", "لدي سيارة."),
                LearningExample("She eats an apple.", "هي تأكل تفاحة."),
                LearningExample("Open the door.", "افتح الباب."),
                LearningExample("He is an engineer.", "هو مهندس.")
            ),
            takeaways = listOf(
                "A تأتي قبل صوت ساكن: a book.",
                "An تأتي قبل صوت متحرك: an apple, an hour.",
                "The تشير إلى شيء محدد أو معروف."
            )
        )
    )

    val vocabulary = listOf(
        VocabularyWord("hello", "مرحباً", "هَلّو", "تحية", "أساسيات", "Hello, Sara!", "مرحباً يا سارة!"),
        VocabularyWord("hi", "أهلاً", "هاي", "تحية غير رسمية", "أساسيات", "Hi! How are you?", "أهلاً! كيف حالك؟"),
        VocabularyWord("goodbye", "إلى اللقاء", "غودباي", "تحية", "أساسيات", "Goodbye, see you tomorrow.", "إلى اللقاء، أراك غداً."),
        VocabularyWord("name", "اسم", "نيم", "اسم", "أساسيات", "What's your name?", "ما اسمك؟"),
        VocabularyWord("student", "طالب", "ستودَنت", "اسم", "التعليم", "I am a student.", "أنا طالب."),
        VocabularyWord("teacher", "معلّم", "تيتشر", "اسم", "التعليم", "Our teacher is kind.", "معلّمنا لطيف."),
        VocabularyWord("friend", "صديق", "فرِند", "اسم", "الأشخاص", "She is my best friend.", "هي صديقتي المقرّبة."),
        VocabularyWord("happy", "سعيد", "هابي", "صفة", "المشاعر", "They are happy today.", "هم سعداء اليوم."),
        VocabularyWord("family", "عائلة", "فاميلي", "اسم", "الأشخاص", "My family is big.", "عائلتي كبيرة."),
        VocabularyWord("house", "منزل", "هاوس", "اسم", "الحياة اليومية", "This is our house.", "هذا منزلنا."),
        VocabularyWord("school", "مدرسة", "سكول", "اسم", "التعليم", "The school is near.", "المدرسة قريبة."),
        VocabularyWord("water", "ماء", "ووتر", "اسم", "الحياة اليومية", "I drink water every morning.", "أشرب الماء كل صباح."),
        VocabularyWord("beautiful", "جميل", "بيوتفُل", "صفة", "الصفات", "It is a beautiful day.", "إنه يوم جميل."),
        VocabularyWord("Monday", "الاثنين", "مَنداي", "اسم", "الوقت", "The course starts on Monday.", "تبدأ الدورة يوم الاثنين."),
        VocabularyWord("Saturday", "السبت", "ساتَرداي", "اسم", "الوقت", "I study English on Saturday.", "أدرس الإنجليزية يوم السبت."),
        VocabularyWord("twenty", "عشرون", "توينتي", "عدد", "الأرقام", "I am twenty years old.", "عمري عشرون عاماً."),
        VocabularyWord("famous", "مشهور", "فايمَس", "صفة", "الصفات", "The city is famous.", "المدينة مشهورة."),
        VocabularyWord("modern", "حديث / عصري", "مودَرن", "صفة", "الصفات", "This is a modern building.", "هذا مبنى حديث."),
        VocabularyWord("difficult", "صعب", "ديفيكَلت", "صفة", "الصفات", "This lesson is not difficult.", "هذا الدرس ليس صعباً."),
        VocabularyWord("careful", "حذر", "كيرفُل", "صفة", "الصفات", "Be careful on the road.", "كن حذراً على الطريق."),
        VocabularyWord("luxurious", "فاخر", "لَكشَريَس", "صفة", "الصفات", "They stayed in a luxurious hotel.", "أقاموا في فندق فاخر."),
        VocabularyWord("detective", "محقّق", "دِتِكتِف", "اسم", "الأشخاص", "The detective asked a question.", "طرح المحقّق سؤالاً."),
        VocabularyWord("furniture", "أثاث", "فِرنتشر", "اسم غير معدود", "المنزل", "We bought new furniture.", "اشترينا أثاثاً جديداً."),
        VocabularyWord("neighbor", "جار", "نيبَر", "اسم", "الأشخاص", "Our neighbor is friendly.", "جارنا ودود."),
        VocabularyWord("balcony", "شرفة", "بالكَني", "اسم", "المنزل", "The apartment has a balcony.", "الشقة لها شرفة."),
        VocabularyWord("apartment", "شقة", "أبارتمنت", "اسم", "المنزل", "She lives in an apartment.", "هي تسكن في شقة."),
        VocabularyWord("recently", "مؤخراً", "ريسَنتلي", "ظرف", "الوقت", "I recently started a new course.", "بدأت دورة جديدة مؤخراً."),
        VocabularyWord("dangerous", "خطير", "دينجَرَس", "صفة", "المشاعر والمخاطر", "This road can be dangerous.", "قد يكون هذا الطريق خطيراً."),
        VocabularyWord("experience", "خبرة / تجربة", "إكسبيرينس", "اسم", "التعليم والعمل", "Learning is a valuable experience.", "التعلّم تجربة قيّمة."),
        VocabularyWord("idea", "فكرة", "آيديا", "اسم", "التواصل", "That is a good idea.", "هذه فكرة جيدة."),
        VocabularyWord("fluent", "طليق في اللغة", "فلوَنت", "صفة", "التعليم", "She is fluent in English.", "هي تتحدث الإنجليزية بطلاقة.")
    )

    val categories = listOf("الكل", "أساسيات", "الأشخاص", "التعليم", "الحياة اليومية", "الصفات", "المنزل", "الوقت")

    val questions = listOf(
        QuizQuestion("I ___ a student.", listOf("am", "is", "are"), 0, "الضمير I يأخذ am."),
        QuizQuestion("She ___ happy.", listOf("am", "is", "are"), 1, "الضمير She يأخذ is."),
        QuizQuestion("They ___ friends.", listOf("am", "is", "are"), 2, "الضمير They يأخذ are."),
        QuizQuestion("___ is my sister.", listOf("He", "She", "They"), 1, "نستخدم She عند الحديث عن أنثى."),
        QuizQuestion("I have ___ apple.", listOf("a", "an", "the"), 1, "Apple تبدأ بصوت متحرك؛ لذلك نستخدم an."),
        QuizQuestion("What is your ___?", listOf("name", "school", "water"), 0, "نسأل عن الاسم بعبارة What is your name?"),
        QuizQuestion("ما معنى Goodbye؟", listOf("مرحباً", "إلى اللقاء", "شكراً"), 1, "Goodbye تعني إلى اللقاء."),
        QuizQuestion("Which day comes after Friday?", listOf("Thursday", "Saturday", "Sunday"), 1, "Saturday يأتي بعد Friday.")
    )
}
