# Ahmed Nawaz · Engineering Portfolio

A mobile inspired engineering portfolio built with Kotlin, Compose Multiplatform, and Kotlin/Wasm. It is a static site with no backend.

For setup, architecture, content editing, deployment, screenshots, and known technical debt, see the [project guide](docs/README.md).
See [CHANGELOG.md](CHANGELOG.md) for verified project updates.

## Current status

The source targets Kotlin/Wasm (`wasmJs`) and Compose Multiplatform. Official Kotlin documentation currently lists the Compose Multiplatform web target as **Beta** and requires browsers with WebAssembly GC support. Validate the experience in current Chrome, Edge, Firefox, and Safari releases before relying on it as the only public portfolio surface.

The repository does not include a Gradle wrapper, so local builds require Gradle 9.4.1 to be available separately. The Wasm production distribution succeeds with JDK 17.0.20.1 and Gradle 9.4.1. Three common-source-set content integrity tests are configured and compile, but local execution currently stops because ChromeHeadless is not installed. GitHub Actions builds the site and is configured to deploy from `main` after GitHub Pages is enabled for Actions in repository settings. The current feature branch has not been pushed.

## Run locally

Install a JDK 17 or later and Gradle 9.4.1, then run:

```sh
gradle :composeApp:wasmJsBrowserDevelopmentRun
```

Build the production site with:

```sh
gradle :composeApp:wasmJsBrowserDistribution
```

The generated static site is in `composeApp/build/dist/wasmJs/productionExecutable`. It can be hosted on GitHub Pages or any static file host. Configure the host's base path if publishing below a project subpath.

## Content editing

Portfolio text and project placeholders live in `composeApp/src/commonMain/kotlin/PortfolioContent.kt`; UI components live in `PortfolioApp.kt`. Replace generic project descriptions with verified case study details, and add only approved public URLs, email, and CV assets. No employer names, dates, outcome metrics, or contact links have been inferred.

## Project layout

```text
composeApp/src/commonMain/kotlin/   Shared portfolio content and Compose UI
composeApp/src/wasmJsMain/          Kotlin/Wasm entry point and web shell
gradle/libs.versions.toml           Centralized Kotlin and Compose versions
.github/workflows/build.yml         Build/test and conditional GitHub Pages deployment
docs/                                Junior-friendly setup, architecture and operations guides
```

## Version references

Versions are pinned to Kotlin 2.4.20 and Compose Multiplatform 1.12.1 based on the current official compatibility documentation at project setup. Check the official [Compose compatibility and versioning guide](https://kotlinlang.org/docs/multiplatform/compose-compatibility-and-versioning.html), [Kotlin Multiplatform compatibility guide](https://kotlinlang.org/docs/multiplatform/multiplatform-compatibility-guide.html), and [Kotlin/Wasm overview](https://kotlinlang.org/docs/wasm-overview.html) when upgrading.
