# Kipeta health-post

Size PV kWp and battery kWh for this synthetic minigrid, then choose the generation plot in QGIS. Record KPIs in `results.md` (or an equivalent leftover spreadsheet).

## Open the site in QGIS

1. Open **QGIS**.
2. Layer → Add Layer → **Add Delimited Text Layer**: file `loads.csv`; Geometry = Point coordinates; **X field** = `lon`; **Y field** = `lat`; Geometry CRS = **EPSG:4326**.
3. Layer → Add Layer → **Add Vector Layer**: `plots.geojson`.
4. Open the plots **attribute table**. Use `plot_id`, `area_m2`, and `excluded`. Do not planimeter the polygons. A web basemap is optional and not required.

LibreOffice Calc is the expected energy-balance tool. QCAD may be used to sketch the array rectangle; not required if leftover results include array area versus plot area.

## Given data

- Site: Kipeta, Dodoma hinterland, Tanzania (GPS -6.82000, 36.48000) — synthetic, not a live project
- Peak-sun hours `PSH` = 5.0 h/day
- Performance ratio `PR` = 0.72
- Loss fraction `loss_frac` = 0.20
- Autonomy = 2.0 days
- `DoD` = 0.80
- Round-trip efficiency `η_rt` = 0.90
- Array packing `m2_per_kwp` = 10 m²/kWp

Building daily kWh values are in `loads.csv`. Do not change them.

## Required formulas

- `E_served` = sum of `daily_kwh`
- `E_day` = `E_served × (1 + loss_frac)`
- `PV_kWp` = `E_day / (PSH × PR)`
- `Battery_kWh` = `(E_day × autonomy_days) / (DoD × η_rt)`
- `Array_area_m2` = `PV_kWp × m2_per_kwp`
- Selected plot: `excluded = false` **and** `area_m2 ≥ Array_area_m2` (exactly one plot qualifies)

## KPIs to report

- Daily generation target `E_day` (kWh/day)
- PV array size `PV_kWp`
- Battery energy `Battery_kWh`
- Array footprint `Array_area_m2`
- Selected `plot_id` (A, B, or C)
