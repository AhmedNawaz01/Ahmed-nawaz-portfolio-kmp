# Changelog

## 2026-10-09

- Verified the production Kotlin/Wasm distribution task with JDK 17.0.20.1 and Gradle 9.4.1. Output: `composeApp/build/dist/wasmJs/productionExecutable/`.
- Added a Gradle 9.4.1 wrapper and changed GitHub Actions to use `./gradlew`.
- Added three common-source-set content integrity tests; `:composeApp:wasmJsTest` executed successfully with temporary Playwright ChromeHeadless.
- Browser QA found and fixed a collapsed mobile viewport and overlapping project/expertise lists. The combined Wasm test and production build then succeeded.
- Captured seven genuine local production-build screenshots at 390×844 and 1440×1000 in Playwright Chromium 156.0.8078.4. See `docs/assets/screenshots/` and `docs/SCREENSHOTS.md`.
- Expanded junior-friendly project, architecture, design, content, testing, deployment, contribution, and debt documentation.
- Pushed `feature/compose-wasm-portfolio` at `e1ef147` to the verified repository. Pages is enabled in workflow mode. CI is queued; the pull request and `main` deployment are still pending. No live URL is yet verified.
