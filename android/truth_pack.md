# Truth pack

## Correctness summary

Candidate implements the stubbed Jetpack Compose screens and `Summary.fromCsv`, then builds the provided Gradle Android project. After `./gradlew assembleDebug` (or Studio assembleDebug / Build APK), Home shows the instance greeting from starter `strings.xml` / `Screens.kt` comments, Status shows the instance `status_text`, and Summary displays group, total, and skipped for the winning category on starter `app/src/main/assets/items.csv`. The leftover project is the Gradle Compose app (Home / Status / Summary tabs remain). The emulator is optional and not required for a passing grade.

Worked example for starter `app/src/main/assets/items.csv` (same CSV on every instance): skip header; skip `electronics,bad-row,not-a-number` and the empty-category `orphan` row (`skipped` = 2). Totals: electronics 65.50, grocery 13.00, hardware 24.50, office 2.00. Winner `group` is `electronics`, `total` 65.5 (65.50 acceptable). Judge greeting and status copy against the instance — not a fixed skeleton-only “Hello from Android” / `ok`.

## Method notes

Inspect leftover Gradle files and sources. From the workspace root run `./gradlew assembleDebug` (ANDROID_HOME is set; `local.properties` is optional). Optionally run `./gradlew test` for the JVM `SummaryTest` contract. Confirm Home / Status / Summary are still wired from `MainActivity`. Recompute Summary from the starter CSV using the documented rules. Confirm Home binds `R.string.greeting` (or equivalent visible text equal to the instance greeting) and Status binds `R.string.status_text`. Judge against the instance application id / greeting / status copy.

## Expected artifacts

- Gradle wrapper plus Android sources (required)
- Debug assemble that succeeds (`assembleDebug`)
- Unchanged `app/src/main/assets/items.csv`

## Acceptable approaches

- Fill in the existing `HomeScreen` / `StatusScreen` / `SummaryScreen` composables and `Summary.fromCsv`, then `./gradlew assembleDebug`
- `stringResource(R.string.greeting)` / `stringResource(R.string.status_text)` (hardcoded instance strings that match the starter resources also count)
- Reading the CSV with `assets.open("items.csv")` from `SummaryScreen` (or a small helper) and passing the text to `Summary.fromCsv`
- Optional `./gradlew test` or emulator demo; neither is required if assembleDebug succeeds and leftover sources match the contract

## Failure signals

- Screens still say “not implemented”, missing, or different from the documented greeting / status / summary
- Summary hardcoded without matching the CSV algorithm, or CSV rewritten
- No Gradle project / cannot rebuild from the desktop
- One-off `kotlinc` that ignores the Android module and wrapper
- Dropping a Home / Status / Summary tab so a destination never appears
- Replaced Jetpack Compose with Flutter, React Native, a WebView, or a Views-only rewrite that abandons the starter module
