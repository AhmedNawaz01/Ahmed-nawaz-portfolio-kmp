# Development setup

## Required tools

| Tool | Version used for verification | Why it is needed |
| --- | --- | --- |
| JDK | 17.0.20.1 | Runs Gradle and the Kotlin compiler |
| Gradle | Wrapper 9.4.1 | Evaluates the Kotlin DSL and runs build tasks |
| Node.js | 24.21.0 on the local machine | Kotlin/Wasm's web packaging uses the Node ecosystem; the Kotlin Gradle plugin can also provision its configured Node runtime |
| npm | 11.19.0 on the local machine | Supports the web package tooling |

Gradle 9 requires JDK 17 or newer. The repository includes `gradlew`, `gradlew.bat`, and the wrapper JAR pinned to Gradle 9.4.1. The wrapper downloads its distribution on first run; no system Gradle installation is required. GitHub Actions uses the same wrapper.

Kotlin 2.4.20 fully supports Gradle versions through 9.7.0. The repository uses Gradle 9.4.1, which builds successfully but receives an out-of-date annotation from the current GitHub Actions runner. Upgrade only within Kotlin's documented supported range, then run local tests/build and CI. Compose Multiplatform 1.12.1 is paired with Kotlin 2.4.20 here; its Compose compiler plugin is intentionally pinned to that same Kotlin version. Compose Multiplatform 1.12.1 publishes its Material3 artifact at `1.12.0-alpha03`, which is tracked separately in the version catalog.

## Check your environment

```sh
java -version
./gradlew --version
node --version
```

Use JDK 17 or later. A Kotlin/Wasm production compile can require more heap than Gradle's defaults, so `gradle.properties` assigns 3 GB to the Kotlin daemon. If the machine cannot provide that memory, lower the value only after checking that the build still completes.

## Run the app locally

From the repository root:

```sh
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

Gradle prints the local URL after starting the development server. Open it in a current browser with WebAssembly GC support. Compose Multiplatform's Wasm web target is Beta, so check the supported browser matrix before treating this as the only way to access the portfolio.

## Build and test

Run the configured Wasm tests (the default browser runner needs ChromeHeadless):

```sh
./gradlew :composeApp:wasmJsTest
```

Build a production site:

```sh
./gradlew :composeApp:wasmJsBrowserDistribution
```

The generated static files are written to:

```text
composeApp/build/dist/wasmJs/productionExecutable/
```

The CI workflow currently runs both tasks in sequence:

```sh
./gradlew :composeApp:wasmJsTest :composeApp:wasmJsBrowserDistribution
```

Three common-source-set content integrity tests live in `composeApp/src/commonTest/kotlin/PortfolioContentTest.kt`. They verify unique/displayable project entries, required experience labels, and nonempty expertise/notes. They were executed successfully with the wrapper and a temporary Playwright ChromeHeadless binary via `CHROME_BIN`; see [testing results](TESTING.md). Kotlin's official [JavaScript test runner guide](https://kotlinlang.org/docs/js-running-tests.html) describes browser configuration.

## Verified command and result

The wrapper verification used JDK 17.0.20.1; it downloaded Gradle 9.4.1 to the configured cache:

```sh
JAVA_HOME=/opt/homebrew/opt/openjdk@17 \
  GRADLE_USER_HOME=/private/tmp/portfolio-gradle-home \
  ./gradlew --no-daemon :composeApp:wasmJsTest
```

Result: `BUILD SUCCESSFUL`; all three configured tests were discovered and the `wasmJsBrowserTest` task completed. Playwright Chromium was downloaded to `/private/tmp`, not installed system-wide.

For this machine, the exact browser-backed command included these temporary paths:

```sh
CHROME_BIN=/private/tmp/portfolio-browser-browsers/chromium_headless_shell-1248/chrome-headless-shell-mac-arm64/chrome-headless-shell \
JAVA_HOME=/opt/homebrew/opt/openjdk@17 \
GRADLE_USER_HOME=/private/tmp/portfolio-gradle-home \
./gradlew --no-daemon :composeApp:wasmJsTest
```

On another machine, use an installed Chrome/ChromeHeadless executable at the runner's default path or set `CHROME_BIN` to that executable.

```sh
JAVA_HOME=/opt/homebrew/opt/openjdk@17 \
  GRADLE_USER_HOME=/private/tmp/portfolio-gradle-home \
  ./gradlew --no-daemon :composeApp:wasmJsBrowserDistribution
```

Result: `BUILD SUCCESSFUL` after the viewport and mobile-layout fixes. Webpack emitted size recommendations for the 513 KiB JS and 2.24 MiB/8.24 MiB Wasm assets; these are performance notices, not build failures.

## Build troubleshooting

### “repository … was added by unknown code”

Kotlin/Wasm provisions Node.js and Binaryen through Gradle repositories. The project uses `RepositoriesMode.PREFER_PROJECT` and declares Google Maven and Maven Central project repositories to allow those tool distributions to resolve. Keep these declarations together if the repository layout changes.

### Kotlin daemon runs out of heap

The project sets `kotlin.daemon.jvmargs=-Xmx3g`. Confirm the host has enough available memory and that Gradle reads the repository's `gradle.properties`. Avoid repeatedly rerunning an optimized Wasm compile with the default 512 MB Kotlin daemon heap.

### Browser loads a blank page

Check the browser console and network panel. `index.html` loads `portfolio.js` and the generated Wasm files by relative path, which supports a GitHub Pages project subpath. A browser without required WasmGC support cannot run this build.
