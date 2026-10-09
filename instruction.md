# Autonomous End-to-End Portfolio Execution Plan

> **Purpose:** This file is the single source of instructions for Codex
> to inspect, finish, verify, document, publish, and deploy the Ahmed
> Nawaz engineering portfolio. Work in the existing repository; preserve
> useful work; make routine decisions autonomously; report blockers
> honestly.

## 1. Mission and completion standard

Act as senior software architect, Kotlin Multiplatform engineer, UI/UX
designer, QA engineer, documentation lead, and DevOps/release engineer.

Take the existing portfolio repository from its current state to a
maintainable, tested, documented, published, and deployed website
wherever the available environment, credentials, and permissions allow.

Do not stop after planning. Inspect the repository, make changes, run
checks, capture genuine screenshots, update documentation, publish
reviewed changes, deploy, and verify the live result. Do not ask routine
questions. Choose sensible options based on the repository, official
documentation, security, compatibility, maintainability, and cost.

The portfolio's owner is Ahmed Nawaz, positioned as a **Senior Android
Engineer \| Kotlin Multiplatform Specialist** with 9+ years of software
engineering experience. Relevant areas include Kotlin, Java, Android,
Jetpack Compose, KMP, MVVM/MVI, Clean Architecture, modularization,
fintech/digital banking, audio/video, healthcare, e-commerce, payment
integrations, and AI-enabled products.

Do not invent employers, dates, credentials, URLs, project details,
production achievements, metrics, or client information. Use clearly
labelled editable placeholders where facts are missing. Never publish
confidential banking/client material.

## 2. Autonomy, safety, and working rules

1.  Start by inspecting the actual repository, branch, Git status,
    remotes, uncommitted changes, build configuration, workflows, docs,
    and existing UI. These instructions do not replace repository
    evidence.
2.  Preserve all useful existing work. Never reset, clean, overwrite, or
    discard unrelated changes.
3.  Make routine project-local changes without asking questions. Do not
    ask the owner to choose between ordinary technical options.
4.  Prefer existing compatible tools and the committed Gradle wrapper.
    Do not install global Gradle merely because it is absent.
5.  Verify versions and commands against current official documentation
    when possible. Do not invent APIs, build tasks, or output paths.
6.  Do not bypass approval prompts, security controls, repository
    protection, authentication, or access permissions.
7.  Never print, commit, or publish passwords, tokens, private keys,
    secrets, or sensitive environment variables.
8.  Do not change system-wide settings or install system software
    without the required authorization. If a safe, authorized
    installation is available, use it; otherwise continue independent
    work and document the blocker.
9.  Do not push directly to the default branch if that risks overwriting
    work. Use a feature branch and a reviewable pull request where
    possible.
10. Do not merge, publish a public repository, or deploy to production
    if that requires authorization not already granted.
11. After a failure, diagnose and fix it where safe. Do not repeat the
    same failing command indefinitely.
12. Distinguish verified facts, assumptions, limitations, and unfinished
    work. Never claim a test, screenshot, push, or deployment succeeded
    without evidence.
13. Continue through all phases that can be completed. If one step is
    blocked, complete other independent tasks and give a precise handoff
    at the end.

## 3. Inspect and repair the build environment

Inspect: - Current working directory and Git status. - JDK versions,
`JAVA_HOME`, Node/npm if relevant, Gradle wrapper, GitHub CLI, and
browser/testing tools. - `settings.gradle.kts`, root/module build files,
`gradle/wrapper/gradle-wrapper.properties`, version catalogs, source
sets, resources, tests, and GitHub Actions. - Existing project modules
and the current deployment configuration.

Earlier observations may have included Kotlin 2.2.20, Compose
Multiplatform 1.9.1, a Gradle 9.4.1 reference, and a missing JDK. Treat
these only as clues and verify actual files and installed tools.

Then: 1. Determine the required JDK and supported version combination
for the actual Gradle/Kotlin/Compose/Wasm setup. 2. Verify compatibility
against official documentation. 3. Preserve compatible versions; make
the smallest justified configuration changes. 4. Use the Gradle wrapper
if available and valid. 5. If the JDK is missing, use only a safe,
available, authorized installation method. Do not silently change
machine-wide configuration. 6. Run Gradle configuration and a baseline
build as soon as possible. 7. Record commands and actual outcomes in the
final report.

Do not start adding more features until basic build issues are
understood. Do not make a broad dependency upgrade without evidence.

## 4. Product and technology direction

Keep the existing Kotlin Multiplatform + Compose Multiplatform approach
unless a verified technical blocker makes it unsuitable. The preferred
web target is Kotlin/Wasm, subject to current toolchain and browser
support.

Preferred approach: - Kotlin and Compose Multiplatform. - Gradle Kotlin
DSL and wrapper. - Web-first delivery; optional Android target only if
it adds real value. - Static content and static hosting. - No custom
backend, database, authentication, or paid API for the initial
portfolio.

Verify web-target compatibility, browser support, startup/bundle-size
implications, accessibility, SEO/discoverability, direct-route/refresh
behavior, and hosting constraints. Document material trade-offs. Do not
silently switch to a different framework; if a different approach is
necessary, explain the evidence in the docs and final report.

## 5. Design and functionality

Create a polished, mobile-first engineering portfolio that feels like a
well-designed mobile application adapted to the web.

Visual direction: - Dark theme by default, charcoal/near-black
surfaces. - Restrained mint/cyan/blue accents. - Excellent typography,
contrast, spacing, and visual hierarchy. - Reusable Compose components
and design tokens. - Rounded project cards, subtle borders, purposeful
animation. - Mobile-friendly navigation and touch targets. - Adaptive
desktop navigation and layouts. - Responsive technical diagrams and
project case studies. - Avoid generic template aesthetics, excessive
gradients, decorative controls that do nothing, and random stock
imagery.

Implement and verify these sections as appropriate to the existing
project: 1. Home: name, professional positioning, concise introduction,
selected work, GitHub/LinkedIn/CV calls to action. 2. Projects:
categorized project listing and reusable cards. 3. Case studies:
overview, goal/problem, role, stack, architecture, decisions/trade-offs,
challenges, testing, lessons, screenshots, verified links. 4. About and
experience: accurate professional profile and timeline. 5. Technical
expertise: Android, Kotlin/Java, Compose, KMP, architecture,
coroutines/Flow, testing, fintech, and other verified experience. 6.
Architecture Lab: illustrative Android modular architecture and KMP
shared/platform-specific boundaries, with diagrams readable on mobile
and desktop. 7. Engineering Notes: useful technical notes, clearly
labelled if sample/unpublished. 8. Contact/CV: configurable GitHub,
LinkedIn, email, and CV download; no fabricated URLs.

All controls and navigation must work. Use editable placeholders for
missing facts/assets and label conceptual demos clearly. Do not claim a
conceptual diagram or prototype is a production system.

## 6. Code architecture and maintainability

Use a simple architecture that fits the actual project; do not
overengineer or create artificial modules. Separate content models from
UI, use reusable components, keep navigation/state ownership
understandable, and document shared versus platform-specific code.

The application should be easy for a junior developer to understand and
change. Favor readable names, small focused functions, consistent Kotlin
style, minimal dependencies, meaningful comments where the reason is not
obvious, and straightforward error handling.

Check: - Accessibility semantics and labels. - Keyboard navigation where
supported. - Contrast, font sizing, and touch targets. - Reduced-motion
behavior where supported. - Responsive overflow and long content. -
Missing assets and external links. - Efficient image loading and
unnecessary network requests. - No secrets embedded in client code.

## 7. Comprehensive documentation deliverables

Documentation is a release requirement, not optional polish. Write
clear, approachable English for a junior developer. Explain unfamiliar
terms on first use. Describe only the implementation that actually
exists.

Create/update these files as appropriate, keeping documentation
proportional and avoiding duplicated contradictory instructions:

-   `README.md` --- main project handbook and entry point.
-   `docs/ARCHITECTURE.md` --- architecture, responsibilities,
    dependencies, startup/navigation/state/data flow.
-   `docs/PROJECT_STRUCTURE.md` --- accurate directory tree and
    explanation of important files/modules.
-   `docs/DEVELOPMENT.md` --- prerequisites, setup, run/build/test/debug
    instructions.
-   `docs/UI_DESIGN_SYSTEM.md` --- tokens, typography, components,
    themes, responsive behavior.
-   `docs/ADDING_CONTENT.md` --- updating profile, projects, skills,
    links, CV, and screenshots.
-   `docs/DEPLOYMENT.md` --- hosting, CI/CD, permissions, artifact
    paths, base path, rollback.
-   `docs/TESTING.md` --- actual test commands, results, manual checks,
    coverage gaps.
-   `docs/SCREENSHOTS.md` --- screenshot index with source, viewport,
    date, and notes.
-   `docs/TECHNICAL_DEBT.md` --- evidence-based technical debt register
    and remediation priorities.
-   `docs/CHANGELOG.md` --- meaningful changes.
-   `CONTRIBUTING.md` --- branch, coding, testing, and pull-request
    workflow.
-   `LICENSE` only if a suitable license is justified; never invent
    licensing authority.

Every internal link must resolve. If the repository layout makes a
filename unsuitable, use a clearly equivalent location and document it.

### README required outline

Include a linked table of contents and these sections: 1. Project
overview, goals, feature list, and current status. 2. Live site and
repository links only when verified. 3. Genuine application screenshots
with captions and alt text. 4. Technology stack and reasons for the
choices. 5. Features and how each works. 6. Prerequisites and exact
local setup. 7. Commands to run, build, test, and find build artifacts.
8. Accurate repository tree with explanations of important files. 9.
Architecture and Mermaid diagrams. 10. Application startup, navigation,
content, and state flow. 11. Step-by-step common changes: edit intro,
social links, project data, case study, CV, screenshot, colors,
typography, navigation, and reusable component. 12. Testing and quality
assurance, distinguishing executed tests from recommended checks. 13.
Git workflow and contribution instructions. 14. CI/CD and deployment,
including workflow triggers, permissions, artifact paths, base path,
failure diagnosis, redeployment, and rollback. 15. Security/privacy and
external services, based on actual inspection. 16. Troubleshooting with
cause, diagnosis, and solution. 17. Technical-debt summary linking to
the full register. 18. Known limitations and future roadmap. 19.
Contribution/support guidance.

Explain what each important command does and what successful output
should look like. Commands must match the real repository. Do not claim
a test passed unless it ran successfully.

### Architecture and junior-developer onboarding

In `docs/ARCHITECTURE.md`, document the actual startup flow, module
boundaries, dependency direction, navigation, state updates, content
loading, platform-specific code, tests, build, and deployment pipeline.
Use valid GitHub Mermaid diagrams where helpful.

For every actual module/package, explain: - Responsibility and why it
exists. - Dependencies and what depends on it. - Important files to read
first. - How it can be tested. - What does not belong there.

If there is only one app module, explain its internal packages honestly;
do not invent modules.

The onboarding guide must show a junior developer how to: - Clone/open
the repository and run it. - Find the home screen and understand a
Compose component. - Update project data and add a case study. - Change
a design token. - Add a navigation destination. - Run the relevant
checks. - Review a Git diff and submit a change.

Include a concrete example based on actual source files.

## 8. Genuine screenshots and visual QA

Screenshots must come from the actual running application, not generated
mockups or fabricated browser output.

Store screenshots under a stable path such as
`docs/assets/screenshots/`. Capture appropriate screens that actually
exist, aiming for: - Mobile home. - Mobile projects. - Mobile case
study. - Mobile expertise. - Mobile Architecture Lab. - Desktop home. -
Desktop projects or case study. - Light theme only if implemented.

Use descriptive names such as `home-mobile.png`, `projects-mobile.png`,
`case-study-mobile.png`, `expertise-mobile.png`,
`architecture-lab-mobile.png`, and `home-desktop.png`.

Process: 1. Build and run the actual web application. 2. Confirm it
loads without fatal errors. 3. Use available browser automation
(Playwright or another compatible tool) if installed and practical. 4.
Capture mobile and desktop viewports. 5. Inspect screenshots for
clipping, missing assets, poor contrast, overflow, unreadable text, and
broken layout. 6. Fix visual defects and recapture affected screenshots.
7. Optimize file sizes without harming readability. 8. Add screenshots
and captions to README and `docs/SCREENSHOTS.md`. 9. Record viewport
dimensions, capture date, and whether each image is local or production.
10. After deployment, capture production screenshots if practical and
label them accurately.

Do not skip screenshot capture silently. If automation is unavailable,
try practical alternatives. If capture remains impossible, record the
exact blocker and never claim screenshots exist when they do not.

## 9. Technical debt register

Maintain `docs/TECHNICAL_DEBT.md`. Every item must include: - ID and
title. - Category. - Description and evidence/file paths. - Impact. -
Severity and priority (P0/P1/P2/P3, with definitions). - Rough effort
range. - Recommended remediation. - Dependencies/blockers. - Status. -
Verification criteria.

Cover only real findings or explicitly labelled risks/recommendations,
including architecture, Kotlin/Wasm maturity, performance/bundle size,
accessibility, SEO, responsiveness, testing gaps, dependency
maintenance, security/privacy, CI/CD, documentation, and missing
portfolio content/assets.

Distinguish confirmed defects, known limitations, trade-offs,
recommended improvements, and unverified items. Include immediate,
next-iteration, and future priorities. Move resolved items to a dated
resolved section with evidence. Do not manufacture debt merely to
lengthen the list.

Update the README's debt summary to match the register.

## 10. Testing and verification

Run all checks supported by the repository and environment: - Gradle
wrapper/configuration. - Kotlin compilation. - Compose/Wasm compilation
and production web build. - Unit tests. - Git diff and secret scan. -
Browser or UI tests where practical. - Mobile and desktop layout
checks. - Navigation and direct URL/refresh checks where supported. -
Asset, CV download, and external link checks. - Documentation link/path
consistency. - CI workflow and deployment configuration.

Use actual task names and artifact directories derived from the project.
Fix genuine failures and rerun relevant checks. Do not claim success
without observed output. Clearly list checks that could not run and why.

## 11. GitHub, CI/CD, and deployment

Inspect existing remotes, authentication, repository visibility, branch
protection, and workflows without exposing credential values.

-   Preserve user changes.
-   Use a feature branch and reviewable changes.
-   Check the diff and ensure no secrets or confidential assets are
    included.
-   Update `.gitignore` appropriately.
-   Commit with descriptive messages.
-   Use authenticated GitHub CLI or an already configured remote if
    available.
-   Push only when authorized.
-   Create a pull request where possible; do not bypass repository rules
    or merge without appropriate authorization.
-   If no remote exists, create a repository only when authenticated
    access and authorization permit. If visibility is unclear, prefer
    private until safely resolved.

Prefer free static hosting compatible with the actual build. Use GitHub
Pages if it works reliably with the generated output; otherwise select
an already available, authorized static host. Avoid paid services unless
unavoidable and authorized.

The CI/CD workflow should: - Check out the repository. - Configure the
required JDK. - Use the Gradle wrapper. - Run available tests and build
the web artifact. - Publish only after successful checks. - Use
least-privilege permissions. - Use correct artifact paths and base
path. - Avoid printing secrets. - Match the real hosting provider and
branch workflow.

Do not claim deployment is complete because a workflow file exists or a
local build succeeds. Trigger and inspect the real deployment when
authorized, open the actual live URL, verify it loads, and test
important screens and links. Record the deployed revision where
available.

If credentials, a browser login, repository permissions, a required
system installation, or external approval blocks a step, finish
independent work and document the precise action needed. Never bypass
access controls.

## 12. Documentation and release consistency

When code, navigation, configuration, dependencies, screenshots, or
deployment change, update the relevant documentation and diagrams.
Update the changelog for meaningful milestones and the debt register
when issues are fixed or introduced.

Before release: - Verify every internal documentation link. - Verify
commands and paths against the repository. - Verify every screenshot
link and caption. - Check that architecture diagrams reflect actual
code. - Ensure the README distinguishes current behavior from planned
work. - Ensure no credentials or private material are present.

## 13. Execution sequence

Proceed in this order, without pausing for routine approval:

**Phase A --- Inspect:** repository, current changes, toolchain,
authentication availability, app, docs, screenshots, CI/CD.

**Phase B --- Repair:** compatible JDK/Gradle/Kotlin/Compose/Wasm
configuration and baseline build.

**Phase C --- Implement:** finish responsive UI, screens, content
models, navigation, architecture visuals, and functional links.

**Phase D --- Verify:** build, tests, browser checks,
accessibility/responsive checks, and fixes.

**Phase E --- Document:** comprehensive README, architecture/module
guide, junior onboarding, development/deployment/testing guides,
screenshot index, debt register, contribution guide, changelog.

**Phase F --- Capture:** genuine screenshots of the running app, inspect
them, fix visual issues, and update docs.

**Phase G --- Review and publish:** review diff, scan for secrets,
commit, push, and create a pull request where authorized.

**Phase H --- Deploy:** configure/repair CI/CD, deploy to the selected
authorized static host, inspect workflow, and verify the real live site.

**Phase I --- Final audit:** check docs links, screenshots, live site,
deployed revision, and consistency across README, changelog, and
technical debt.

Do not stop after producing a plan. Continue to the next phase whenever
it can proceed safely. If blocked, complete independent work first.

## 14. Final report

At the end, provide: 1. Summary of implemented features. 2. Actual build
and test commands with results. 3. Screenshots created and exact paths.
4. Documentation files created or updated. 5. Important architecture and
module explanations. 6. Technical debt still open, with priorities. 7.
Git branch, commits, remote repository, and pull-request details where
available. 8. Deployment provider, actual live URL, workflow result, and
deployed revision if verified. 9. Every incomplete step and the precise
blocker/action required.

The project is complete only when all safely achievable tasks have been
executed and verified. Be transparent about anything that remains
incomplete.
