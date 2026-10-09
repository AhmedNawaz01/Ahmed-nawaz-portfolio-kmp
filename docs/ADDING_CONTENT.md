# Adding portfolio content

## Edit data safely

Open `composeApp/src/commonMain/kotlin/PortfolioContent.kt`. The lists are plain Kotlin values:

```kotlin
Project("04", "Approved product name", "DOMAIN", "Publicly reviewed summary", listOf("Kotlin", "Compose"))
```

Only use the example shape; replace every example value with approved, accurate information. Keep project numbers unique. `Project.status` defaults to “Case study in progress”. Project cards do not currently open detailed case studies.

- `projects`: title, domain, summary, stack and status.
- `experience`: role, organization, period and details. The current organization/period values are generic placeholders.
- `expertise`: skill labels.
- `notes`: draft topics; publish as articles only when the article exists.

Do not add employer/client names, dates, results, metrics, URLs, or customer information without confirmation and public-use approval.

## Intro, links, CV, and screenshots

The introductory copy and calls to action are in `HomeSection` in `PortfolioApp.kt`. Contact and CV are explicitly placeholders; no profile URLs or PDF have been supplied. Once approved details exist, model them as content, render real links with accessibility semantics, place the CV in `wasmJsMain/resources`, then build and confirm the files exist in the production output.

Screenshots must come from the running browser. Store them in `docs/assets/screenshots/` (or keep the documented index path consistent), use descriptive names, and record viewport, date, and local/production source. See [screenshots](SCREENSHOTS.md).

## When adding a destination or component

The app uses one ordered `LazyColumn`. For a new destination, add the label to `destinations`, add its list index to `destinationIndex`, expose it in the relevant navigation control, and keep the matching section in the same list position. A new reusable composable belongs near related UI in `PortfolioApp.kt`. Update tests and architecture docs when behavior changes.

## Verify the edit

Run `./gradlew :composeApp:wasmJsTest` and `./gradlew :composeApp:wasmJsBrowserDistribution`, then inspect the screen at mobile and desktop widths. Test execution requires a compatible headless browser; compilation alone does not prove the assertions ran.
