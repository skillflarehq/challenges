# Truth pack

## Correctness summary

Candidate opens the variation’s GHCN daily extract in a spreadsheet and leaves a labeled min / max / average summary for `TMAX`, `TMIN`, and `PRCP` in **stored units** (tenths of °C / tenths of mm), **excluding the missing sentinel column by column**. Station, year, and row count are variation contents. Do not score against a fixed year table. Example numbers in the rubric (including 2010 keys) are illustrations; the keys you compute from this variation’s extract win.

A strong finish is formula-driven (`MIN` / `MAX` / `AVERAGE` or `AVERAGEIF`) over contiguous column ranges, with missing values excluded, and a leftover labeled block a reviewer can read. Status-bar or sort-based answers can earn the **correct-stats** slice; they do not earn the **leftover-functions** or **range-selection** slices unless formulas remain in the workbook. Python/`pandas` can corroborate numbers (correctness only) and does not replace the spreadsheet work sample.

Score **numeric match** independently of **sentinel-exclusion method**. `MIN` over a range that still contains the sentinel is usually still the correct minimum; `MAX` and `AVERAGE` are not. See Method notes.

### Keys for this variation

The sentinel in this challenge’s extracts is `9999`. If the variation’s data notes name a different missing code, use that code instead.

1. Open the variation’s daily CSV. It has a header row. Identify `TMAX`, `TMIN`, and `PRCP` **by header name**, not by column letter.
2. For each of those three columns, drop sentinel cells, then record:
   - **Min** — minimum of the remaining values
   - **Max** — maximum of the remaining values
   - **Average** — arithmetic mean of the remaining values (unrounded)
   - **Failure average** — arithmetic mean of the column **including** the sentinel. This is a diagnostic for the average slice only.
3. Score the candidate’s nine figures against those keys. Min and max must match the key exactly. Averages match within **±0.1** (two decimal places is enough). If a visible `AVERAGE` / `AVERAGEIF` over the correct range would produce the mean, treat the average criterion as YES even when cell format rounds the display.

`MIN` over a range that still contains the sentinel is YES for that minimum: the sentinel is larger than real temperatures and precipitation, so it cannot be the minimum. `MAX` and `AVERAGE` over a range that still contains the sentinel are not the keys. The failure average zeros **only that average slice**.

A value that is the stored key divided by 10 counts **only if labeled** as °C or mm. The required summary is stored tenths. The rubric’s unlabeled −24.4 vs −244 contrast is a unit check, not an answer key — do not treat unlabeled converted figures as matching the stored-unit keys.

## Method notes

Score the single rubric item **Spreadsheet column summary** (weight 100) as the sum of the four slices below. Ignore any year table or example keys printed in the rubric; use the keys computed above. If a leftover cell cannot be read, zero **that statistic or slice only**.

### Correct stats excluding missing (40)

Number match; formula bar not required. Award partial across the nine statistics against the computed keys: about **4** per matching TMAX/TMIN min or max (exact key), about **5** per matching average (within **±0.1**), and about **4 / 5 / 5** for PRCP min / max / average. Full **40** if all nine match.

- **Minima** — Do **not** require `<>9999` / `MINIFS`. `MIN` of the column’s data range that still contains the sentinel is YES for that min. Fail the slice for wrong column, missing min, unlabeled conversion (stored key divided by 10, e.g. −24.4 vs −244), or a value that is not the key (including the sentinel).
- **Maxima** — Do **not** also require seeing `<>9999` / `MAXIFS` if the number already matches. A leftover `MAX` equal to the sentinel zeros only that max slice. Unlabeled conversion or missing max zeros that slice.
- **Averages** — YES for the slice iff within **±0.1** of the computed average. Display rounding is OK if a visible `AVERAGE` / `AVERAGEIF` would produce the mean. An average that matches the including-sentinel failure average zeros **only that average slice**. Omit a column entirely = 0 for that column’s slice (~13).

### Leftover functions (30)

Leftover `MIN`/`MAX`/`MINIFS`/`MAXIFS` (or AutoSum min/max) **and** leftover `AVERAGE` / `AVERAGEIF` / `AVERAGEIFS` (or filtered-range average) for **at least one** required column. Do not require all nine formula cells. Partial **15** if leftover min/max functions but no average formula, or leftover average formula but no min/max functions; **10** if only MIN or only MAX remains. Typed literals / sort-copy / calculator or Python typed into cells = 0.

### Efficient range selection (20)

Leftover formulas use a contiguous block covering that column’s data rows (a bounded range, the full column, a named range, or a filtered column) rather than listing individual cells, **and** the same formula pattern is reused across `TMAX`, `TMIN`, and `PRCP` (fill/copy, mixed references, or isomorphic formulas). Identify columns by header name. `G2:G366`, `H`, and `I` are examples only when the file has a header plus 365 daily rows and `TMAX` / `TMIN` / `PRCP` sit in those columns. Contiguous-range leftover formulas **are** sufficient evidence of efficient selection; do not require a Ctrl+Shift+Arrow clip. Also YES for column-letter click, Ctrl+Shift+Arrow (Linux webtop / Calc equivalent), Name Box, fill handle, or AutoFilter when leftover formulas still use ranges. Nine separately typed but isomorphic formulas still count as reuse. Partial **10** if contiguous ranges exist for at least one column but formulas were not reused. Cell-by-cell lists, hundreds of cells clicked as the method, typed literals only, or no leftover formulas = 0.

### Labeled leftover spreadsheet (10)

Confirm LibreOffice Calc (or equivalent) from video / window events **and** a labeled Min / Max / Average block (or equivalent headings) a reviewer can find. `soffice` opening the CSV counts. Do **not** require all nine correctness keys here; missing numbers are scored on the correct-stats slice. Partial **5** if spreadsheet work is visible but the leftover summary is unlabeled scrap, or a labeled block exists but spreadsheet work is not shown. Viewing CSV only in a text editor or VS Code, Python-only work, spoken-only results, or a cleared sheet = 0.

### Missing-value mechanics (why MIN ≠ MAX / AVERAGE)

The sentinel (`9999` unless the variation’s notes say otherwise) is missing. Autofilter, `MINIFS`/`MAXIFS`, `AVERAGEIF(range,"<>9999")`, `IF` wrappers, or filter-then-aggregate all count as exclusion **when the leftover result matches the computed key**. `MIN`/`MAX`/`AVERAGE` over a range that still contains the sentinel will distort **max** and **average**; min of temperatures and precipitation is usually still correct because the sentinel is large.

## Expected artifacts

- Spreadsheet workbook (`.ods` / `.xlsx` / unsaved Calc sheet) with a labeled Min / Max / Average block for TMAX, TMIN, and PRCP
- Formulas in that block using contiguous ranges and excluding the sentinel on max and average (especially on PRCP)
- Optional: extra sheet, named ranges, AutoFilter, notes citing the variation’s data notes
- No requirement for charts, pivots, unit conversion, Python, or a written memo

## Acceptable approaches

- `MIN` / `MAX` of the column’s data range, with `AVERAGEIF` (or equivalent) excluding the sentinel, and the same pattern on `TMIN` and `PRCP`
- `MINIFS` / `MAXIFS` / `AVERAGEIFS` with a not-equal-to-sentinel criterion
- AutoFilter to hide the sentinel, then `MIN`/`MAX`/`AVERAGE` on the visible range if the leftover formulas still exclude missing
- AutoSum / function wizard / formula autocomplete producing the same functions
- Excel-style names in LibreOffice Calc (`AVERAGE` not `AVERAGE.WEIGHTED`)
- A dedicated summary sheet that references the data sheet
- Status-bar check used to **verify** formula results
- Labeled converted (°C / mm) side column **in addition to** stored-unit summary
- Minor display rounding when the underlying formula is correct
- On a header-plus-365-row extract with `TMAX` / `TMIN` / `PRCP` in columns G / H / I, ranges such as `G2:G366` or `G:G` (and the same for H and I)

## Failure signals

- Never opens a spreadsheet (CSV stays in an editor or only Python is used for the whole task)
- An average that matches the including-sentinel failure average (zeros that **average** slice only; the min can still score)
- TMAX or TMIN max equal to the sentinel
- Nine numbers typed from a calculator or from sorting, with no leftover formulas (the correct-stats slice may still apply; the function and range slices are 0)
- Clicking or listing hundreds of individual cells in formulas
- Reporting unlabeled converted °C/mm as if they were stored tenths (stored key divided by 10, e.g. −24.4 instead of −244)
- Empty sheet at the end; results only spoken in the narrative
- Rebuilding or replacing the extract instead of summarizing it
