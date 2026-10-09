# Screenshot capture

## Current status

There are no screenshots in this repository. I attempted to capture the built app in Safari 26.5 on 2026-10-09:

- Safari fetched `index.html`, `portfolio.js`, both Wasm files, and `favicon.svg` from the local static server; each request returned HTTP 200.
- `/usr/sbin/screencapture -x /private/tmp/portfolio-desktop.png` failed with `could not create image from display` because this execution environment does not expose a capturable desktop display.
- Safari WebDriver returned `session not created` and requires **Allow remote automation** in Safari's Developer settings. That setting was not changed.
- Chrome and Chromium executables are not installed. The Wasm test runner also confirms it expects ChromeHeadless at `/Applications/Google Chrome.app/Contents/MacOS/Google Chrome`.

No placeholder or generated mockup is being presented as a screenshot. `docs/TECHNICAL_DEBT.md` tracks this review gap.

See the [screenshot index](screenshots/README.md) for the current image inventory.

## Capture after browser automation is permitted

1. Build the production distribution using [the development instructions](DEVELOPMENT.md).
2. Serve `composeApp/build/dist/wasmJs/productionExecutable` on localhost.
3. Open it in Safari or another supported browser and wait until Compose has rendered.
4. Capture one desktop viewport and one narrow mobile viewport from the browser, making sure the page has loaded its Wasm assets.
5. Save the unmodified captures under `docs/screenshots/` with descriptive names such as `portfolio-desktop.png` and `portfolio-mobile.png`.
6. Record the browser version, viewport dimensions, and capture date here; review that no private browser tabs or desktop content are visible.

Screenshots should show the running app, not a design mockup or a locally altered image.
