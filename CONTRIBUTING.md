# Contributing

## Before making a change

Read the [README](README.md), [architecture](docs/ARCHITECTURE.md), and the topic-specific guide. Create a short branch from the current default branch and keep each change focused.

## Code and content

- Keep shared app behavior in `commonMain` and browser-only startup/assets in `wasmJsMain`.
- Add a common test when changing testable content or behavior.
- Use the existing Kotlin/Compose versions and version catalog; explain upgrades.
- Do not publish unverified experience, metrics, employers, client information, links, or CV material.
- Keep generated output, local IDE state, and credentials out of commits.

## Checks and review

Run `./gradlew :composeApp:wasmJsTest` and `./gradlew :composeApp:wasmJsBrowserDistribution` where the environment supports them. Review `git diff --check`, inspect the complete diff, validate documentation links and screenshot references, and state any check that could not run. Open a pull request to `main` with a summary, verification evidence, and screenshots for UI changes. Do not bypass required reviews or protections.
