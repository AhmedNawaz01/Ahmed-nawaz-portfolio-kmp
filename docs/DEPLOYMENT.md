# GitHub Pages deployment

## Workflow behavior

`.github/workflows/build.yml` runs the Wasm test task and production distribution task for pushes to `main`, pushes to `feature/**`, pull requests, and manual runs. It uploads the build output as a workflow artifact. A Pages artifact is uploaded and deployed only for a run on `main` (push or manual dispatch); pull requests and feature branches do not replace the live site.

The static distribution path is `composeApp/build/dist/wasmJs/productionExecutable`. The generated `index.html` references `portfolio.js` and the Wasm files relatively, so it does not assume the site is hosted at the domain root.

The deployment job uses `actions/configure-pages`, `actions/upload-pages-artifact`, and `actions/deploy-pages`. GitHub requires the Pages source to be set to **GitHub Actions**. The `configure-pages` action does not enable Pages with `GITHUB_TOKEN`; automatic enablement requires a separate token with repository administration access. The repository was checked on 2026-10-09 and currently reports `has_pages: false`, so this setting must be enabled in repository settings before the first Pages deployment can succeed.

## One-time repository setup

An administrator should:

1. Open the repository's **Settings → Pages**.
2. Set **Build and deployment → Source** to **GitHub Actions**.
3. Confirm Actions are allowed to use the `github-pages` environment and the workflow permissions `pages: write` and `id-token: write`.
4. Merge the reviewed deployment workflow to `main` or dispatch it from `main`.
5. Open the workflow run and inspect the deployment job's `page_url` output.

GitHub's official guide describes the setup and required workflow permissions: [Deploying your website automatically](https://docs.github.com/en/get-started/start-your-journey/deploying-your-website-automatically).

## Expected URL

For this public project repository, the expected project-site URL is:

```text
https://ahmednawaz01.github.io/Ahmed-nawaz-portfolio-kmp/
```

This is a derived expected URL, not a verified live URL. GitHub currently reports Pages disabled, so do not share it as active until the deployment job succeeds and the URL returns the published page.

## Manual static hosting

Any static host can serve the files in `composeApp/build/dist/wasmJs/productionExecutable`. Preserve the relative JS/Wasm filenames and set the host's route fallback to `index.html` if required. Do not upload Gradle source files or the repository root as the site artifact.
