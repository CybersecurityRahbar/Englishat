package com.englishat.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.englishat.app.data.LearningContent
import com.englishat.app.data.LearningProgress
import com.englishat.app.data.Lesson
import com.englishat.app.data.VocabularyWord
import com.englishat.app.data.ReviewRating
import java.time.LocalDate

private enum class AppTab(val label: String, val icon: ImageVector) {
    Home("الرئيسية", Icons.Filled.Home),
    Learn("التعلّم", Icons.AutoMirrored.Filled.MenuBook),
    Practice("التدريب", Icons.Filled.School),
    Words("الكلمات", Icons.Filled.Translate),
    Profile("حسابي", Icons.Filled.Person)
}

@Composable
fun EnglishatApp(
    progress: LearningProgress,
    onSpeak: (String) -> Unit,
    onCompleteLesson: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onPracticeCompleted: (Int) -> Unit,
    onReviewWord: (String, ReviewRating) -> Unit
) {
    var openReviewOnPractice by rememberSaveable { mutableStateOf(false) }
    var selectedTab by rememberSaveable { mutableStateOf(AppTab.Home) }
    var selectedLessonId by rememberSaveable { mutableStateOf<String?>(null) }

    androidx.compose.runtime.CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            containerColor = Color(0xFFF5F7FC),
            bottomBar = {
                if (selectedLessonId == null) {
                    NavigationBar(
                        containerColor = Color.White,
                        tonalElevation = 8.dp,
                        windowInsets = WindowInsets.navigationBars
                    ) {
                        AppTab.entries.forEach { tab ->
                            NavigationBarItem(
                                selected = selectedTab == tab,
                                onClick = { selectedTab = tab },
                                icon = { Icon(tab.icon, contentDescription = null) },
                                label = { Text(tab.label, fontSize = 11.sp, fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Medium) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color(0xFF245BDB),
                                    selectedTextColor = Color(0xFF245BDB),
                                    indicatorColor = Color(0xFFE7EEFF),
                                    unselectedIconColor = Color(0xFF8893A8),
                                    unselectedTextColor = Color(0xFF8893A8)
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                color = Color(0xFFF5F7FC)
            ) {
                val currentLesson = LearningContent.lessons.firstOrNull { it.id == selectedLessonId }
                if (currentLesson != null) {
                    LessonDetailScreen(
                        lesson = currentLesson,
                        isCompleted = currentLesson.id in progress.completedLessons,
                        onBack = { selectedLessonId = null },
                        onSpeak = onSpeak,
                        onComplete = {
                            onCompleteLesson(currentLesson.id)
                            selectedLessonId = null
                        }
                    )
                } else {
                    when (selectedTab) {
                        AppTab.Home -> HomeScreen(progress, { selectedLessonId = it }, { tab, reviewMode -> selectedTab = tab; openReviewOnPractice = reviewMode })
                        AppTab.Learn -> LearnScreen(progress) { selectedLessonId = it }
                        AppTab.Practice -> PracticeScreen(progress, onSpeak, onPracticeCompleted, onReviewWord, openReviewOnPractice)
                        AppTab.Words -> VocabularyScreen(progress, onSpeak, onToggleFavorite)
                        AppTab.Profile -> ProfileScreen(progress)
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(progress: LearningProgress, onContinue: (String) -> Unit, onNavigate: (AppTab, Boolean) -> Unit) {
    val completed = progress.completedLessons.size.coerceAtMost(LearningContent.lessons.size)
    val percent = completed * 100 / LearningContent.lessons.size
    val nextLesson = LearningContent.lessons.firstOrNull { it.id !in progress.completedLessons } ?: LearningContent.lessons.first()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("مرحباً بك 👋", color = Color(0xFF69758D), fontSize = 14.sp)
                    Text("لنطوّر لغتك الإنجليزية", color = Color(0xFF17223B), fontSize = 23.sp, fontWeight = FontWeight.ExtraBold)
                }
                Box(Modifier.size(48.dp).clip(CircleShape).background(Color(0xFFE5EDFF)), contentAlignment = Alignment.Center) {
                    Text("A", color = Color(0xFF245BDB), fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                }
            }
        }
        item { HeroCard(percent) { onContinue(nextLesson.id) } }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                StatCard("النقاط", progress.xp.toString(), "⭐", Modifier.weight(1f))
                StatCard("أيام متتالية", progress.streak.toString(), "🔥", Modifier.weight(1f))
                StatCard("دروس مكتملة", completed.toString(), "✓", Modifier.weight(1f))
            }
        }
        item { SectionHeading("مسارك اليوم", "ابدأ بخطوة قصيرة وثابتة") }
        item { LessonRow(nextLesson, nextLesson.id in progress.completedLessons, { onContinue(nextLesson.id) }, true) }
        item { SectionHeading("ماذا تريد أن تتدرّب؟", "اختر نشاطاً سريعاً") }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                QuickActionCard("اختبر نفسك", "🎯", Modifier.weight(1f)) { onNavigate(AppTab.Practice, false) }
                QuickActionCard("مراجعة ذكية", "🧠", Modifier.weight(1f)) { onNavigate(AppTab.Practice, true) }
            }
        }
        item {
            Surface(color = Color(0xFFEAF7F3), shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("💡", fontSize = 24.sp)
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("نصيحة اليوم", fontWeight = FontWeight.Bold, color = Color(0xFF126B58))
                        Text("استمع إلى الجملة، ثم كرّرها بصوتك ثلاث مرات.", color = Color(0xFF416D64), fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroCard(completedPercent: Int, onContinue: () -> Unit) {
    Box(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(27.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF10264B), Color(0xFF245BDB), Color(0xFF5479E8))))
            .padding(22.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Surface(color = Color(0x33FFFFFF), shape = RoundedCornerShape(40.dp)) {
                    Text("مسار المبتدئ  ·  A1", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp))
                }
                Text("ENGLISHAT", color = Color(0xFFD7E2FF), fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Text("كل يوم كلمة،\nوكل يوم خطوة.", color = Color.White, fontSize = 29.sp, lineHeight = 37.sp, fontWeight = FontWeight.ExtraBold)
            Text("تعلّم بطريقة منظمة تجمع الشرح والأمثلة والصوت والتدريب.", color = Color(0xFFDDE7FF), fontSize = 13.sp, lineHeight = 21.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("تقدّمك في المسار", color = Color(0xFFDDE7FF), fontSize = 11.sp)
                    Spacer(Modifier.height(7.dp))
                    Box(Modifier.fillMaxWidth().height(7.dp).clip(CircleShape).background(Color(0x44FFFFFF))) {
                        Box(Modifier.fillMaxWidth(completedPercent / 100f).height(7.dp).clip(CircleShape).background(Color(0xFF68E0C1)))
                    }
                    Text(completedPercent.toString() + "% مكتمل", color = Color.White, fontSize = 11.sp, modifier = Modifier.padding(top = 5.dp))
                }
                Spacer(Modifier.width(13.dp))
                Button(
                    onClick = onContinue,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF173D9C)),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 11.dp)
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                    Text("تابع", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun StatCard(title: String, value: String, emoji: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE9EDF5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(emoji, fontSize = 19.sp)
            Text(value, fontSize = 21.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF17223B))
            Text(title, fontSize = 10.sp, color = Color(0xFF78849A), textAlign = TextAlign.Center)
        }
    }
}

@Composable
private fun SectionHeading(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(title, fontSize = 18.sp, color = Color(0xFF17223B), fontWeight = FontWeight.ExtraBold)
        Text(subtitle, fontSize = 12.sp, color = Color(0xFF8993A7))
    }
}

@Composable
private fun LessonRow(lesson: Lesson, completed: Boolean, onClick: () -> Unit, prominent: Boolean = false) {
    Card(
        onClick = onClick, modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, if (prominent) Color(0xFFDCE5FC) else Color(0xFFE9EDF5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(48.dp).clip(RoundedCornerShape(15.dp)).background(Color(0xFFEAF0FF)), contentAlignment = Alignment.Center) {
                Text(lesson.icon, fontSize = 22.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(lesson.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF1B2943))
                    if (completed) Icon(Icons.Filled.CheckCircle, contentDescription = "مكتمل", tint = Color(0xFF16A58A), modifier = Modifier.size(16.dp))
                }
                Text(lesson.subtitle, color = Color(0xFF758199), fontSize = 12.sp)
                Text(lesson.duration + "  ·  " + lesson.level, color = Color(0xFF9AA3B3), fontSize = 10.sp)
            }
            Icon(Icons.Filled.PlayArrow, contentDescription = "افتح الدرس", tint = Color(0xFF245BDB))
        }
    }
}

@Composable
private fun QuickActionCard(title: String, emoji: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick, modifier = modifier, shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE9EDF5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(emoji, fontSize = 24.sp)
            Text(title, color = Color(0xFF23324E), fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}

@Composable
private fun LearnScreen(progress: LearningProgress, onOpenLesson: (String) -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp), verticalArrangement = Arrangement.spacedBy(13.dp)) {
        item {
            Text("مسار التعلّم", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF17223B))
            Text("دروس قصيرة مرتبة من الأساسيات إلى التطبيق.", color = Color(0xFF7D889E), fontSize = 13.sp)
        }
        item {
            Card(shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9F0FF)), elevation = CardDefaults.cardElevation(0.dp)) {
                Row(Modifier.padding(17.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("A1  ·  مبتدئ", color = Color(0xFF214FAF), fontSize = 17.sp, fontWeight = FontWeight.ExtraBold)
                        Text("المرحلة الأولى: بناء الأساس", color = Color(0xFF526A9C), fontSize = 12.sp)
                        Text(LearningContent.lessons.size.toString() + " دروس متاحة في هذه النسخة", color = Color(0xFF7183A7), fontSize = 11.sp)
                    }
                    Text("🎓", fontSize = 34.sp)
                }
            }
        }
        items(LearningContent.lessons) { lesson ->
            LessonRow(lesson, lesson.id in progress.completedLessons, { onOpenLesson(lesson.id) })
        }
        item {
            Surface(color = Color(0xFFF0F2F7), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Text("المستويات التالية A2 وB1 ستُضاف بعد اكتمال محتوى المرحلة التأسيسية.", color = Color(0xFF7E889B), fontSize = 12.sp, modifier = Modifier.padding(14.dp))
            }
        }
    }
}

@Composable
private fun LessonDetailScreen(
    lesson: Lesson, isCompleted: Boolean, onBack: () -> Unit,
    onSpeak: (String) -> Unit, onComplete: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("رجوع") }
            Spacer(Modifier.weight(1f))
            Text(lesson.level + "  ·  " + lesson.duration, color = Color(0xFF8590A5), fontSize = 12.sp)
        }
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 18.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(lesson.icon, fontSize = 34.sp)
            Text(lesson.title, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF17223B))
            Text(lesson.subtitle, color = Color(0xFF245BDB), fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Surface(color = Color(0xFFEAF7F3), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(15.dp)) {
                    Text("هدف الدرس", color = Color(0xFF13725D), fontWeight = FontWeight.Bold)
                    Text(lesson.objective, color = Color(0xFF456B63), fontSize = 13.sp)
                }
            }
            SectionHeading("افهم الفكرة", "شرح مبسط باللغة العربية")
            Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color(0xFFE8ECF4))) {
                Text(lesson.explanation, Modifier.padding(16.dp), fontSize = 14.sp, lineHeight = 25.sp, color = Color(0xFF3C4960))
            }
            SectionHeading("أمثلة مع الصوت", "استمع إلى الجملة ثم اقرأ معناها")
            lesson.examples.forEach { example ->
                Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color(0xFFE8ECF4))) {
                    Row(Modifier.fillMaxWidth().padding(13.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(example.english, color = Color(0xFF1C3156), fontWeight = FontWeight.Bold, fontSize = 15.sp, textAlign = TextAlign.Left)
                            Text(example.arabic, color = Color(0xFF7B879B), fontSize = 12.sp)
                        }
                        OutlinedButton(onClick = { onSpeak(example.english) }, contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp)) {
                            Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "استمع", modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
            SectionHeading("تذكّر هذه النقاط", "ملخص سريع قبل التدريب")
            lesson.takeaways.forEach { point ->
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()) {
                    Text("•", color = Color(0xFF16A58A), fontSize = 20.sp, modifier = Modifier.padding(end = 7.dp))
                    Text(point, color = Color(0xFF48556B), fontSize = 13.sp, lineHeight = 22.sp)
                }
            }
            Button(onClick = onComplete, modifier = Modifier.fillMaxWidth().height(52.dp), shape = RoundedCornerShape(16.dp)) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(if (isCompleted) "تم إكمال هذا الدرس" else "أنهيت الدرس · احفظ تقدمي")
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun VocabularyScreen(progress: LearningProgress, onSpeak: (String) -> Unit, onToggleFavorite: (String) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf("الكل") }
    var favoritesOnly by rememberSaveable { mutableStateOf(false) }
    val filteredWords = LearningContent.vocabulary.filter { word ->
        val matchesQuery = query.isBlank() || word.word.contains(query, true) || word.meaning.contains(query, true)
        val matchesCategory = selectedCategory == "الكل" || word.category == selectedCategory ||
            (selectedCategory == "الأشخاص" && word.category == "الأشخاص")
        val matchesFavorites = !favoritesOnly || word.word in progress.favorites
        matchesQuery && matchesCategory && matchesFavorites
    }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(horizontal = 18.dp, vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("قاموسك الشخصي", fontSize = 24.sp, color = Color(0xFF17223B), fontWeight = FontWeight.ExtraBold)
            Text("ابحث، استمع، واحفظ الكلمات التي تريد مراجعتها.", color = Color(0xFF7D889E), fontSize = 12.sp)
            OutlinedTextField(
                value = query, onValueChange = { query = it }, modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("ابحث عن كلمة أو معناها...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true, shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF245BDB), unfocusedBorderColor = Color(0xFFE3E8F1),
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
                )
            )
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                LearningContent.categories.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category && !favoritesOnly,
                        onClick = { selectedCategory = category; favoritesOnly = false },
                        label = { Text(category, fontSize = 11.sp) }
                    )
                }
                FilterChip(
                    selected = favoritesOnly,
                    onClick = { favoritesOnly = !favoritesOnly },
                    label = { Text("المفضلة", fontSize = 11.sp) },
                    leadingIcon = { Icon(Icons.Filled.Favorite, contentDescription = null, modifier = Modifier.size(15.dp)) }
                )
            }
        }
        if (filteredWords.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("لا توجد كلمات مطابقة لهذا البحث.", color = Color(0xFF8490A5))
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(start = 18.dp, end = 18.dp, bottom = 22.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                items(filteredWords, key = { it.word }) { word ->
                    VocabularyCard(
                        word, word.word in progress.favorites,
                        { onSpeak(word.word) }, { onToggleFavorite(word.word) }
                    )
                }
            }
        }
    }
}

@Composable
private fun VocabularyCard(word: VocabularyWord, isFavorite: Boolean, onSpeak: () -> Unit, onToggleFavorite: () -> Unit) {
    Card(shape = RoundedCornerShape(17.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color(0xFFE9EDF5)), elevation = CardDefaults.cardElevation(0.dp)) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(word.word, color = Color(0xFF1D3155), fontSize = 19.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Left)
                    Text(word.pronunciation + "  ·  " + word.type, color = Color(0xFF159078), fontSize = 11.sp)
                    Text(word.meaning, color = Color(0xFF536077), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
                TextButton(onClick = onToggleFavorite) {
                    Icon(Icons.Filled.Favorite, contentDescription = if (isFavorite) "إزالة من المفضلة" else "إضافة للمفضلة", tint = if (isFavorite) Color(0xFFE65B76) else Color(0xFFB9C2D0), modifier = Modifier.size(21.dp))
                }
                OutlinedButton(onClick = onSpeak, contentPadding = PaddingValues(horizontal = 9.dp, vertical = 5.dp)) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "استمع", modifier = Modifier.size(18.dp))
                }
            }
            Surface(color = Color(0xFFF7F9FC), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(9.dp)) {
                    Text(word.example, color = Color(0xFF42516C), fontSize = 12.sp, textAlign = TextAlign.Left)
                    Text(word.exampleArabic, color = Color(0xFF8792A6), fontSize = 11.sp)
                }
            }
        }
    }
}

@Composable
private fun PracticeScreen(progress: LearningProgress, onSpeak: (String) -> Unit, onPracticeCompleted: (Int) -> Unit, onReviewWord: (String, ReviewRating) -> Unit, startWithReview: Boolean) {
    var selectedMode by rememberSaveable { mutableStateOf(if (startWithReview) "review" else "quiz") }
    LaunchedEffect(startWithReview) { if (startWithReview) selectedMode = "review" }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("التدريب الذكي", color = Color(0xFF17223B), fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
        Text("تدرّب على القواعد وراجع الكلمات في الوقت المناسب.", color = Color(0xFF7D889E), fontSize = 13.sp)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = selectedMode == "quiz", onClick = { selectedMode = "quiz" }, label = { Text("اختبار سريع") }, modifier = Modifier.weight(1f))
            FilterChip(selected = selectedMode == "review", onClick = { selectedMode = "review" }, label = { Text("مراجعة الكلمات") }, modifier = Modifier.weight(1f))
        }
        if (selectedMode == "review") ReviewFlashcards(progress, onSpeak, onReviewWord) else QuizPractice(onPracticeCompleted)
    }
}

@Composable
private fun ReviewFlashcards(progress: LearningProgress, onSpeak: (String) -> Unit, onReviewWord: (String, ReviewRating) -> Unit) {
    val today = LocalDate.now().toEpochDay()
    val deck = remember {
        LearningContent.vocabulary.filter { word ->
            val state = progress.reviewCards[word.word]
            state == null || state.dueEpochDay <= today
        }.take(10)
    }
    var index by rememberSaveable { mutableIntStateOf(0) }
    var revealed by rememberSaveable { mutableStateOf(false) }
    val current = deck.getOrNull(index)
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color(0xFFE7ECF4))) {
        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(13.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (current == null) {
                Text(if (deck.isEmpty()) "أحسنت! لا توجد كلمات مستحقة الآن." else "ممتاز! أنهيت مراجعة اليوم.", color = Color(0xFF126B58), fontSize = 19.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                Text(if (deck.isEmpty()) "عد لاحقاً عندما يحين موعد المراجعة التالية." else "كل كلمة راجعتها حُفظ موعد مراجعتها القادم.", color = Color(0xFF758199), fontSize = 13.sp, textAlign = TextAlign.Center)
                Text("📚", fontSize = 42.sp)
            } else {
                Text("مراجعة متباعدة · " + (index + 1) + " / " + deck.size, color = Color(0xFF7D889E), fontSize = 12.sp)
                Box(Modifier.fillMaxWidth().height(7.dp).clip(CircleShape).background(Color(0xFFE6EAF2))) {
                    Box(Modifier.fillMaxWidth((index + 1f) / deck.size).height(7.dp).clip(CircleShape).background(Color(0xFF16A58A)))
                }
                Text(current.category + "  ·  " + current.type, color = Color(0xFF159078), fontSize = 11.sp)
                Text(current.word, color = Color(0xFF1D3155), fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
                OutlinedButton(onClick = { onSpeak(current.word) }) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "استمع", modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("استمع إلى النطق")
                }
                if (revealed) {
                    Surface(color = Color(0xFFF0F6FF), shape = RoundedCornerShape(15.dp), modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(current.meaning, color = Color(0xFF204A9C), fontSize = 19.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                            Text("النطق التقريبي: " + current.pronunciation, color = Color(0xFF64738E), fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                            Text(current.example, color = Color(0xFF34445F), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                            Text(current.exampleArabic, color = Color(0xFF7D889E), fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                            OutlinedButton(onClick = { onSpeak(current.example) }, modifier = Modifier.fillMaxWidth()) { Text("استمع إلى المثال") }
                        }
                    }
                    Text("قيّم مدى تذكّرك للكلمة:", color = Color(0xFF68748C), fontSize = 12.sp)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Button(onClick = { onReviewWord(current.word, ReviewRating.AGAIN); revealed = false; index++ }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE8E8), contentColor = Color(0xFFB34350)), contentPadding = PaddingValues(horizontal = 5.dp, vertical = 10.dp)) { Text("صعبة", fontSize = 12.sp) }
                        Button(onClick = { onReviewWord(current.word, ReviewRating.GOOD); revealed = false; index++ }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F1FF), contentColor = Color(0xFF245BDB)), contentPadding = PaddingValues(horizontal = 5.dp, vertical = 10.dp)) { Text("تذكّرتها", fontSize = 12.sp) }
                        Button(onClick = { onReviewWord(current.word, ReviewRating.EASY); revealed = false; index++ }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE4F7EF), contentColor = Color(0xFF13725D)), contentPadding = PaddingValues(horizontal = 5.dp, vertical = 10.dp)) { Text("سهلة", fontSize = 12.sp) }
                    }
                } else {
                    Button(onClick = { revealed = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) { Text("أظهر المعنى والإجابة") }
                }
            }
        }
    }
    Surface(color = Color(0xFFEAF7F3), shape = RoundedCornerShape(15.dp), modifier = Modifier.fillMaxWidth()) {
        Text("الكلمات التي تجدها صعبة ستعود للمراجعة قريباً، أما الكلمات التي تتذكرها بسهولة فستتباعد مراجعتها تدريجياً.", Modifier.padding(13.dp), color = Color(0xFF416D64), fontSize = 12.sp, lineHeight = 20.sp)
    }
}

@Composable
private fun QuizPractice(onPracticeCompleted: (Int) -> Unit) {
    var questionIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedIndex by rememberSaveable { mutableIntStateOf(-1) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var recorded by rememberSaveable { mutableStateOf(false) }
    val questions = LearningContent.questions
    val finished = questionIndex >= questions.size
    LaunchedEffect(finished) {
        if (finished && !recorded) {
            onPracticeCompleted(score)
            recorded = true
        }
    }
    if (finished) {
        Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF7F3))) {
            Column(Modifier.fillMaxWidth().padding(23.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(11.dp)) {
                Text("🏆", fontSize = 42.sp)
                Text("أحسنت! انتهى التدريب", color = Color(0xFF126B58), fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Text(score.toString() + " من " + questions.size + " إجابات صحيحة", color = Color(0xFF416D64), fontSize = 15.sp)
                Button(onClick = { questionIndex = 0; selectedIndex = -1; score = 0; recorded = false }) { Text("إعادة التدريب") }
            }
        }
    } else {
        val question = questions[questionIndex]
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text("السؤال " + (questionIndex + 1) + " من " + questions.size, color = Color(0xFF76839A), fontSize = 12.sp)
            Text("النقاط: " + score, color = Color(0xFF245BDB), fontWeight = FontWeight.Bold)
        }
        Box(Modifier.fillMaxWidth().height(8.dp).clip(CircleShape).background(Color(0xFFE6EAF2))) {
            Box(Modifier.fillMaxWidth((questionIndex + 1f) / questions.size).height(8.dp).clip(CircleShape).background(Color(0xFF16A58A)))
        }
        Card(shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color(0xFFE7ECF4))) {
            Column(Modifier.fillMaxWidth().padding(19.dp), verticalArrangement = Arrangement.spacedBy(13.dp)) {
                Text("اختر الإجابة الصحيحة", color = Color(0xFF8A95A9), fontSize = 12.sp)
                Text(question.question, color = Color(0xFF1D3155), fontWeight = FontWeight.ExtraBold, fontSize = 21.sp, lineHeight = 30.sp)
                question.options.forEachIndexed { index, option ->
                    val isCorrect = index == question.correctIndex
                    val chosen = selectedIndex == index
                    val bg = when { selectedIndex == -1 -> Color(0xFFF7F9FC); isCorrect -> Color(0xFFE4F7EF); chosen -> Color(0xFFFFE9EB); else -> Color(0xFFF7F9FC) }
                    val border = when { selectedIndex == -1 -> Color(0xFFE5EAF2); isCorrect -> Color(0xFF72C9A8); chosen -> Color(0xFFE78A95); else -> Color(0xFFE5EAF2) }
                    Surface(Modifier.fillMaxWidth().clip(RoundedCornerShape(13.dp)).clickable(enabled = selectedIndex == -1) { selectedIndex = index; if (isCorrect) score++ }, color = bg, shape = RoundedCornerShape(13.dp), border = BorderStroke(1.dp, border)) {
                        Text(option, Modifier.fillMaxWidth().padding(14.dp), color = Color(0xFF35445D), fontWeight = FontWeight.SemiBold)
                    }
                }
                if (selectedIndex != -1) {
                    Surface(color = Color(0xFFF0F7F4), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
                        Text((if (selectedIndex == question.correctIndex) "إجابة صحيحة ✓  " else "ليست الإجابة الصحيحة.  ") + question.explanation, Modifier.padding(12.dp), color = Color(0xFF26735E), fontSize = 12.sp, lineHeight = 20.sp)
                    }
                    Button(onClick = { questionIndex++; selectedIndex = -1 }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) { Text(if (questionIndex == questions.lastIndex) "عرض النتيجة" else "السؤال التالي") }
                }
            }
        }
    }
}

@Composable
private fun ProfileScreen(progress: LearningProgress) {
    val completed = progress.completedLessons.size.coerceAtMost(LearningContent.lessons.size)
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Text("ملف التعلّم", color = Color(0xFF17223B), fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
            Text("تابع تقدّمك واحتفل بكل خطوة.", color = Color(0xFF7D889E), fontSize = 13.sp)
        }
        item {
            Card(shape = RoundedCornerShape(23.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF122B55))) {
                Row(Modifier.fillMaxWidth().padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(58.dp).clip(CircleShape).background(Color(0xFFDAE5FF)), contentAlignment = Alignment.Center) {
                        Text("A", color = Color(0xFF245BDB), fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Spacer(Modifier.width(14.dp))
                    Column {
                        Text("متعلّم الإنجليزية", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                        Text("المستوى الحالي: A1 · مبتدئ", color = Color(0xFFD5E0F8), fontSize = 12.sp)
                    }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatCard("النقاط المكتسبة", progress.xp.toString(), "⭐", Modifier.weight(1f))
                StatCard("أيام الاستمرار", progress.streak.toString(), "🔥", Modifier.weight(1f))
                StatCard("الدروس", completed.toString(), "📘", Modifier.weight(1f))
            }
        }
        item {
            Card(shape = RoundedCornerShape(19.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(1.dp, Color(0xFFE9EDF5))) {
                Column(Modifier.padding(17.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("حول التطبيق", color = Color(0xFF1F304F), fontWeight = FontWeight.ExtraBold)
                    Text("Englishat مساحة تعلّم عربية لممارسة الإنجليزية من خلال الدروس القصيرة والمفردات والاستماع والتدريب.", color = Color(0xFF758199), fontSize = 13.sp, lineHeight = 22.sp)
                    Text("الإصدار التجريبي 0.1.0", color = Color(0xFF9AA3B3), fontSize = 11.sp)
                }
            }
        }
    }
}
