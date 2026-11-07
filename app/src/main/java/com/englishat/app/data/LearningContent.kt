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
        ),
        Lesson(
            id = "present_simple", title = "Present Simple", subtitle = "العادات والروتين اليومي",
            level = "A1", duration = "10 دقائق", icon = "🕒",
            objective = "تصف عاداتك وروتينك اليومي بجمل مثبتة ومنفية وأسئلة بسيطة.",
            explanation = "نستخدم المضارع البسيط للأشياء التي تتكرر أو الحقائق العامة. مع I وYou وWe وThey نستخدم الفعل بصورته الأساسية، أما مع He وShe وIt فنضيف غالباً s أو es. في النفي نستخدم don't أو doesn't ثم الفعل الأساسي.",
            examples = listOf(LearningExample("I study English every day.", "أدرس الإنجليزية كل يوم."), LearningExample("She works in a hospital.", "هي تعمل في مستشفى."), LearningExample("They don't drink coffee.", "هم لا يشربون القهوة."), LearningExample("Does he play football?", "هل يلعب كرة القدم؟")),
            takeaways = listOf("He / She / It + verb-s: She reads.", "النفي: don't مع I/You/We/They وdoesn't مع He/She/It.", "بعد does أو doesn't نستخدم الفعل الأساسي دون s.")
        ),
        Lesson(
            id = "have_has", title = "Have / Has", subtitle = "الملكية والوصف",
            level = "A1", duration = "7 دقائق", icon = "🎒",
            objective = "تتحدث عن الأشياء التي تملكها وعن أفراد العائلة والصفات.",
            explanation = "نستخدم have مع I وYou وWe وThey، ونستخدم has مع He وShe وIt. ويمكن استخدامهما للسؤال عن الملكية والنفي بمساعدة do أو does.",
            examples = listOf(LearningExample("I have a new book.", "لدي كتاب جديد."), LearningExample("She has two brothers.", "لديها أخوان."), LearningExample("Do you have a pen?", "هل لديك قلم؟"), LearningExample("He doesn't have a car.", "ليس لديه سيارة.")),
            takeaways = listOf("I / You / We / They → have", "He / She / It → has", "في السؤال: Do you have...? وDoes she have...?")
        ),
        Lesson(
            id = "question_words", title = "Question Words", subtitle = "What · Where · When · Who · Why · How",
            level = "A1", duration = "8 دقائق", icon = "❓",
            objective = "تطرح أسئلة مفيدة للحصول على معلومات أساسية.",
            explanation = "تبدأ أسئلة المعلومات بكلمة استفهام تحدد نوع الإجابة: What للشيء أو المعلومة، Where للمكان، When للوقت، Who للشخص، Why للسبب، وHow للطريقة أو الحالة.",
            examples = listOf(LearningExample("What is your name?", "ما اسمك؟"), LearningExample("Where do you live?", "أين تسكن؟"), LearningExample("When does the class start?", "متى يبدأ الدرس؟"), LearningExample("Who is your teacher?", "من معلمك؟"), LearningExample("How are you today?", "كيف حالك اليوم؟")),
            takeaways = listOf("What = ماذا / ما، Where = أين، When = متى.", "Who = من، Why = لماذا، How = كيف.", "ضع كلمة السؤال في بداية السؤال، ثم أكمل ترتيب الجملة.")
        ),
        Lesson(
            id = "prepositions", title = "Prepositions of Place", subtitle = "in · on · under · next to · between",
            level = "A1", duration = "8 دقائق", icon = "📍",
            objective = "تصف مكان الأشياء والأشخاص باستخدام حروف الجر الشائعة.",
            explanation = "توضح حروف الجر مكان شيء بالنسبة إلى شيء آخر. in تعني داخل، on تعني على سطح، under تعني تحت، next to تعني بجانب، وbetween تعني بين شيئين.",
            examples = listOf(LearningExample("The keys are on the table.", "المفاتيح على الطاولة."), LearningExample("The cat is under the chair.", "القطة تحت الكرسي."), LearningExample("The milk is in the fridge.", "الحليب داخل الثلاجة."), LearningExample("The bank is next to the school.", "البنك بجانب المدرسة.")),
            takeaways = listOf("in = داخل، on = على سطح، under = أسفل.", "next to = بجانب، between = بين شيئين.", "تعلّم حرف الجر مع جملة كاملة وصورة ذهنية للمكان.")
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
        VocabularyWord("happy", "سعيد", "هابي", "صفة", "المشاعر والمخاطر", "They are happy today.", "هم سعداء اليوم."),
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
        VocabularyWord("fluent", "طليق في اللغة", "فلوَنت", "صفة", "التعليم", "She is fluent in English.", "هي تتحدث الإنجليزية بطلاقة."),
        VocabularyWord("morning", "الصباح", "مورنينغ", "اسم", "الوقت", "I study in the morning.", "أدرس في الصباح."),
        VocabularyWord("evening", "المساء", "إيفنينغ", "اسم", "الوقت", "We walk in the evening.", "نتمشى في المساء."),
        VocabularyWord("breakfast", "وجبة الإفطار", "بريكفَست", "اسم", "الحياة اليومية", "I have breakfast at seven.", "أتناول الإفطار عند السابعة."),
        VocabularyWord("lunch", "وجبة الغداء", "لَنتش", "اسم", "الحياة اليومية", "Lunch is ready.", "الغداء جاهز."),
        VocabularyWord("dinner", "وجبة العشاء", "دِنَر", "اسم", "الحياة اليومية", "We have dinner together.", "نتناول العشاء معاً."),
        VocabularyWord("read", "يقرأ", "ريد", "فعل", "الأفعال", "I read a short story.", "أقرأ قصة قصيرة."),
        VocabularyWord("write", "يكتب", "رايت", "فعل", "الأفعال", "Please write your name.", "من فضلك اكتب اسمك."),
        VocabularyWord("speak", "يتحدث", "سبيك", "فعل", "الأفعال", "Can you speak English?", "هل تستطيع التحدث بالإنجليزية؟"),
        VocabularyWord("listen", "يستمع", "لِسِن", "فعل", "الأفعال", "Listen to the dialogue.", "استمع إلى الحوار."),
        VocabularyWord("work", "عمل / يعمل", "وِرك", "اسم / فعل", "التعليم والعمل", "I work from Sunday to Thursday.", "أعمل من الأحد إلى الخميس."),
        VocabularyWord("market", "سوق", "ماركت", "اسم", "الأماكن", "The market is near my house.", "السوق قريب من منزلي."),
        VocabularyWord("kitchen", "مطبخ", "كِتشن", "اسم", "المنزل", "My mother is in the kitchen.", "أمي في المطبخ."),
        VocabularyWord("bedroom", "غرفة النوم", "بِدروم", "اسم", "المنزل", "The bedroom is clean.", "غرفة النوم نظيفة."),
        VocabularyWord("small", "صغير", "سمول", "صفة", "الصفات", "I live in a small house.", "أعيش في منزل صغير."),
        VocabularyWord("large", "كبير / واسع", "لارج", "صفة", "الصفات", "They have a large garden.", "لديهم حديقة واسعة."),
        VocabularyWord("tired", "متعب", "تايرد", "صفة", "المشاعر والمخاطر", "I am tired after work.", "أنا متعب بعد العمل."),
        VocabularyWord("early", "مبكراً", "إيرلي", "ظرف", "الوقت", "She wakes up early.", "تستيقظ مبكراً."),
        VocabularyWord("late", "متأخر", "ليت", "صفة / ظرف", "الوقت", "The bus is late.", "الحافلة متأخرة."),
        VocabularyWord("behind", "خلف", "بِهايند", "حرف جر", "الأماكن", "The garden is behind the house.", "الحديقة خلف المنزل."),
        VocabularyWord("between", "بين", "بتوين", "حرف جر", "الأماكن", "The shop is between two banks.", "المتجر بين مصرفين."),
        VocabularyWord("under", "تحت", "أندر", "حرف جر", "الأماكن", "The bag is under the chair.", "الحقيبة تحت الكرسي."),
        VocabularyWord("next to", "بجانب", "نكست تو", "عبارة حرف جر", "الأماكن", "The school is next to the park.", "المدرسة بجانب الحديقة."),
        VocabularyWord("often", "غالباً", "أوفِن", "ظرف", "الوقت", "I often practise English.", "غالباً ما أتمرّن على الإنجليزية."),
        VocabularyWord("always", "دائماً", "أولويز", "ظرف", "الوقت", "He always drinks water.", "هو يشرب الماء دائماً.")
    )

    val categories = listOf("الكل", "أساسيات", "الأشخاص", "التعليم", "التعليم والعمل", "الحياة اليومية", "الأفعال", "الصفات", "المنزل", "الوقت", "المشاعر والمخاطر", "الأماكن")

    val questions = listOf(
        QuizQuestion("I ___ a student.", listOf("am", "is", "are"), 0, "الضمير I يأخذ am."),
        QuizQuestion("She ___ happy.", listOf("am", "is", "are"), 1, "الضمير She يأخذ is."),
        QuizQuestion("They ___ friends.", listOf("am", "is", "are"), 2, "الضمير They يأخذ are."),
        QuizQuestion("___ is my sister.", listOf("He", "She", "They"), 1, "نستخدم She عند الحديث عن أنثى."),
        QuizQuestion("I have ___ apple.", listOf("a", "an", "the"), 1, "Apple تبدأ بصوت متحرك؛ لذلك نستخدم an."),
        QuizQuestion("What is your ___?", listOf("name", "school", "water"), 0, "نسأل عن الاسم بعبارة What is your name?"),
        QuizQuestion("ما معنى Goodbye؟", listOf("مرحباً", "إلى اللقاء", "شكراً"), 1, "Goodbye تعني إلى اللقاء."),
        QuizQuestion("Which day comes after Friday?", listOf("Thursday", "Saturday", "Sunday"), 1, "Saturday يأتي بعد Friday."),
        QuizQuestion("He ___ to school every day.", listOf("go", "goes", "going"), 1, "مع He نضيف s إلى الفعل في المضارع البسيط."),
        QuizQuestion("We ___ breakfast at seven.", listOf("has", "have", "having"), 1, "نستخدم have مع We."),
        QuizQuestion("___ do you live?", listOf("Where", "Who", "When"), 0, "Where تستخدم للسؤال عن المكان."),
        QuizQuestion("The book is ___ the table.", listOf("under", "on", "between"), 1, "on تعني على سطح الشيء."),
        QuizQuestion("She ___ English very well.", listOf("speak", "speaks", "speaking"), 1, "مع She نضيف s إلى الفعل."),
        QuizQuestion("I ___ like tea.", listOf("doesn't", "don't", "am not"), 1, "نستخدم don't مع I."),
        QuizQuestion("The cat is ___ the chair.", listOf("under", "between", "next"), 0, "under تعني تحت."),
        QuizQuestion("___ is your birthday?", listOf("Where", "When", "Who"), 1, "When تستخدم للسؤال عن الوقت.")
    )
}
