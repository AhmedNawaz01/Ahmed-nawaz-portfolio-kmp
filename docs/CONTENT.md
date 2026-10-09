# Editing portfolio content

The maintained step-by-step guide is [Adding content](ADDING_CONTENT.md). This file retains the original editorial checklist below for quick reference.

## Keep claims verifiable

Only publish experience, project details, dates, metrics, clients, screenshots, or links that Ahmed has reviewed and confirmed. The current project cards are domain placeholders, not proof of a particular employer, product, team size, or outcome.

## Edit the content lists

Open `composeApp/src/commonMain/kotlin/PortfolioContent.kt`.

- `PortfolioContent.projects` contains `Project` values with a display number, name, domain label, short summary, technology tags, and a case-study status.
- `PortfolioContent.experience` contains role and organization labels, a period, and a short description. Replace generic organization and period labels with confirmed details.
- `PortfolioContent.expertise` is the list of skill chips.
- `PortfolioContent.notes` contains draft engineering-note titles. Replace them with published titles only when the article exists.

The models are near the top of the same file. Keep content as plain values and put layout or interaction in `PortfolioApp.kt`.

## Add a case study

For each project, collect the facts before writing:

1. The product problem and the audience.
2. Ahmed's specific role and contribution.
3. Architecture or technical choices he can discuss publicly.
4. Constraints and trade-offs.
5. Outcomes that can be substantiated.
6. Approved images, public URLs, and any confidentiality limits.

Write the case study without confidential company or user data. Do not infer a metric from a qualitative outcome. Keep the technology tags relevant to that project.

## Add contact details or a CV

No real email, LinkedIn URL, GitHub profile URL, or CV file has been provided in the source content. Before publishing them:

1. Confirm the exact public address and URL with Ahmed.
2. Place an approved PDF in `composeApp/src/wasmJsMain/resources` and use a relative asset path so project-site hosting continues to work.
3. Move the displayed link labels and targets into `PortfolioContent.kt`.
4. Make the links keyboard- and screen-reader-accessible in the Compose UI.
5. Build the distribution and verify the link and PDF are present in `productionExecutable`.

## Review checklist

- Names, dates, outcomes, and technologies are accurate.
- Every public URL is approved and resolves.
- No client, company, or user information violates confidentiality.
- Placeholder wording has been removed only when there is real replacement content.
- Mobile and desktop layouts still fit after longer copy is added.
