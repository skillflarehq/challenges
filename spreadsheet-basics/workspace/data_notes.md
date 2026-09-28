# Station daily extract

- File: `station_daily.csv`
- Station: GHCND:USC00327027 — PETERSBURG 2 N ND US
- Period: calendar year 2010 (365 daily rows)

## Columns

`STATION`, `STATION_NAME`, `ELEVATION`, `LATITUDE`, `LONGITUDE`, `DATE`, `TMAX`, `TMIN`, `PRCP`

`DATE` is `YYYYMMDD`. Elevation is metres; lat/lon are decimal degrees.

## Units (leave these as stored)

- `TMAX` / `TMIN`: tenths of degrees Celsius (example: `-178` means −17.8 °C)
- `PRCP`: tenths of millimetres

Do not convert unless you clearly label a converted side calculation. The required summary uses the stored values.

## Missing values

`9999` means missing. Exclude `9999` from minimum, maximum, and average **column by column**. Do not treat `9999` as a real temperature or precipitation value.
