# Truth pack

## Correctness summary

Candidate produces a written thermodynamic calculation for a single-stage **transcritical R744** chiller sized for vineyard wine storage, using the **instance** design data from the brief / `system_parameters.txt` (Tev, useful/suction superheat, gas-cooler exit T, high-side P, η_comp, η_vol, Q_design, external T_L / T_H). The deliverable should include a state-point table; cooling EER = q_evap / w_comp (W/W); inverse Carnot EER from cellar air and outdoor ambient (`EER_Carnot = T_L / (T_H − T_L)` with absolute temperatures); η_Carnot = EER / EER_Carnot; refrigerant mass flow ṁ = Q_design / q_evap; and compressor displacement V̇_disp = ṁ · v1 / η_vol. Heating COP is not required. Heat rejection is a **gas cooler** at controlled high-side pressure — not a subcritical condenser.

### Skeleton instance reference (CoolProp R744 / CO2)

Numeric bands below apply to the **default skeleton only**: Tev = −2 °C, useful SH = suction SH = 5 K, gas-cooler exit = +35 °C, P_gc = 9.0 MPa, η_comp = 0.70, η_vol = 0.85, Q_design = 18 kW, T_L = 13 °C, T_H = 32 °C. For other combinatorial instances, judge method first, then whether enthalpy-derived results and sizing are coherent for that instance’s parameters (do not force the skeleton EER/ṁ band onto a different Tev/P_gc/η/Q).

| Quantity | Reference | Acceptable band (±3% on enthalpy-derived results, or stated) |
|----------|-----------|---------------------------------------------------------------|
| P_evap (sat @ −2 °C) | ≈ 3.30 MPa | Property source may differ slightly |
| h1 compressor inlet (+3 °C @ P_evap) | ≈ 440.3 kJ/kg | Spot-check consistency with source |
| s1 | ≈ 1.887 kJ/kg·K | Spot-check consistency with source |
| v1 | ≈ 0.0116 m³/kg | Spot-check consistency with source |
| h2s (isentropic @ 9.0 MPa) | ≈ 483.1 kJ/kg | Spot-check consistency with source |
| w_comp = (h2s − h1) / 0.70 | ≈ 61.1 kJ/kg | η_comp must divide isentropic work |
| h2 = h1 + w_comp | ≈ 501.4 kJ/kg | Actual discharge |
| h3 = h4 (gas cooler exit 35 °C @ 9.0 MPa) | ≈ 299.0 kJ/kg | Supercritical — not saturated liquid |
| q_evap = h1 − h4 | ≈ 141.3 kJ/kg | Refrigerating effect |
| EER (q_evap / w_comp) | ≈ 2.31 | ≈ 2.2–2.45 |
| EER_Carnot (T_L = 13 °C, T_H = 32 °C) | ≈ 15.06 | ≈ 15.0–15.1 (formula exact for given temps) |
| η_Carnot | ≈ 0.15 | ≈ 0.14–0.17 |
| ṁ = 18 / q_evap | ≈ 0.127 kg/s | Scales with Q_design |
| V̇_disp = ṁ · v1 / 0.85 | ≈ 0.00173 m³/s (≈ 6.2 m³/h) | Scales with Q_design |

Skeleton Carnot check: T_L = 13 + 273.15 = 286.15 K; T_H = 32 + 273.15 = 305.15 K; EER_Carnot = 286.15 / (305.15 − 286.15) = 286.15 / 19 ≈ 15.06.

Property sources may use different enthalpy zeros; judge enthalpy **differences** and method first. Acceptable if EER, Carnot, and sizing are internally coherent.

## Method notes

1. **Useful / suction superheat** → compressor inlet at Tev + SH and evaporator pressure. When useful SH = suction SH (skeleton), evaporator outlet and compressor inlet coincide.
2. **High-side pressure** → independent design input (above critical ≈ 7.38 MPa). Discharge and gas-cooler states are at P_gc, not at a saturation temperature corresponding to ambient.
3. **Gas-cooler exit** → supercritical CO2 at stated (T_gc_exit, P_gc). Do **not** look up saturated liquid at a “condensing temperature.”
4. **Expansion** → isenthalpic: h4 = h3.
5. **Isentropic compression** → from state 1 (h1, s1) to P_gc at s = s1 → h2s.
6. **Actual compression** → w_comp = (h2s − h1) / η_comp; h2 = h1 + w_comp.
7. **Refrigerating effect** → q_evap = h1 − h4.
8. **Cooling EER** → EER = q_evap / w_comp in W/W.
9. **Carnot reference from external temperatures**:
    - T_L = cellar air (then kelvin)
    - T_H = outdoor ambient (then kelvin)
    - EER_Carnot = T_L / (T_H − T_L)
10. **Relative efficiency** → η_Carnot = EER / EER_Carnot.
11. **Mass flow sizing** → ṁ = Q_design / q_evap (consistent units: kW and kJ/kg → kg/s).
12. **Displacement sizing** → V̇_disp = ṁ · v1 / η_vol with v1 at compressor inlet.

Inspect the state table for supercritical gas-cooler identification, then check energy formulas, Carnot temperatures, and sizing against the instance parameters.

## Expected artifacts

- Written calculation, long-form notes, or spreadsheet covering state points, q_evap, w_comp, EER, EER_Carnot, η_Carnot, ṁ, and V̇_disp
- Optional: property-software export or p-h sketch supporting the numbers
- No requirement for Dockerfile, application code, containerization, or a free-form building heat-gain study

## Acceptable approaches

- Hand calculation from ASHRAE / manufacturer / Engineering Toolbox R744 tables with interpolation
- Spreadsheet with tabulated properties
- CoolProp, REFPROP, or similar (fluid may be labeled CO2, R744, or CarbonDioxide)
- Online refrigerant property calculators, if the source is cited
- Reporting V̇_disp in m³/s or m³/h — accept either if conversion is coherent
- Optionally discussing high-side pressure optimization — not required for full credit if the stated P_gc is used correctly

## Failure signals

- Treats the high side as a subcritical condenser (saturated liquid at ambient or gas-cooler “condensing temperature”) instead of supercritical gas cooler at the stated P_gc
- Applies η_comp inverted (multiplies isentropic work by η_comp instead of dividing)
- Carnot EER uses Tev and gas-cooler exit (or Tcond) instead of the stated cellar / ambient T_L / T_H
- Carnot formula uses °C instead of absolute temperature, or uses heat-engine Carnot efficiency
- Omits cooling EER (q_evap / w)
- Omits capacity sizing (ṁ and/or V̇_disp), or sizes from q_cond / wrong load
- Ignores η_vol when computing displacement
- No property source and no readable state table — numbers cannot be audited
- Turns the task into building software or an unbounded heat-gain workbook instead of the thermodynamics + sizing calculation
- Grades or plans against different Tev/P_gc/η/Q/T_L/T_H than the instance brief / starter files
