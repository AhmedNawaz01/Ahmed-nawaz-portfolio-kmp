# Project structure

This repository has one Gradle module, `composeApp`; Kotlin Multiplatform source sets divide shared code from browser-specific startup and assets.

```text
.
├── .github/workflows/build.yml
├── composeApp/
│   ├── build.gradle.kts
│   └── src/
│       ├── commonMain/kotlin/
│       │   ├── PortfolioApp.kt
│       │   └── PortfolioContent.kt
│       ├── commonTest/kotlin/PortfolioContentTest.kt
│       └── wasmJsMain/
│           ├── kotlin/Main.kt
│           └── resources/{index.html,favicon.svg}
├── docs/
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/{gradle-wrapper.jar,gradle-wrapper.properties}
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── gradlew
```

| Path | What belongs here |
| --- | --- |
| `PortfolioContent.kt` | Plain models and editable project, experience, expertise, and note lists. Avoid composables and unverified claims. |
| `PortfolioApp.kt` | Compose screens/components, design tokens, local navigation and architecture diagram state. Avoid secrets or network-backed content. |
| `Main.kt` | Browser entry point: creates `ComposeViewport` and starts `PortfolioApp`. |
| `resources/index.html` | Document metadata, fonts, loading text, and script host. |
| `commonTest/PortfolioContentTest.kt` | Invariants of shared content models. |
| `composeApp/build.gradle.kts` | Wasm target, source-set dependencies, browser webpack output naming. |
| `gradle/libs.versions.toml` | Kotlin, Compose, and library versions. |
| `settings.gradle.kts` | Plugin/dependency repositories and included module. |
| `.github/workflows/build.yml` | Wrapper-based test/build and gated GitHub Pages deployment. |

No Android/iOS targets, server module, article system, or separate case-study module exists.
