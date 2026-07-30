# R22 cycle calculation

Fill in this template (or an equivalent spreadsheet). Do not leave critical steps implied.

EU nomenclature (EN 14511-style steady-state): **EER** = cooling (`q_evap / w_comp`, W/W); **COP** = heating / heat pump (`q_cond / w_comp`, W/W). No US BTU/(W·h) conversion required.

## Given data

- Tev = −10 °C
- Useful superheat = 0 K
- Suction superheat = 10 K
- Tcond = +30 °C
- Refrigerant = R22
- η_comp = 0.70
- Carnot ΔT = 9 K
- Property data source =

## Assumptions

-

## State-point table

| Point | Description | T (°C) | P (kPa) | h (kJ/kg) | s (kJ/kg·K) | Phase / notes |
|-------|-------------|--------|---------|-----------|-------------|---------------|
| Evap outlet | Useful SH = 0 K | | | | | |
| 1 Compressor inlet | Suction SH = 10 K (0 °C) | | | | | |
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

## Carnot reference (ΔT = 9 K both sides)

- T_cold = Tev + 9 K → (K) =
- T_hot = Tcond − 9 K → (K) =
- EER_Carnot =
- η_Carnot = EER / EER_Carnot =

## Results summary

| Result | Value | Units |
|--------|-------|-------|
| EER | | W/W |
| COP | | W/W |
| EER_Carnot | | W/W |
| η_Carnot | | — |
