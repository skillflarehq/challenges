# R22 cycle calculation

Fill in this template (or an equivalent spreadsheet). Do not leave critical steps implied.

Report **both** cooling EER and heat-pump COP: EER applies in refrigeration (cooling) mode; COP as a heat-pump figure of merit applies when the same system also provides heating.

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
- EER_cooling = q_evap / w_comp =
- EER = EER_cooling × 3.412 =
- COP_HP = q_cond / w_comp (or EER_cooling + 1) =

## Carnot reference (ΔT = 10 K both sides)

- T_cold (K) =
- T_hot (K) =
- EER_Carnot =
- η_Carnot = EER_cooling / EER_Carnot =

## Results summary

| Result | Value | Units |
|--------|-------|-------|
| EER_cooling | | — |
| EER | | |
| COP_HP | | — |
| EER_Carnot | | — |
| η_Carnot | | — |
