# Truth pack

## Correctness summary

Candidate opens the instance GHCN daily extract in a spreadsheet and leaves a labeled min / max / average summary for `TMAX`, `TMIN`, and `PRCP` in **stored units** (tenths of °C / tenths of mm), **excluding `9999` missing** column-wise. The skeleton instance is **Petersburg 2 N, ND / calendar year 2010** (`GHCND:USC00327027`, 365 daily rows in `station_daily.csv`). Other combinatorial extracts use the same station and layout for **2009** or **2013** — judge those against the matching table below, not the 2010 skeleton numbers.

A strong finish is formula-driven (`MIN` / `MAX` / `AVERAGE` or `AVERAGEIF`) over contiguous column ranges, with missing values excluded, and a leftover labeled block a reviewer can read. Status-bar or sort-based answers can earn **correctness** items; they do not earn **function** items unless formulas remain in the workbook. Python/`pandas` can corroborate numbers (correctness only) and does not replace the spreadsheet work sample.

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

1. **Tool** — Confirm LibreOffice Calc (or equivalent spreadsheet) from video / window events. `soffice` opening the CSV counts. Viewing CSV only in a text editor or VS Code does not satisfy “worked in a spreadsheet.”
2. **Ranges** — Data are columns G/H/I (`TMAX` / `TMIN` / `PRCP`) on rows 2–366 if the header is row 1 (365 daily rows). `G2:G366`, `G:G`, a named range, or a filtered column are all fine.
3. **Missing** — `9999` is missing. Check with Autofilter, `MINIFS`/`MAXIFS`, `AVERAGEIF(range,"<>9999")`, `IF` wrappers, or filter-then-aggregate. `MIN`/`MAX`/`AVERAGE` over a range that still contains 9999 will distort **max** and **average** (min of temperatures is usually still correct because 9999 is large).
4. **PRCP trap** — Averaging PRCP **including** 9999 yields a mean in the **tens to hundreds**, not ~14–16. That is a clear NO for the PRCP criterion.
5. **Formulas vs typed values** — Full method credit needs leftover `MIN`/`MAX`/`AVERAGE` (or `AVERAGEIF`) in the formula bar. Status-bar Autocalculate, sorting the column and reading the first/last cell, or a Python printout can support correctness only.
6. **Efficient selection** — Look for column-letter click, Ctrl+Shift+Arrow (Linux webtop / Calc equivalent), Name Box, fill handle, or AutoFilter — not clicking hundreds of cells.
7. **Reuse** — One summary row filled or copied across TMAX/TMIN/PRCP (or min/max/avg filled across) is YES for formula reuse.

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
- PRCP average in the hundreds because 9999 was included
- TMAX/TMIN max equal to 9999
- Nine numbers typed from a calculator or from sorting, with no leftover formulas (correctness may still apply; method items are NO)
- Clicking or listing hundreds of individual cells in formulas
- Reporting unlabeled converted °C/mm as if they were stored tenths (skeleton TMAX min −24.4 instead of −244)
- Empty sheet at the end; results only spoken in the narrative
- Rebuilding or replacing the extract instead of summarizing it
