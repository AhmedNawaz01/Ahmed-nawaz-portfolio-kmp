# Development setup

## Required tools

| Tool | Version used for verification | Why it is needed |
| --- | --- | --- |
| JDK | 17.0.20.1 | Runs Gradle and the Kotlin compiler |
| Gradle | 9.4.1 | Evaluates the Kotlin DSL and runs build tasks |
| Node.js | 24.21.0 on the local machine | Kotlin/Wasm's web packaging uses the Node ecosystem; the Kotlin Gradle plugin can also provision its configured Node runtime |
| npm | 11.19.0 on the local machine | Supports the web package tooling |

Gradle 9 requires JDK 17 or newer. The project does **not** contain `gradlew`, `gradlew.bat`, or the wrapper JAR, so a local build needs Gradle 9.4.1 available on `PATH`. GitHub Actions downloads the pinned Gradle version with `gradle/actions/setup-gradle` and does not need a global Gradle installation.

Kotlin 2.4.20 supports Gradle versions through 9.7.0. Compose Multiplatform 1.12.1 is paired with Kotlin 2.4.20 here; its Compose compiler plugin is intentionally pinned to that same Kotlin version. Compose Multiplatform 1.12.1 publishes its Material3 artifact at `1.12.0-alpha03`, which is tracked separately in the version catalog.

## Check your environment

```sh
java -version
gradle --version
node --version
npm --version
```

Use JDK 17 or later, and use Gradle 9.4.1 for the verified configuration. A Kotlin/Wasm production compile can require more heap than Gradle's defaults, so `gradle.properties` assigns 3 GB to the Kotlin daemon. If the machine cannot provide that memory, lower the value only after checking that the build still completes.

## Run the app locally

From the repository root:

```sh
gradle :composeApp:wasmJsBrowserDevelopmentRun
```

Gradle prints the local URL after starting the development server. Open it in a current browser with WebAssembly GC support. Compose Multiplatform's Wasm web target is Beta, so check the supported browser matrix before treating this as the only way to access the portfolio.

## Build and test

Run the configured Wasm tests (the default browser runner needs ChromeHeadless):

```sh
gradle :composeApp:wasmJsTest
```

Build a production site:

```sh
gradle :composeApp:wasmJsBrowserDistribution
```

The generated static files are written to:

```text
composeApp/build/dist/wasmJs/productionExecutable/
```

The CI workflow currently runs both tasks in sequence:

```sh
gradle :composeApp:wasmJsTest :composeApp:wasmJsBrowserDistribution
```

Three common-source-set content integrity tests live in `composeApp/src/commonTest/kotlin/PortfolioContentTest.kt`. They check that project cards have unique numbers and displayable copy, experience entries have required labels, and expertise/engineering notes are renderable. The test Kotlin source compiled successfully in this environment, but the browser test task did not complete: Gradle's Karma runner could not find `/Applications/Google Chrome.app/Contents/MacOS/Google Chrome`. No test assertions have been reported as passing. Install or point the runner at a compatible Chrome executable (`CHROME_BIN`) before expecting local browser tests to run. Kotlin's official [JavaScript test runner guide](https://kotlinlang.org/docs/js-running-tests.html) documents the browser runner configuration.

## Verified command and result

The production build verification used JDK 17.0.20.1 and a Gradle 9.4.1 distribution unpacked under `/private/tmp` (not installed system-wide):

```sh
env JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home \
  GRADLE_USER_HOME=/private/tmp/portfolio-gradle-home \
  /private/tmp/gradle-9.4.1/bin/gradle \
  :composeApp:wasmJsBrowserDistribution :composeApp:wasmJsTest
```

The independently run production distribution command completed with `BUILD SUCCESSFUL` on 2026-10-09. It emitted Webpack size recommendations for the 513 KiB JS bundle and 2.24 MiB and 8.24 MiB Wasm assets. Those are performance observations, not compilation failures. The actual test command was:

```sh
env JAVA_HOME=/opt/homebrew/opt/openjdk@17 \
  GRADLE_USER_HOME=/private/tmp/portfolio-gradle-home \
  /private/tmp/gradle-9.4.1/bin/gradle :composeApp:wasmJsTest
```

It failed at `wasmJsBrowserTest`: ChromeHeadless was missing at `/Applications/Google Chrome.app/Contents/MacOS/Google Chrome`, followed by “no tests to execute” because the browser never launched. `compileTestKotlinWasmJs` completed, but no test assertions ran. A compatible Chrome binary and `CHROME_BIN` (if it is outside the default location) are required to finish local test execution.

## Build troubleshooting

### “repository … was added by unknown code”

Kotlin/Wasm provisions Node.js and Binaryen through Gradle repositories. The project uses `RepositoriesMode.PREFER_PROJECT` and declares Google Maven and Maven Central project repositories to allow those tool distributions to resolve. Keep these declarations together if the repository layout changes.

### Kotlin daemon runs out of heap

The project sets `kotlin.daemon.jvmargs=-Xmx3g`. Confirm the host has enough available memory and that Gradle reads the repository's `gradle.properties`. Avoid repeatedly rerunning an optimized Wasm compile with the default 512 MB Kotlin daemon heap.

### Browser loads a blank page

Check the browser console and network panel. `index.html` loads `portfolio.js` and the generated Wasm files by relative path, which supports a GitHub Pages project subpath. A browser without required WasmGC support cannot run this build.
