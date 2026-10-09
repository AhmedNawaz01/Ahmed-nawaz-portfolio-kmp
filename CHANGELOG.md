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
- PR #1 passed its updated checks and merged to `main` as `4b0b1141f2af31ee8ec3c5c4e91419d027c93a15`. Workflow run [37939856119](https://github.com/AhmedNawaz01/Ahmed-nawaz-portfolio-kmp/actions/runs/37939856119) passed the Wasm test/build and GitHub Pages deployment jobs. The public Pages URL returned HTTP 200 and served the portfolio HTML. The wrapper warning for Gradle 9.4.1 remains tracked; Kotlin 2.4.20's documented fully supported maximum is Gradle 9.7.0.
