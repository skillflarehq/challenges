# R744 transcritical wine-cellar chiller calculation

Fill in this template (or an equivalent spreadsheet). Do not leave critical steps implied.

**Cooling EER** = `q_evap / w_comp` (W/W). Heat rejection is a **gas cooler** at controlled high-side pressure. Size ṁ and compressor displacement for Q_design.

## Given data

- Tev = −2 °C
- Useful superheat = 5 K
- Suction superheat = 5 K
- Gas-cooler exit = +35 °C
- High-side pressure = 9.0 MPa
- Refrigerant = R744
- η_comp = 0.70
- η_vol = 0.85
- Q_design = 18 kW
- T_L (cellar air) = 13 °C
- T_H (outdoor ambient) = 32 °C
- Property data source =

## Assumptions

-

## State-point table

| Point | Description | T (°C) | P (MPa) | h (kJ/kg) | s (kJ/kg·K) | v (m³/kg) | Phase / notes |
|-------|-------------|--------|---------|-----------|-------------|-----------|---------------|
| 1 Compressor inlet | SH = 5 K (+3 °C) | | | | | | |
| 2s Isentropic discharge | s = s1, P = 9.0 MPa | | | | | | supercritical |
| 2 Actual discharge | η_comp applied | | | | | | |
| 3 Gas-cooler exit | +35 °C @ 9.0 MPa | | | | | | |
| 4 Expansion / evap inlet | h4 = h3 | | | | | | |

Optional ASCII p-h sketch (transcritical):

```
  P
  ^
  |   2s / 2 ----* 3 (gas cooler)
  |      /        |
  |     /         |
  |    1          |
  |     \         |
  |      *--------* 4
  |     (evap)
  +----------------> h
```

## Energy balance

- w_isentropic =
- w_comp =
- q_evap = h1 − h4 =
- EER = q_evap / w_comp =

## Carnot reference (external T_L / T_H)

- T_L = 13 °C → (K) =
- T_H = 32 °C → (K) =
- EER_Carnot = T_L / (T_H − T_L) =
- η_Carnot = EER / EER_Carnot =

## Capacity sizing

- ṁ = Q_design / q_evap =
- v1 (suction specific volume) =
- V̇_disp = ṁ · v1 / η_vol =

## Results summary

| Result | Value | Units |
|--------|-------|-------|
| EER | | W/W |
| EER_Carnot | | W/W |
| η_Carnot | | — |
| ṁ | | kg/s |
| V̇_disp | | m³/s |
