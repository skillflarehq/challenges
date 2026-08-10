# R1233zd(E) industrial heat-pump calculation

Fill in this template (or an equivalent spreadsheet). Do not leave critical steps implied.

**Heating COP** = `q_cond / w_comp` (W/W). Cooling EER is not required. Carnot reference uses external water temperatures T_L and T_H.

## Given data

- Tev = +35 °C
- Useful superheat = 6 K
- Suction superheat = 6 K
- Tcond = +100 °C
- Refrigerant = R1233zd(E)
- η_comp = 0.76
- T_L (waste-heat source water) = 40 °C
- T_H (process delivery water) = 95 °C
- Property data source =

## Assumptions

-

## State-point table

| Point | Description | T (°C) | P (kPa) | h (kJ/kg) | s (kJ/kg·K) | Phase / notes |
|-------|-------------|--------|---------|-----------|-------------|---------------|
| Evap outlet | Useful SH = 6 K | | | | | |
| 1 Compressor inlet | Suction SH = 6 K (+41 °C) | | | | | |
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

- w_isentropic =
- w_comp =
- q_cond = h2 − h3 (or q_evap + w_comp) =
- COP = q_cond / w_comp =

## Carnot reference (external T_L / T_H)

- T_L = 40 °C → (K) =
- T_H = 95 °C → (K) =
- COP_Carnot = T_H / (T_H − T_L) =
- η_Carnot = COP / COP_Carnot =

## Results summary

| Result | Value | Units |
|--------|-------|-------|
| COP | | W/W |
| COP_Carnot | | W/W |
| η_Carnot | | — |
