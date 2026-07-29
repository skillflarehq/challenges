# Truth pack

## Correctness summary

Candidate produces a written thermodynamic calculation for an R22 vapor-compression cycle with Tev = −10 °C, useful superheat 0 K, suction superheat 10 K, Tcond = +30 °C, and compression efficiency η_comp = 0.7. The deliverable should include a state-point table, COP and EER of the actual cycle, inverse Carnot COP with ΔT = 10 K at both heat exchangers, and Carnot-relative efficiency η_Carnot = COP_actual / COP_Carnot.

Reference results from CoolProp (R22, REFPROP-based Helmholtz EOS; enthalpies in kJ/kg):

| Quantity | Reference | Acceptable band (±3% on enthalpy-derived results, or stated) |
|----------|-----------|---------------------------------------------------------------|
| P_evap (sat @ −10 °C) | ≈ 355 kPa | Property source may differ slightly |
| P_cond (sat @ +30 °C) | ≈ 1192 kPa | Property source may differ slightly |
| h at evaporator outlet (sat vapor, useful SH = 0) | ≈ 401.2 | Spot-check consistency with source |
| h1 compressor inlet (0 °C @ P_evap) | ≈ 408.2 | Spot-check consistency with source |
| h3 = h4 (sat liquid @ +30 °C) | ≈ 236.6 | Spot-check consistency with source |
| h2s (isentropic discharge @ P_cond) | ≈ 439.9 | Spot-check consistency with source |
| q_useful = h_evap_out − h4 | ≈ 164.6 | Prefer useful-superheat convention |
| w_comp = (h2s − h1) / 0.7 | ≈ 45.3 | η_comp must divide isentropic work |
| COP_actual (useful) | ≈ 3.63 | ≈ 3.5–3.8 |
| EER (useful, COP × 3.412) | ≈ 12.4 | ≈ 12.0–13.0 |
| COP_Carnot | ≈ 4.22 | ≈ 4.20–4.23 (formula is exact for given ΔT) |
| η_Carnot (useful) | ≈ 0.86 | ≈ 0.83–0.90 |

If the candidate includes suction-line superheat in the refrigerating effect (q = h1 − h4 ≈ 171.5), COP ≈ 3.79, EER ≈ 12.9, η_Carnot ≈ 0.90 — accept if they clearly state that assumption. Prefer the useful-superheat convention (q based on evaporator-outlet enthalpy) when both appear without explanation.

## Method notes

1. **Useful superheat 0 K** → evaporator outlet is saturated vapor at Tev = −10 °C.
2. **Suction superheat 10 K** → compressor inlet is at Tev + 10 K = 0 °C and evaporator pressure (superheated vapor).
3. **Condenser outlet** → saturated liquid at Tcond = +30 °C (unless candidate justifies subcooling; none is specified).
4. **Expansion** → isenthalpic: h4 = h3.
5. **Isentropic compression** → from state 1 (h1, s1) to P_cond at s = s1 → h2s.
6. **Actual compression** → w_comp = (h2s − h1) / η_comp with η_comp = 0.7; h2 = h1 + w_comp.
7. **Refrigerating effect (preferred)** → q_evap = h_evap_out − h4 (useful capacity excludes suction-line heating).
8. **COP** → COP = q_evap / w_comp.
9. **EER** → EER = COP × 3.412 (BTU/(W·h)). Accept equivalent SI definitions if clearly labeled and consistent.
10. **Carnot reference with ΔT = 10 K on both sides**:
    - T_cold = (−10 − 10) + 273.15 = 253.15 K
    - T_hot = (30 + 10) + 273.15 = 313.15 K
    - COP_Carnot = T_cold / (T_hot − T_cold) ≈ 4.219
11. **Relative efficiency** → η_Carnot = COP_actual / COP_Carnot.

Inspect the state table for correct phase identification, then check energy formulas and the Carnot temperatures. Property values may differ by source; judge method first, then whether final COP/EER land in the bands above.

## Expected artifacts

- Written calculation, long-form notes, or spreadsheet covering state points, q_evap, w_comp, COP, EER, COP_Carnot, and η_Carnot
- Optional: property-software export or p-h sketch supporting the numbers
- No requirement for Dockerfile, application code, or containerization

## Acceptable approaches

- Hand calculation from ASHRAE / manufacturer / Engineering Toolbox R22 tables with interpolation
- Spreadsheet with tabulated properties
- CoolProp, REFPROP, or similar property libraries
- Online refrigerant property calculators, if the source is cited
- Including or excluding suction-line enthalpy rise in q_evap, if the choice is stated (prefer useful-superheat convention)

## Failure signals

- Treats useful superheat 0 K and suction superheat 10 K as the same state (or puts 10 K of useful superheat in the evaporator while claiming useful SH = 0)
- Applies η_comp inverted (multiplies isentropic work by 0.7 instead of dividing)
- Carnot COP uses Tev and Tcond directly without the specified ΔT = 10 K on both sides
- Carnot formula uses °C instead of absolute temperature, or uses heat-engine Carnot efficiency instead of refrigerator COP
- Reports COP but never converts to (or defines) EER
- No property source and no readable state table — numbers cannot be audited
- Ignores compression efficiency and assumes isentropic compression only
- Turns the task into building software instead of a thermodynamic calculation
