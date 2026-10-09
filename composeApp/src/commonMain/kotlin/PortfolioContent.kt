data class Project(
    val number: String,
    val name: String,
    val domain: String,
    val summary: String,
    val stack: List<String>,
    val status: String = "Case study in progress",
)

data class Experience(
    val title: String,
    val organization: String,
    val period: String,
    val details: String,
)

object PortfolioContent {
    val projects = listOf(
        Project("01", "Digital banking platform", "FINTECH · DIGITAL BANKING", "A modular banking experience built around reliable account, payment, and customer journeys.", listOf("Kotlin", "Compose", "Clean Architecture")),
        Project("02", "Audio & video experiences", "MEDIA · REAL TIME", "Mobile product work across rich playback and communication experiences.", listOf("Android", "Kotlin", "Media")),
        Project("03", "Care, commerce & AI", "HEALTHCARE · COMMERCE · AI", "Product engineering across care delivery, online retail, and AI-powered workflows.", listOf("Kotlin", "MVVM", "MVI")),
    )

    val experience = listOf(
        Experience("Senior Android Engineer", "Professional experience", "9+ years", "Building production mobile applications and shaping maintainable engineering foundations."),
        Experience("Kotlin Multiplatform Specialist", "Cross-platform engineering", "Current focus", "Designing shared Kotlin architecture with platform-aware boundaries and practical adoption paths."),
    )

    val expertise = listOf(
        "Kotlin & Java", "Kotlin Multiplatform", "Jetpack Compose", "MVVM & MVI",
        "Clean Architecture", "Modularization", "Fintech & digital banking", "Audio & video",
        "Healthcare & e-commerce", "AI-powered products",
    )

    val notes = listOf(
        "A practical path to modular Android architecture",
        "Where Kotlin Multiplatform boundaries belong",
        "Compose UI patterns for evolving product teams",
    )
}
