import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

private val Ink = Color(0xFF0B1010)
private val Panel = Color(0xFF111A19)
private val PanelRaised = Color(0xFF172321)
private val Mint = Color(0xFF9AF0D1)
private val TextPrimary = Color(0xFFE7EFEC)
private val TextMuted = Color(0xFF91A39E)
private val Stroke = Color(0xFF293936)

@Composable
fun PortfolioApp() {
    var selected by remember { mutableStateOf("Home") }
    var entered by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val navigate: (String) -> Unit = { destination ->
        selected = destination
        scope.launch { listState.animateScrollToItem(destinationIndex(destination)) }
    }
    LaunchedEffect(Unit) { entered = true }

    MaterialTheme {
        BoxWithConstraints(Modifier.fillMaxSize().background(Ink)) {
            val wide = maxWidth >= 920.dp
            if (wide) {
                Row(Modifier.fillMaxSize()) {
                    Sidebar(selected, onSelect = navigate)
                    LazyColumn(
                        modifier = Modifier.weight(1f).fillMaxSize(),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                    ) {
                        item { AnimatedVisibility(entered, enter = fadeIn() + slideInVertically { it / 12 }) { HomeSection(onProjects = { navigate("Projects") }, onContact = { navigate("Contact") }) } }
                        item { ProjectsSection() }
                        item { ArchitectureSection() }
                        item { AboutSection() }
                        item { ExpertiseSection() }
                        item { NotesSection() }
                        item { ContactSection() }
                    }
                }
            } else {
                Column(Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                    ) {
                        item { AnimatedVisibility(entered, enter = fadeIn() + slideInVertically { it / 12 }) { HomeSection(onProjects = { navigate("Projects") }, onContact = { navigate("Contact") }) } }
                        item { ProjectsSection() }
                        item { ArchitectureSection() }
                        item { AboutSection() }
                        item { ExpertiseSection() }
                        item { NotesSection() }
                        item { ContactSection() }
                    }
                    BottomNavigation(selected, onSelect = navigate)
                }
            }
        }
    }
}

private val destinations = listOf("Home", "Projects", "About", "Expertise", "Lab", "Notes", "Contact")

private fun destinationIndex(destination: String) = when (destination) {
    "Projects" -> 1
    "Lab" -> 2
    "About" -> 3
    "Expertise" -> 4
    "Notes" -> 5
    "Contact" -> 6
    else -> 0
}

@Composable
private fun Sidebar(selected: String, onSelect: (String) -> Unit) {
    Column(
        Modifier.width(228.dp).fillMaxSize().background(Color(0xFF0E1514)).border(1.dp, Stroke)
            .padding(horizontal = 22.dp, vertical = 30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            BrandMark()
            Spacer(Modifier.height(52.dp))
            destinations.forEach { Destination(it, selected == it, onSelect) }
        }
        Column {
            StatusBadge()
            Spacer(Modifier.height(14.dp))
            Text("© 2026 Ahmed Nawaz", color = TextMuted, fontSize = 11.sp)
        }
    }
}

@Composable
private fun BrandMark() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(11.dp)) {
        Box(Modifier.size(34.dp).background(Mint, RoundedCornerShape(11.dp)), contentAlignment = Alignment.Center) {
            Text("AN", color = Ink, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
        Column {
            Text("AHMED NAWAZ", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.3.sp)
            Text("ENGINEER · BUILDER", color = TextMuted, fontSize = 9.sp, letterSpacing = 1.1.sp)
        }
    }
}

@Composable
private fun Destination(label: String, active: Boolean, onSelect: (String) -> Unit) {
    Row(
        Modifier.fillMaxWidth().padding(vertical = 3.dp).background(if (active) PanelRaised else Color.Transparent, RoundedCornerShape(12.dp))
            .clickable { onSelect(label) }.padding(horizontal = 13.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.size(6.dp).background(if (active) Mint else Color(0xFF3D514C), CircleShape))
        Spacer(Modifier.width(12.dp))
        Text(label, color = if (active) TextPrimary else TextMuted, fontSize = 13.sp, fontWeight = if (active) FontWeight.Medium else FontWeight.Normal)
    }
}

@Composable
private fun BottomNavigation(selected: String, onSelect: (String) -> Unit) {
    Row(
        Modifier.fillMaxWidth().background(Color(0xF20E1514)).windowInsetsPadding(WindowInsets.navigationBars)
            .border(BorderStroke(1.dp, Stroke)).padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        listOf("Home", "Projects", "Lab", "About", "Contact").forEach { label ->
            Column(Modifier.clickable { onSelect(label) }.padding(horizontal = 8.dp, vertical = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.size(5.dp).background(if (selected == label) Mint else Color.Transparent, CircleShape))
                Spacer(Modifier.height(4.dp))
                Text(label, color = if (selected == label) Mint else TextMuted, fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun HomeSection(onProjects: () -> Unit, onContact: () -> Unit) {
    BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 36.dp)) {
        val wide = maxWidth >= 680.dp
        val metricSpacing = if (maxWidth < 360.dp) 4.dp else 28.dp
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(28.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(if (wide) 1f else 1f)) {
                Eyebrow("SENIOR ANDROID ENGINEER · KOTLIN MULTIPLATFORM")
                Spacer(Modifier.height(20.dp))
                Text("I build mobile\nproducts that\nfeel effortless.", color = TextPrimary, fontSize = if (wide) 56.sp else 42.sp, lineHeight = if (wide) 62.sp else 49.sp, fontWeight = FontWeight.SemiBold, letterSpacing = (-2).sp)
                Spacer(Modifier.height(19.dp))
                Text("9+ years turning complex product challenges into thoughtful, reliable software. Focused on Kotlin, Android, and shared multiplatform foundations.", color = TextMuted, fontSize = 15.sp, lineHeight = 24.sp)
                Spacer(Modifier.height(25.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Pill("Explore selected work", filled = true, onClick = onProjects)
                    Pill("Get in touch", filled = false, onClick = onContact)
                }
                Spacer(Modifier.height(34.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(metricSpacing)) {
                    Metric("09+", "YEARS BUILDING")
                    Metric("KMP", "SHARED SYSTEMS")
                    Metric("10", "CORE DISCIPLINES")
                }
            }
            if (wide) ArchitecturePreview(Modifier.width(310.dp).height(286.dp))
        }
    }
}

@Composable
private fun Metric(value: String, label: String) {
    Column {
        Text(value, color = Mint, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(3.dp))
        Text(label, color = TextMuted, fontSize = 9.sp, letterSpacing = 1.sp)
    }
}

@Composable
private fun Pill(label: String, filled: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(100.dp),
        color = if (filled) Mint else Panel,
        border = if (filled) null else BorderStroke(1.dp, Stroke),
    ) {
        Text(label, color = if (filled) Ink else TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 16.dp, vertical = 11.dp))
    }
}

@Composable
private fun SectionHeader(kicker: String, title: String, detail: String? = null) {
    Eyebrow(kicker)
    Spacer(Modifier.height(9.dp))
    Text(title, color = TextPrimary, fontSize = 29.sp, fontWeight = FontWeight.SemiBold, letterSpacing = (-0.7).sp)
    if (detail != null) {
        Spacer(Modifier.height(8.dp))
        Text(detail, color = TextMuted, fontSize = 14.sp, lineHeight = 22.sp)
    }
}

@Composable
private fun Eyebrow(text: String) {
    Text(text, color = Mint, fontSize = 10.sp, letterSpacing = 1.55.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun ProjectsSection() {
    ContentSection("PROJECTS", "Selected work", "Representative product domains. Detailed project facts and public links can be added when ready.") {
        BoxWithConstraints {
            val columns = if (maxWidth >= 760.dp) 3 else 1
            if (columns == 3) {
                Row(horizontalArrangement = Arrangement.spacedBy(13.dp)) {
                    PortfolioContent.projects.forEach { ProjectCard(it, Modifier.weight(1f)) }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    PortfolioContent.projects.forEach { ProjectCard(it, Modifier.fillMaxWidth()) }
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(project: Project, modifier: Modifier = Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(20.dp)).border(1.dp, Stroke, RoundedCornerShape(20.dp)).padding(19.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(project.number, color = Mint, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("↗", color = TextMuted, fontSize = 15.sp)
        }
        Spacer(Modifier.height(26.dp))
        Eyebrow(project.domain)
        Spacer(Modifier.height(9.dp))
        Text(project.name, color = TextPrimary, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(9.dp))
        Text(project.summary, color = TextMuted, fontSize = 12.sp, lineHeight = 19.sp)
        Spacer(Modifier.height(17.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) { project.stack.forEach { TinyTag(it) } }
        Spacer(Modifier.height(17.dp))
        Text(project.status, color = TextMuted, fontSize = 10.sp)
    }
}

@Composable
private fun ArchitectureSection() {
    ContentSection("KOTLIN MULTIPLATFORM · ARCHITECTURE LAB", "Shared by design. Native by nature.", "An interactive model of a practical app boundary: share domain rules and state, keep platform services and product UI adaptable.") {
        ArchitectureDiagram()
    }
}

@Composable
private fun ArchitecturePreview(modifier: Modifier = Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(24.dp)).border(1.dp, Stroke, RoundedCornerShape(24.dp)).padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Eyebrow("SYSTEM OVERVIEW")
            Text("● LIVE MODEL", color = Mint, fontSize = 8.sp, letterSpacing = 1.sp)
        }
        Spacer(Modifier.height(16.dp))
        ArchitectureNodes(compact = true)
    }
}

@Composable
private fun ArchitectureDiagram() {
    Column(Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(20.dp)).border(1.dp, Stroke, RoundedCornerShape(20.dp)).padding(17.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("PRODUCT SURFACES", color = TextMuted, fontSize = 9.sp, letterSpacing = 1.2.sp)
            Text("SHARED CORE", color = Mint, fontSize = 9.sp, letterSpacing = 1.2.sp)
        }
        Spacer(Modifier.height(14.dp))
        ArchitectureNodes(compact = false)
        Spacer(Modifier.height(14.dp))
        Text("Tap a layer to explore its responsibility", color = TextMuted, fontSize = 11.sp)
    }
}

@Composable
private fun ArchitectureNodes(compact: Boolean) {
    var selectedLayer by remember { mutableStateOf("Domain") }
    val layers = listOf("Presentation", "Domain", "Data")
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        if (!compact) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Android UI", "iOS UI", "Web UI").forEach { LayerChip(it, false, Modifier.weight(1f)) }
            }
            Connector()
        }
        Column(Modifier.fillMaxWidth().background(PanelRaised, RoundedCornerShape(14.dp)).padding(10.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
            layers.forEach { layer ->
                LayerChip("$layer  ·  ${layerDescription(layer)}", selectedLayer == layer, Modifier.fillMaxWidth().clickable { selectedLayer = layer })
            }
        }
        if (!compact) {
            Connector()
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Platform APIs", "Remote services", "Local storage").forEach { LayerChip(it, false, Modifier.weight(1f)) }
            }
            Spacer(Modifier.height(2.dp))
            Text(when (selectedLayer) {
                "Presentation" -> "State holders coordinate UI intent and renderable state."
                "Data" -> "Repositories hide source selection and implementation detail."
                else -> "Business rules stay independent of UI and platform frameworks."
            }, color = Mint, fontSize = 12.sp, lineHeight = 18.sp)
        }
    }
}

private fun layerDescription(layer: String) = when (layer) {
    "Presentation" -> "state & intent"
    "Data" -> "repositories"
    else -> "business rules"
}

@Composable
private fun Connector() {
    Box(Modifier.fillMaxWidth().height(8.dp).padding(start = 20.dp), contentAlignment = Alignment.CenterStart) {
        Box(Modifier.width(1.dp).height(8.dp).background(Color(0xFF536B64)))
    }
}

@Composable
private fun LayerChip(label: String, active: Boolean, modifier: Modifier = Modifier) {
    Box(modifier.background(if (active) Color(0xFF233A34) else Color(0xFF131D1B), RoundedCornerShape(9.dp)).border(1.dp, if (active) Color(0xFF507B6C) else Stroke, RoundedCornerShape(9.dp)).padding(horizontal = 10.dp, vertical = 9.dp)) {
        Text(label, color = if (active) Mint else TextPrimary, fontSize = 10.sp, textAlign = TextAlign.Center, lineHeight = 14.sp)
    }
}

@Composable
private fun AboutSection() {
    ContentSection("ABOUT & EXPERIENCE", "Thoughtful engineering, from first commit to long-term care.", "I work across product and platform concerns: shaping clear boundaries, shipping useful interfaces, and helping codebases stay adaptable as products grow.") {
        PortfolioContent.experience.forEach { item ->
            Row(Modifier.fillMaxWidth().padding(vertical = 11.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                Box(Modifier.padding(top = 5.dp).size(8.dp).background(Mint, CircleShape))
                Column(Modifier.weight(1f)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(item.title, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        Text(item.period, color = Mint, fontSize = 10.sp)
                    }
                    Spacer(Modifier.height(3.dp))
                    Text(item.organization, color = TextMuted, fontSize = 11.sp)
                    Spacer(Modifier.height(5.dp))
                    Text(item.details, color = TextMuted, fontSize = 12.sp, lineHeight = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun ExpertiseSection() {
    ContentSection("TECHNICAL EXPERTISE", "A toolkit for products that matter.", "") {
        BoxWithConstraints {
            val columns = if (maxWidth >= 760.dp) 2 else 1
            if (columns == 2) {
                PortfolioContent.expertise.chunked(5).forEach { group ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        group.forEach { ExpertiseItem(it, Modifier.weight(1f)) }
                    }
                    Spacer(Modifier.height(8.dp))
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    PortfolioContent.expertise.forEach { ExpertiseItem(it, Modifier.fillMaxWidth()) }
                }
            }
        }
    }
}

@Composable
private fun ExpertiseItem(label: String, modifier: Modifier = Modifier) {
    Row(modifier.padding(vertical = 4.dp).background(Panel, RoundedCornerShape(12.dp)).border(1.dp, Stroke, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 11.dp), verticalAlignment = Alignment.CenterVertically) {
        Text("✳", color = Mint, fontSize = 11.sp)
        Spacer(Modifier.width(8.dp))
        Text(label, color = TextPrimary, fontSize = 11.sp)
    }
}

@Composable
private fun NotesSection() {
    ContentSection("ENGINEERING NOTES", "Ideas, patterns, and field notes.", "Draft topics to publish as articles when ready.") {
        PortfolioContent.notes.forEachIndexed { index, note ->
            Row(Modifier.fillMaxWidth().padding(vertical = 8.dp).background(Panel, RoundedCornerShape(13.dp)).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("0${index + 1}", color = Mint, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(13.dp))
                Text(note, color = TextPrimary, fontSize = 12.sp, modifier = Modifier.weight(1f))
                Text("↗", color = TextMuted, fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun ContactSection() {
    ContentSection("CONTACT", "Let’s build something useful.", "For collaboration, product engineering, or a thoughtful conversation about Kotlin Multiplatform.") {
        Column(Modifier.fillMaxWidth().background(PanelRaised, RoundedCornerShape(20.dp)).padding(20.dp)) {
            Text("Add your preferred email and professional links here.", color = TextPrimary, fontSize = 13.sp)
            Spacer(Modifier.height(8.dp))
            Text("CV download placeholder · Add an approved PDF to the static assets.", color = TextMuted, fontSize = 11.sp, lineHeight = 18.sp)
        }
    }
}

@Composable
private fun StatusBadge() {
    Row(Modifier.background(Panel, RoundedCornerShape(100.dp)).padding(horizontal = 10.dp, vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(6.dp).background(Mint, CircleShape))
        Spacer(Modifier.width(7.dp))
        Text("KOTLIN MULTIPLATFORM", color = TextMuted, fontSize = 8.sp, letterSpacing = .7.sp)
    }
}

@Composable
private fun TinyTag(label: String) {
    Box(Modifier.background(Color(0xFF1B2926), RoundedCornerShape(6.dp)).padding(horizontal = 7.dp, vertical = 5.dp)) {
        Text(label, color = TextMuted, fontSize = 8.sp)
    }
}

@Composable
private fun ContentSection(kicker: String, title: String, detail: String, content: @Composable () -> Unit) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp)) {
        SectionHeader(kicker, title, detail.takeIf { it.isNotBlank() })
        Spacer(Modifier.height(18.dp))
        content()
    }
}
