# UI design system

## Visual direction

The UI uses a dark charcoal canvas with restrained mint highlights. Tokens are private top-level values in `composeApp/src/commonMain/kotlin/PortfolioApp.kt`: `Ink` (`#0B1010`), `Panel` (`#111A19`), `PanelRaised` (`#172321`), `Mint` (`#9AF0D1`), `TextPrimary` (`#E7EFEC`), `TextMuted` (`#91A39E`), and `Stroke` (`#293936`). Adjust these values together and recheck text contrast.

## Typography and spacing

`index.html` requests DM Sans and Manrope from Google Fonts, with DM Sans/system sans-serif as the page fallback. Compose uses explicit `sp` font sizes, mostly 10–15 sp for labels/body and 29 sp for section titles; home hero scales between 42 and 56 sp. Layout uses a small set of spacing values in `dp`, rounded card shapes, and fine borders. Font requests require network access; the app has no locally bundled font files.

## Components

- `Eyebrow`, `SectionHeader`, `Pill`, `TinyTag`, and `LayerChip` express repeated labels/actions/chips.
- `ProjectCard`, `ExpertiseItem`, `Destination`, and `Metric` render repeated content.
- `Sidebar` and `BottomNavigation` are responsive navigation treatments.
- Sections are composed inside one `LazyColumn`; this keeps scroll behavior simple but does not create page routes.

## Responsive rules

- At 920 dp, navigation changes from bottom bar to 228 dp side rail.
- The home hero adds a conceptual architecture preview at 680 dp.
- Project cards use three columns from 760 dp; expertise uses two columns at the same width.
- Other sections remain a single vertical flow. Validate long content at 320 px and desktop widths; a case-study screen and alternate theme are not implemented.

## Motion and accessibility

Home entrance uses a short fade/slide; architecture selection updates local explanatory text. There is no explicit reduced-motion preference handling. Compose controls should gain semantic roles and meaningful labels before adding more interactions; perform keyboard, screen-reader, contrast, and touch-target review in a real browser. These checks are open in the [debt register](TECHNICAL_DEBT.md).
