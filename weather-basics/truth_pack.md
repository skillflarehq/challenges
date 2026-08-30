# Truth pack

## Correctness summary

Candidate opens the instance GHCN daily extract in a spreadsheet and leaves a labeled min / max / average summary for `TMAX`, `TMIN`, and `PRCP` in **stored units** (tenths of °C / tenths of mm), **excluding `9999` missing** column-wise. The skeleton instance is **Petersburg 2 N, ND / calendar year 2010** (`GHCND:USC00327027`, 365 daily rows in `station_daily.csv`). Other combinatorial extracts use the same station and layout for **2009** or **2013** — judge those against the matching table below, not the 2010 skeleton numbers.

A strong finish is formula-driven (`MIN` / `MAX` / `AVERAGE` or `AVERAGEIF`) over contiguous column ranges, with missing values excluded, and a leftover labeled block a reviewer can read. Status-bar or sort-based answers can earn **Correct stats excluding missing**; they do not earn **MIN, MAX, and AVERAGE via functions** or **Efficient range selection** unless formulas remain in the workbook. Python/`pandas` can corroborate numbers (correctness only) and does not replace the spreadsheet work sample.

Score **numeric match** independently of **9999-exclusion method**. `MIN` over a range that still contains `9999` is usually still the correct minimum; `MAX` and `AVERAGE` are not. See Method notes.

### Per-extract keys (exclude 9999)

Averages below are unrounded; accept about **±0.1** (two decimal places is enough). If a visible `AVERAGE` / `AVERAGEIF` over the correct range would produce the mean, treat the average criterion as YES even when cell format rounds the display.

| Extract | Column | n used | n missing | Min | Max | Average | Failure: average including 9999 |
|---------|--------|--------|-----------|-----|-----|---------|---------------------------------|
| **2010** (skeleton) | TMAX | 362 | 3 | −244 | 339 | 98.174… (**98.17**) | ~179.6 |
| 2010 | TMIN | 357 | 8 | −328 | 211 | −6.342… (**−6.34**) | ~213.0 |
| 2010 | PRCP | 360 | 5 | 0 | 381 | 14.733… (**14.73**) | ~151.5 |
| **2009** | TMAX | 364 | 1 | −261 | 344 | 78.673… (**78.67**) | ~105.9 |
| 2009 | TMIN | 362 | 3 | −367 | 189 | −26.279… (**−26.28**) | ~56.1 |
| 2009 | PRCP | 359 | 6 | 0 | 592 | 14.265… (**14.26**) | ~178.4 |
| **2013** | TMAX | 364 | 1 | −256 | 344 | 79.923… (**79.92**) | ~107.1 |
| 2013 | TMIN | 363 | 2 | −311 | 200 | −29.331… (**−29.33**) | ~25.6 |
| 2013 | PRCP | 362 | 3 | 0 | 417 | 15.804… (**15.80**) | ~97.9 |

Identify the instance from the candidate brief year (2010 / 2009 / 2013). Do not force 2010 keys onto another extract.

Converted °C or mm (divide by 10) is acceptable **only if labeled**; the required summary is stored tenths. Converted skeleton 2010 TMAX min would be −24.4 °C — do not treat unlabeled converted figures as matching the tenths keys.

## Method notes

Score each of the four rubric items independently. 2010 numbers in the rubric are examples; use this table for the brief year. If a leftover cell cannot be read, zero **that statistic or item only**.

### 1. Correct stats excluding missing (40)

Number match; formula bar not required. Award partial across the nine statistics against the instance table: about **4** per matching TMAX/TMIN min or max (exact key), about **5** per matching average (within **±0.1**), and about **4 / 5 / 5** for PRCP min / max / average. Full **40** if all nine match.

- **Minima** — Do **not** require `<>9999` / `MINIFS`. `=MIN(G2:G366)` (or H) that still contains 9999 is YES for that min — 9999 cannot be the temperature minimum. Fail the slice for wrong column, missing min, unlabeled conversion (e.g. −24.4 vs −244), or a value that is not the key (including 9999).
- **Maxima** — Do **not** also require seeing `<>9999` / `MAXIFS` if the number already matches. A leftover `MAX` of **9999** zeros only that max slice. Unlabeled conversion or missing max zeros that slice.
- **Averages** — YES for the slice iff within **±0.1** of the instance average key. Display rounding is OK if a visible `AVERAGE` / `AVERAGEIF` would produce the mean. The table column “Failure: average including 9999” zeros **only that average slice**. Omit a column entirely = 0 for that column’s slice (~13).

### 2. MIN, MAX, and AVERAGE via functions (30)

Leftover `MIN`/`MAX`/`MINIFS`/`MAXIFS` (or AutoSum min/max) **and** leftover `AVERAGE` / `AVERAGEIF` / `AVERAGEIFS` (or filtered-range average) for **at least one** required column. Do not require all nine formula cells. Partial **15** if leftover min/max functions but no average formula, or leftover average formula but no min/max functions; **10** if only MIN or only MAX remains. Typed literals / sort-copy / calculator or Python typed into cells = 0.

### 3. Efficient range selection (20)

Leftover formulas use a contiguous block (`G2:G366`, `G:G`, named range, filtered column) rather than listing individual cells, **and** the same formula pattern is reused across columns (fill/copy, mixed references, or isomorphic G vs H vs I). Data are columns G/H/I (`TMAX` / `TMIN` / `PRCP`) on rows 2–366 if the header is row 1. Contiguous-range leftover formulas **are** sufficient evidence of efficient selection; do not require a Ctrl+Shift+Arrow clip. Also YES for column-letter click, Ctrl+Shift+Arrow (Linux webtop / Calc equivalent), Name Box, fill handle, or AutoFilter when leftover formulas still use ranges. Nine separately typed but isomorphic formulas still count as reuse. Partial **10** if contiguous ranges exist for at least one column but formulas were not reused. Cell-by-cell lists, hundreds of cells clicked as the method, typed literals only, or no leftover formulas = 0.

### 4. Labeled leftover spreadsheet (10)

Confirm LibreOffice Calc (or equivalent) from video / window events **and** a labeled Min / Max / Average block (or equivalent headings) a reviewer can find. `soffice` opening the CSV counts. Do **not** require all nine correctness keys here; missing numbers are scored on item 1. Partial **5** if spreadsheet work is visible but the leftover summary is unlabeled scrap, or a labeled block exists but spreadsheet work is not shown. Viewing CSV only in a text editor or VS Code, Python-only work, spoken-only results, or a cleared sheet = 0.

### Missing-value mechanics (why MIN ≠ MAX / AVERAGE)

`9999` is missing. Autofilter, `MINIFS`/`MAXIFS`, `AVERAGEIF(range,"<>9999")`, `IF` wrappers, or filter-then-aggregate all count as exclusion **when the leftover result matches**. `MIN`/`MAX`/`AVERAGE` over a range that still contains 9999 will distort **max** and **average**; min of temperatures is usually still correct because 9999 is large.

## Expected artifacts

- Spreadsheet workbook (`.ods` / `.xlsx` / unsaved Calc sheet) with a labeled Min / Max / Average block for TMAX, TMIN, and PRCP
- Formulas in that block using contiguous ranges and excluding 9999 (especially on PRCP)
- Optional: extra sheet, named ranges, AutoFilter, notes citing `data_notes.md`
- No requirement for charts, pivots, unit conversion, Python, or a written memo

## Acceptable approaches

- `=MIN(G2:G366)` / `=MAX(G2:G366)` / `=AVERAGEIF(G2:G366,"<>9999")` (and the same for H and I)
- `MINIFS` / `MAXIFS` / `AVERAGEIFS` with criterion `<>9999`
- AutoFilter to hide 9999, then `MIN`/`MAX`/`AVERAGE` on the visible range if the leftover formulas still exclude missing
- AutoSum / function wizard / formula autocomplete producing the same functions
- Excel-style names in LibreOffice Calc (`AVERAGE` not `AVERAGE.WEIGHTED`)
- A dedicated summary sheet that references the data sheet
- Status-bar check used to **verify** formula results
- Labeled converted (°C / mm) side column **in addition to** stored-unit summary
- Minor display rounding when the underlying formula is correct

## Failure signals

- Never opens a spreadsheet (CSV stays in an editor or only Python is used for the whole task)
- PRCP average in the hundreds because 9999 was included (zeros the PRCP **average** slice only; min can still score if it is 0)
- TMAX/TMIN max equal to 9999
- Nine numbers typed from a calculator or from sorting, with no leftover formulas (correctness may still apply; function and range items are NO)
- Clicking or listing hundreds of individual cells in formulas
- Reporting unlabeled converted °C/mm as if they were stored tenths (skeleton TMAX min −24.4 instead of −244)
- Empty sheet at the end; results only spoken in the narrative
- Rebuilding or replacing the extract instead of summarizing it
