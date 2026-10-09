# Technical debt register

This register distinguishes confirmed gaps from platform trade-offs and unverified items. Effort is a rough engineering estimate, not a commitment.

## Priority guide

- **P0:** release blocker with immediate user/security impact.
- **P1:** required for a credible public portfolio or reliable release workflow.
- **P2:** important quality, performance, or maintainability improvement.
- **P3:** worthwhile future refinement with limited immediate impact.

## Open items

### TD-01 — Add reviewed detailed case studies and real contact/CV details

- **Category / status:** Portfolio content / blocked on owner-provided facts.
- **Evidence:** `PortfolioContent.kt` contains broad domain summaries; contact/CV remain explicit placeholders; no detailed case-study route/card action, verified links, or CV PDF exists.
- **Impact / priority:** High public-readiness impact; **P1**.
- **Effort:** 1–3 days per case study/UI pass, plus owner review.
- **Remediation:** Gather approved problem, role, stack, architecture, trade-offs, testing, outcomes, media, and confidentiality constraints. Add a real detail view only after factual material is ready. Add approved contact links and an actual PDF.
- **Dependencies:** Owner approval and source materials.
- **Verification:** Claims and URLs reviewed; contact links open correctly; PDF appears in production artifact; case-study tests and responsive browser captures added.

### TD-02 — Publish the feature branch and verify deployment

- **Category / status:** CI/CD and release / pending publication and live verification.
- **Evidence:** The feature branch contains local commits ahead of `origin/main`. Authenticated GitHub CLI now verifies account `AhmedNawaz01` with Admin repository permission; Pages is configured with `build_type: workflow` and the expected site URL. No deployment has run and no live URL response is verified yet.
- **Impact / priority:** High delivery impact; **P1**.
- **Effort:** 15–45 minutes after valid repository write/admin access.
- **Remediation:** Push the reviewed feature branch, create a PR, wait for CI, merge through repository policy, then inspect the `main` workflow and published URL.
- **Dependencies:** GitHub network access and successful CI. `main` currently has no branch protection requirement.
- **Verification:** Remote branch commit matches reviewed local commit; PR status recorded; main deployment job succeeds; live URL returns the built application.

### TD-03 — Expand automated UI and navigation coverage

- **Category / status:** Testing / partial.
- **Evidence:** Three common content integrity tests execute successfully via `:composeApp:wasmJsTest`; no automated navigation, architecture interaction, screenshot, accessibility, or route tests exist. The site has one in-page list and no routes.
- **Impact / priority:** Medium-high regression risk; **P1**.
- **Effort:** 1–2 days for meaningful browser navigation/interaction checks.
- **Remediation:** Add browser-level checks for navigation destinations and layer selection, and assert mobile project/skill lists render without overlap. Consider screenshot checks only if CI browser setup is stable.
- **Dependencies:** Current Compose/Wasm browser test support.
- **Verification:** CI discovers/runs named interaction tests and they fail on a deliberately broken destination/list layout.

### TD-04 — Measure and optimize Wasm startup/download cost

- **Category / status:** Performance trade-off / open.
- **Evidence:** Production webpack reports `portfolio.js` at 513 KiB and Wasm files at 2.24 MiB and 8.24 MiB (about 10.5 MiB generated assets in total); see the Gradle production build output.
- **Impact / priority:** Slow first load on constrained networks/devices; **P2**.
- **Effort:** 1–3 days measurement and targeted optimization.
- **Remediation:** Record cold-load timing, transferred bytes, and memory on representative mobile devices. Profile dependency/runtime contributions before removing functionality or splitting code.
- **Dependencies:** Access to target devices and network profiles.
- **Verification:** Document baseline and post-change measurements; preserve functional smoke checks.

### TD-05 — Complete accessibility and reduced-motion review

- **Category / status:** Accessibility / unverified.
- **Evidence:** Compose controls are primarily `clickable` layout elements and custom-drawn content. No screen-reader, keyboard, contrast, or reduced-motion review is recorded; `AnimatedVisibility` has no explicit reduced-motion handling.
- **Impact / priority:** Users may miss navigation/control meaning or experience motion barriers; **P2**.
- **Effort:** 1–3 days for audit and targeted fixes.
- **Remediation:** Add semantics/roles and accessible labels, keyboard activation/focus behavior, contrast checks, and reduced-motion behavior where supported. Test with browser accessibility tools and assistive technology.
- **Dependencies:** Browser/assistive technology access.
- **Verification:** Documented keyboard and screen-reader walkthrough; no unlabeled primary controls; contrast and motion behavior checked.

### TD-06 — Improve search discoverability and font resilience

- **Category / status:** SEO/external dependency / partially mitigated.
- **Evidence:** `index.html` has a title and meta description, but app content is rendered by Compose/Wasm and may be less discoverable than server-rendered HTML. DM Sans/Manrope load from Google Fonts at runtime.
- **Impact / priority:** Search previews and first-render typography depend on browser/runtime/external availability; **P2**.
- **Effort:** 0.5–2 days for testing and possible metadata/static fallback improvements.
- **Remediation:** Validate social preview metadata and crawler output; consider a useful static text shell. Keep reliable system-font fallback and assess whether font hosting should be local after licensing review.
- **Dependencies:** Search crawler/browser verification and font license review if bundling.
- **Verification:** Inspect generated HTML and crawler preview; site remains readable when Google Fonts is blocked.

### TD-07 — Verify privacy/security and dependency maintenance at release

- **Category / status:** Maintenance/release check / ongoing.
- **Evidence:** The site has no backend, auth, analytics, or API secrets; the HTML requests Google Fonts. Kotlin/Compose toolchain and GitHub Actions action major versions require future maintenance review.
- **Impact / priority:** External font request discloses normal request metadata; stale build/action versions can create maintenance/security risk; **P2**.
- **Effort:** 0.5–1 day per periodic audit.
- **Remediation:** Review dependencies/actions before upgrades, pin actions to reviewed SHAs if repository policy requires it, document third-party requests, and keep secrets out of static assets.
- **Dependencies:** Release owner/review policy.
- **Verification:** Dependency/action review recorded; secret scan clean; third-party requests remain intentional.

## Resolved in this execution

| Date | Finding | Evidence |
| --- | --- | --- |
| 2026-10-09 | Root document height collapsed the Compose viewport on mobile. | Playwright showed a 195 px body and clipped home content before the fix. `index.html` now sets explicit full root width/height; rebuilt browser geometry measured 390×844. |
| 2026-10-09 | Mobile `BoxWithConstraints` stacked project cards and expertise items on top of one another. | Genuine mobile screenshots exposed only project 03 and the final expertise label. The narrow branches now wrap repeated children in `Column`; rebuild and recapture verify the lists. |
| 2026-10-09 | Metric labels wrapped awkwardly at 320 px. | Responsive spacing now contracts below 360 dp; Playwright measured a 320×780 viewport with no horizontal overflow or browser errors. |
| 2026-10-09 | Local/CI build required an undocumented global Gradle install. | Generated and verified the Gradle 9.4.1 wrapper; workflow now runs `./gradlew`. |

Reopen resolved items if future screenshots or builds show regression. Update this register alongside every material change.
