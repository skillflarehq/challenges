# Truth pack

## Correctness summary

Candidate sizes a solar-battery minigrid from the instance load list (`loads.csv`) and design parameters, then selects the generation plot in QGIS. The leftover energy balance uses the **stated formulas** (not HOMER / PVGIS):

- `E_served` = sum of `daily_kwh`
- `E_day` = `E_served × (1 + loss_frac)`
- `PV_kWp` = `E_day / (PSH × PR)`
- `Battery_kWh` = `(E_day × autonomy_days) / (DoD × η_rt)`
- `Array_area_m2` = `PV_kWp × m2_per_kwp`

They then choose the unique plot with `excluded = false` and `area_m2 ≥ Array_area_m2`. Plot `area_m2` on the GeoJSON attribute table is authoritative.

The skeleton instance is **Kipeta health-post** / clinic 10.0 + 6×1.5 houses + shop 4.0 + school 7.0 kWh/day (`E_served` = 30.0) / `loss_frac` = 0.20 / PSH = 5.0 h / PR = 0.72 / autonomy = 2.0 d / DoD = 0.80 / η_rt = 0.90 / `m2_per_kwp` = 10. Other instances change the village label/GPS and/or the design bundle — judge KPIs against the matching table below, not the skeleton numbers.

A strong finish leaves a Calc workbook (or labeled `results.md`), a QGIS inspection of loads + plots, and a leftover `plot_id`. Analytic checks of the formulas may corroborate numbers; they do not replace leftover calculation and map evidence.

### Per-instance keys

Accept about **±3%** on `PV_kWp` and `Battery_kWh` (or **±0.2 kWp** / **±2 kWh**, whichever is looser). Accept 10 vs 10.0. `E_day` and `Array_area_m2` should match the table when present; they support the design-rules item. `plot_id` must match **exactly**.

Village factor only changes names and GPS — it does **not** change the numeric keys. Identify the instance from PSH / PR / `loss_frac` / autonomy / load totals in `notes.md` / `design_parameters.txt` / `loads.csv`.

Plot A is **500 m²** and **excluded** (flood / watercourse setback) on every bundle.

| Design | E_served | loss | E_day | PSH | PR | aut (d) | PV_kWp | Battery_kWh | Array_m2 | B m² | C m² | Plot |
|--------|----------|------|-------|-----|----|---------|--------|-------------|----------|------|------|------|
| **d1_psh5_pr072** (skeleton) | 30.0 | 0.20 | **36.0** | 5.0 | 0.72 | 2.0 | **10.0** | **100** | **100** | 80 | 220 | **C** |
| d2_psh5_pr075 | 24.0 | 0.25 | **30.0** | 5.0 | 0.75 | 2.0 | **8.0** | **83.3** | **80** | 60 | 200 | **C** |
| d3_psh48_pr080 | 40.0 | 0.20 | **48.0** | 4.8 | 0.80 | 1.5 | **12.5** | **100** | **125** | 90 | 300 | **C** |
| d4_psh6_pr080 | 20.0 | 0.20 | **24.0** | 6.0 | 0.80 | 2.0 | **5.0** | **66.7** | **50** | 120 | 40 | **B** |

Identify the instance from the candidate brief / `notes.md` parameters. Do not force skeleton 10.0 kWp / 100 kWh / plot C onto another bundle.

Skeleton check: E_served = 10 + 9 + 4 + 7 = 30; E_day = 30 × 1.20 = 36; PV = 36 / (5.0 × 0.72) = 10.0 kWp; Battery = (36 × 2.0) / (0.80 × 0.90) = 100 kWh; Array = 10.0 × 10 = 100 m². A excluded; B 80 m² too small; C 220 m² fits.

d4 check (plot **B**, not C): E_served = 6 + 6 + 3 + 5 = 20; E_day = 24; PV = 24 / (6.0 × 0.80) = 5.0 kWp; Battery = (24 × 2.0) / 0.72 = 66.67 kWh; Array = 50 m². C is 40 m² (too small); A excluded; B 120 m² fits.

## Method notes

Score each of the four rubric items independently. If a leftover KPI cannot be read, zero **that item only**.

### 1. Correct PV and battery KPIs (40)

Number match against the instance table: `PV_kWp` within band and `Battery_kWh` within band. Partial: about **20** for matching PV only, about **20** for matching battery only. Full **40** if both match. Unlabeled unit conversion (W vs kW, Wh vs kWh) that does not match the keys = 0 for that KPI. Reporting skeleton 10.0 / 100 on a different design bundle = 0.

### 2. Correct generation plot (25)

Leftover `plot_id` matches the instance table **and** QGIS was used to inspect `loads.csv` + `plots.geojson` (attribute table, map, leftover `.qgz` / screenshot, or spoken while those layers are visible). Partial **10** if the correct plot is written but there is no QGIS evidence (text-editor-only GeoJSON read). Picking plot A because it is the largest, picking C on d4, or guessing without opening QGIS = 0.

### 3. Stated design rules (20)

Leftover calculation shows served-energy sum, `E_day` with the instance `loss_frac`, PV from `PSH × PR`, battery from autonomy / DoD / η_rt, and array footprint compared to plot `area_m2`. Partial **10** if the energy formulas are present but the array-vs-plot check is missing (or the reverse). HOMER / PVGIS / invented PSH with no leftover stated formulas = 0.

### 4. Clarity of deliverables (15)

Confirm Calc (or equivalent leftover spreadsheet / `results.md`) **and** a labeled KPI block including `plot_id` a reviewer can find. Partial **8** if the energy numbers are leftover but the plot choice is only spoken, or the plot is written but the workbook was deleted. A cleared desktop = 0.

### Formula mechanics

- `E_served` is the sum of the nine `daily_kwh` rows (six houses share the instance house kWh).
- Loss is applied as a multiplier on served energy, not subtracted from PSH.
- Do not invert PR (`E_day × PR / PSH`) or treat PSH as kWh/kWp/year.
- Battery uses `(E_day × autonomy) / (DoD × η_rt)` — not `E_day × autonomy × DoD`.
- `m2_per_kwp` already includes row spacing; do not add a second packing factor on top unless they still land in the array-area band.
- Plot A is always excluded. Plots B and C are never excluded; the failing one is **too small**, not flooded.

## Expected artifacts

- Leftover energy balance (LibreOffice Calc workbook and/or completed `results.md`) with E_day, PV_kWp, Battery_kWh, Array_area_m2
- QGIS use of `loads.csv` and `plots.geojson` (attribute table and/or map)
- Selected `plot_id` with a brief rejection of the other two plots
- Unchanged instance parameters (load kWh, PSH, PR, DoD, plot attributes)
- No requirement for HOMER, PVGIS, a distribution SLD, or a CAD drawing (QCAD sketch is optional)

## Acceptable approaches

- LibreOffice Calc with explicit formula cells for E_served, E_day, PV, battery, and array area
- Hand / calculator arithmetic recorded in `results.md` or notes, then checked in Calc
- QGIS Delimited Text + Vector layers as documented in `notes.md`
- Opening a leftover `.qgz` / screenshot of the attribute table
- Optional QCAD rectangle of `Array_area_m2` using plot dimensions from QGIS
- Minor display rounding when the underlying values match the band (e.g. battery 66.67 vs 66.7)

## Failure signals

- Never opens QGIS (Calc-only with a guessed plot letter, or GeoJSON read only in a text editor)
- Picks plot A because it has the largest `area_m2` despite `excluded = true`
- Reports skeleton 10.0 kWp / 100 kWh / plot C on a different `(PSH, PR, loss, autonomy, loads)` bundle
- Replaces the stated formulas with HOMER, PVGIS, or an invented solar resource
- Inverts PR or DoD (multiplies by DoD instead of dividing)
- Changes given PSH / PR / load kWh so the instance keys no longer apply
- Deletes the workbook and `results.md` and leaves only spoken numbers
- Turns the task into a distribution-network, tariff, or CAD-only layout exercise with no energy balance
