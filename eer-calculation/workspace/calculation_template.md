# R22 cycle calculation

Fill in this template (or an equivalent spreadsheet). Do not leave critical steps implied.

EU nomenclature (EN 14511-style steady-state): **EER** = cooling (`q_evap / w_comp`, W/W); **COP** = heating / heat pump (`q_cond / w_comp`, W/W). No US BTU/(W·h) conversion required.

## Given data

- Tev =
- Useful superheat =
- Suction superheat =
- Tcond =
- Refrigerant =
- η_comp =
- Property data source =

## Assumptions

-

## State-point table

| Point | Description | T (°C) | P (kPa) | h (kJ/kg) | s (kJ/kg·K) | Phase / notes |
|-------|-------------|--------|---------|-----------|-------------|---------------|
| Evap outlet | Useful SH = 0 | | | | | |
| 1 Compressor inlet | Suction SH = 10 K | | | | | |
| 2s Isentropic discharge | s = s1 | | | | | |
| 2 Actual discharge | η_comp applied | | | | | |
| 3 Condenser outlet | | | | | | |
| 4 Expansion inlet / evap inlet | h4 = h3 | | | | | |

Optional ASCII p-h sketch:

```
  P
  ^
  |     2s / 2
  |      *----* 3
  |     /      |
  |    /       |
  |   1        |
  |    \       |
  |     *------* 4
  |    (evap)
  +----------------> h
```

## Energy balance

- q_evap =
- q_cond = q_evap + w_comp =
- w_isentropic =
- w_comp =
- EER = q_evap / w_comp =
- COP = q_cond / w_comp (or EER + 1) =

## Carnot reference (ΔT = 10 K both sides)

- T_cold (K) =
- T_hot (K) =
- EER_Carnot =
- η_Carnot = EER / EER_Carnot =

## Results summary

| Result | Value | Units |
|--------|-------|-------|
| EER | | W/W |
| COP | | W/W |
| EER_Carnot | | W/W |
| η_Carnot | | — |
