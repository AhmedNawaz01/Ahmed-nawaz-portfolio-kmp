# Application architecture

## At a glance

The repository contains one Kotlin Multiplatform module, `composeApp`, with a browser target. It shares UI and portfolio content through `commonMain`; the web-specific source set only supplies the browser entry point and static HTML assets.

```text
Browser
└── wasmJsMain
    ├── Main.kt                 Calls ComposeViewport { PortfolioApp() }
    └── resources               index.html and favicon.svg
        ↓
commonMain
├── PortfolioApp.kt             Layout, navigation, reusable UI and color tokens
└── PortfolioContent.kt         Projects, experience, expertise and notes
```

## Build configuration

- `settings.gradle.kts` defines plugin and dependency repositories and includes `:composeApp`.
- Root `build.gradle.kts` declares plugin aliases and shared Maven repositories.
- `gradle/libs.versions.toml` keeps Kotlin, Compose, and Material3 versions in one place.
- `composeApp/build.gradle.kts` configures `wasmJs`, its browser distribution, and the Compose dependencies.
- `gradle.properties` gives the Kotlin compiler daemon enough heap for production Wasm optimization.

The app does not have Android, iOS, desktop, or server targets yet. Adding one should be a deliberate product decision: it changes build configuration, source-set ownership, and platform testing needs.

## UI organization

`PortfolioApp.kt` owns the presentation layer:

- `PortfolioApp` chooses a desktop side rail or mobile bottom bar based on available width and holds the active section state.
- Section composables group the Home, Projects, Architecture Lab, About, Expertise, Notes, and Contact content.
- Small composables such as `Eyebrow`, `Pill`, `TinyTag`, and `LayerChip` keep repeated visual patterns consistent.
- The architecture diagram is an interactive teaching model. Selecting Presentation, Domain, or Data changes the explanatory text.
- Color values are private file-level tokens near the top of the file.

The current navigation scrolls to entries in one `LazyColumn`. Its item order and `destinationIndex` mapping must stay aligned when adding or reordering sections.

## Content boundary

`PortfolioContent.kt` holds plain Kotlin data models and lists. It deliberately avoids project-specific client names, dates, results, metrics, and external links that Ahmed has not supplied. Replace generic summaries with reviewed facts before publishing a case study.

Some contact and CV placeholder copy remains in `PortfolioApp.kt` because it is presentation guidance. Before adding real contact links or a CV, move those values into the content layer and add the approved static file under `composeApp/src/wasmJsMain/resources`.

## Runtime and data flow

The app is entirely static. It has no backend calls, persistence, analytics, or authentication. Compose creates the viewport, reads local content objects, and renders the responsive sections. Navigation and the architecture lab use local Compose state.

## Web platform considerations

Compose Multiplatform's Wasm web target is Beta. Users need a browser with WasmGC support. The current build emits a JS bundle and Wasm assets; the production output also includes Skiko runtime Wasm files. The verified build reports size warnings, so measure on actual devices before adding heavier dependencies.

Sources: [Compose Multiplatform compatibility](https://kotlinlang.org/docs/multiplatform/compose-compatibility-and-versioning.html), [Kotlin/Wasm overview](https://kotlinlang.org/docs/wasm-overview.html).
