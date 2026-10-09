# Testing and quality checks

## Automated tests

`composeApp/src/commonTest/kotlin/PortfolioContentTest.kt` contains three common tests:

1. Project list is nonempty, project numbers are unique, and card text/tags are present.
2. Experience entries have nonblank title, organization, period, and details.
3. Expertise and engineering-note lists contain displayable values.

Run all current Wasm tests with:

```sh
./gradlew :composeApp:wasmJsTest
```

This target uses the Gradle Kotlin/Wasm browser test runner and requires ChromeHeadless locally. A successful Gradle `compileTestKotlinWasmJs` task is only compilation evidence; the test task must discover and execute the tests for assertions to count. Latest results are in [development verification](DEVELOPMENT.md#verified-command-and-result).

## Build checks

```sh
./gradlew :composeApp:wasmJsBrowserDistribution
git diff --check
```

The first command verifies optimized web compilation and writes the static output to `composeApp/build/dist/wasmJs/productionExecutable/`. The second detects whitespace errors in the current Git diff.

## Browser/manual review

Open the running app in current Chrome, Edge, Firefox, and Safari with Wasm GC support. Review mobile and desktop widths, navigation scroll targets, architecture-layer text changes, keyboard/screen-reader semantics, long labels, console errors, and asset requests. The architecture diagram is illustrative; it is not evidence of actual native platform targets.

## Current evidence and gaps

- `./gradlew --no-daemon :composeApp:wasmJsTest :composeApp:wasmJsBrowserDistribution` completed with `BUILD SUCCESSFUL` on 2026-10-09 after a temporary Playwright ChromeHeadless binary was installed under `/private/tmp`; `wasmJsBrowserTest` ran and Gradle discovered the common tests.
- Playwright opened the locally served production distribution at `http://127.0.0.1:4173/`. HTML, JS, both Wasm files, fonts, and favicon loaded; the final screenshot run recorded no page errors or failed requests.
- A 320×780 narrow-width smoke check reported document width 320 px, no horizontal overflow, and no page errors after responsive metrics spacing was corrected.
- Genuine mobile (390×844) and desktop (1440×1000) screenshots are in `docs/assets/screenshots/` and document local build status only.
- Accessibility semantics, keyboard/screen-reader behavior, direct-route behavior (there are no routes), external links (none approved), and target-device startup performance still require additional review. A detailed case-study screen and CV are not implemented.
