# Changelog

## 2026-10-09

- Verified the production Kotlin/Wasm distribution task with JDK 17.0.20.1 and Gradle 9.4.1. Output: `composeApp/build/dist/wasmJs/productionExecutable/`.
- Added a Gradle 9.4.1 wrapper and changed GitHub Actions to use `./gradlew`.
- Added three common-source-set content integrity tests; `:composeApp:wasmJsTest` executed successfully with temporary Playwright ChromeHeadless.
- Browser QA found and fixed a collapsed mobile viewport and overlapping project/expertise lists. The combined Wasm test and production build then succeeded.
- Updated workflow actions to their latest stable official releases after the first hosted CI run reported Node 20 deprecation notices.
- Captured seven genuine local production-build screenshots at 390×844 and 1440×1000 in Playwright Chromium 156.0.8078.4. See `docs/assets/screenshots/` and `docs/SCREENSHOTS.md`.
- Expanded junior-friendly project, architecture, design, content, testing, deployment, contribution, and debt documentation.
- Published `feature/compose-wasm-portfolio` and opened [PR #1](https://github.com/AhmedNawaz01/Ahmed-nawaz-portfolio-kmp/pull/1). The first hosted build/test run succeeded; the updated action versions require a fresh CI run. Pages is enabled in workflow mode; deployment and live URL verification are pending.
