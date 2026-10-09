# GitHub Pages deployment

## Workflow behavior

`.github/workflows/build.yml` runs the Wasm test task and production distribution task with `./gradlew` for pushes to `main`, pushes to `feature/**`, pull requests, and manual runs. It uploads the build output as a workflow artifact. A Pages artifact is uploaded and deployed only for a run on `main` (push or manual dispatch); pull requests and feature branches do not replace the live site.

The static distribution path is `composeApp/build/dist/wasmJs/productionExecutable`. The generated `index.html` references `portfolio.js` and the Wasm files relatively, so it does not assume the site is hosted at the domain root.

The deployment job uses `actions/configure-pages`, `actions/upload-pages-artifact`, and `actions/deploy-pages`. GitHub requires the Pages source to be set to **GitHub Actions**. The `configure-pages` action does not enable Pages with `GITHUB_TOKEN`; automatic enablement requires a separate token with repository administration access. During this run, authenticated repository admin access was confirmed and Pages was enabled through GitHub's API with `build_type: workflow`. The API returns the expected `html_url`; that confirms configuration only, not deployment.

## Repository setup status and release steps

Pages was enabled through the authenticated repository admin session. `GET /pages` reports the site URL and `build_type: workflow`. PR #1 merged on 2026-10-09 as `4b0b1141f2af31ee8ec3c5c4e91419d027c93a15`. Main workflow run [37939856119](https://github.com/AhmedNawaz01/Ahmed-nawaz-portfolio-kmp/actions/runs/37939856119) completed successfully: Wasm tests/build and artifact upload passed, then the Pages deploy job passed. The deployed URL returned HTTP 200 and its HTML referenced the generated `portfolio.js` asset. This verifies the deployment and entry page; verify deeper interactions after future changes.

1. Confirm **Settings → Pages → Build and deployment → Source** remains **GitHub Actions**.
2. Confirm Actions may use the `github-pages` environment and the workflow's deploy-job permissions `pages: write` and `id-token: write`.
3. Merge the reviewed feature branch to `main` after its checks pass, or dispatch the workflow from `main`.
4. Open the workflow run and inspect the deployment job's `page_url` output.

GitHub's official guide describes the setup and required workflow permissions: [Deploying your website automatically](https://docs.github.com/en/get-started/start-your-journey/deploying-your-website-automatically).

## Expected URL

GitHub Pages reports this configured project-site URL:

```text
https://ahmednawaz01.github.io/Ahmed-nawaz-portfolio-kmp/
```

The deployment job and public HTTP response verified this URL as live on 2026-10-09. A later `main` push triggers the same build/test/deploy sequence.

## Rollback

For a broken deployment, rerun the last known-good deployment workflow from its deployed `main` revision, or revert the faulty change through a reviewed commit/PR and let the `main` workflow publish that revision. Do not force-push or rewrite deployment history. Record which commit is live after rollback.

## Manual static hosting

Any static host can serve the files in `composeApp/build/dist/wasmJs/productionExecutable`. Preserve the relative JS/Wasm filenames and set the host's route fallback to `index.html` if required. Do not upload Gradle source files or the repository root as the site artifact.
