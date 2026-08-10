# Truth pack

## Correctness summary

Candidate produces a written thermodynamic calculation for an industrial R1233zd(E) vapor-compression heat pump using the **instance** design data from the brief / `system_parameters.txt` (Tev, useful superheat, suction superheat, Tcond, η_comp, external T_L / T_H). The deliverable should include a state-point table; heating COP = q_cond / w_comp (W/W); reverse Carnot heating COP from the stated external waste-heat source and process delivery water temperatures (`COP_Carnot = T_H / (T_H − T_L)` with absolute temperatures); and Carnot-relative efficiency η_Carnot = COP / COP_Carnot. Cooling EER is not required.

### Skeleton instance reference (author / CoolProp-class R1233zd(E))

Numeric bands below apply to the **default skeleton only**: Tev = +35 °C, useful SH = 6 K, suction SH = 6 K, Tcond = +100 °C, η_comp = 0.76, T_L = 40 °C, T_H = 95 °C. For other combinatorial instances, judge method first, then whether enthalpy-derived results are coherent for that instance’s parameters (do not force the skeleton COP band onto a different Tev/Tcond/η/T_L/T_H).

| Quantity | Reference | Acceptable band (±3% on enthalpy-derived results, or stated) |
|----------|-----------|---------------------------------------------------------------|
| P_evap (sat @ +35 °C) | ≈ 0.17 MPa | Property source may differ slightly |
| P_cond (sat @ +100 °C) | ≈ 0.84 MPa | Property source may differ slightly |
| h1 compressor inlet (+41 °C @ P_evap; useful SH = suction SH = 6 K) | ≈ 428.2 | Spot-check consistency with source |
| s1 | ≈ 1.765 | Spot-check consistency with source |
| h3 = h4 (sat liquid @ +100 °C) | ≈ 299.1 | Spot-check consistency with source |
| h2s (isentropic discharge @ P_cond) | ≈ 472.9 | Spot-check consistency with source |
| w_comp = (h2s − h1) / 0.76 | ≈ 58.8 | η_comp must divide isentropic work |
| h2 = h1 + w_comp | ≈ 487.0 | Actual discharge |
| q_cond = h2 − h3 | ≈ 187.9 | Heating effect |
| COP (q_cond / w_comp) | ≈ 3.19 | ≈ 3.05–3.35 |
| COP_Carnot (T_H / (T_H − T_L), T_L = 40 °C, T_H = 95 °C) | ≈ 6.69 | ≈ 6.65–6.75 (formula is exact for given temps) |
| η_Carnot | ≈ 0.48 | ≈ 0.45–0.51 |

Skeleton Carnot check: T_L = 40 + 273.15 = 313.15 K; T_H = 95 + 273.15 = 368.15 K; COP_Carnot = 368.15 / (368.15 − 313.15) = 368.15 / 55 ≈ 6.69.

If useful SH and suction SH differ on a non-skeleton instance, evaporator outlet and compressor inlet are distinct; accept q_cond based on actual discharge and condenser outlet enthalpies. Prefer documenting whether evaporator-outlet enthalpy is used for any optional q_evap check.

## Method notes

1. **Useful superheat** → evaporator outlet is Tev + useful SH at evaporator pressure (superheated vapor).
2. **Suction superheat** → compressor inlet is Tev + suction SH at evaporator pressure. When useful SH = suction SH (skeleton), evaporator outlet and compressor inlet coincide.
3. **Condenser outlet** → saturated liquid at the stated Tcond (unless candidate justifies subcooling; none is specified).
4. **Expansion** → isenthalpic: h4 = h3.
5. **Isentropic compression** → from state 1 (h1, s1) to P_cond at s = s1 → h2s.
6. **Actual compression** → w_comp = (h2s − h1) / η_comp with the stated η_comp; h2 = h1 + w_comp.
7. **Heating effect** → q_cond = h2 − h3 (equivalently q_evap + w_comp if q_evap is defined).
8. **Heating COP** → COP = q_cond / w_comp in W/W. Cooling EER is not required.
9. **Carnot reference from external fluid temperatures** (heat-pump / heating comparison):
    - T_L = waste-heat source water temperature (then convert to kelvin)
    - T_H = process delivery water temperature (then convert to kelvin)
    - COP_Carnot = T_H / (T_H − T_L)
    - Do **not** use Tev and Tcond as the Carnot boundaries (those are refrigerant-side; approaches are already embodied in the external temps given).
10. **Relative efficiency** → η_Carnot = COP / COP_Carnot.

Inspect the state table for correct phase identification, then check energy formulas and the Carnot temperatures against the instance parameters. Property values may differ by source; judge method first, then whether final COP is coherent for that instance.

## Expected artifacts

- Written calculation, long-form notes, or spreadsheet covering state points, q_cond, w_comp, COP, COP_Carnot, and η_Carnot
- Optional: property-software export or p-h sketch supporting the numbers
- No requirement for Dockerfile, application code, or containerization

## Acceptable approaches

- Hand calculation from ASHRAE / manufacturer / Engineering Toolbox R1233zd(E) tables with interpolation
- Spreadsheet with tabulated properties
- CoolProp, REFPROP, or similar property libraries (fluid may be labeled R1233zdE / R1233zd(E))
- Online refrigerant property calculators, if the source is cited
- Computing q_cond as h2 − h3 or as q_evap + w_comp, if energy balance is consistent
- Optionally reporting cooling EER in addition — not required; do not fail if heating COP and Carnot comparison are correct

## Failure signals

- Treats useful superheat and suction superheat inconsistently with the instance (e.g. claims useful SH = suction SH but uses different states without explanation)
- Applies η_comp inverted (multiplies isentropic work by η_comp instead of dividing)
- Carnot COP uses Tev and Tcond directly instead of the stated external T_L / T_H
- Carnot formula uses °C instead of absolute temperature, or uses refrigerator EER_Carnot = T_L / (T_H − T_L) as the required benchmark without the heating form COP_Carnot = T_H / (T_H − T_L)
- Omits heating COP (q_cond / w)
- No property source and no readable state table — numbers cannot be audited
- Ignores compression efficiency and assumes isentropic compression only
- Turns the task into building software instead of a thermodynamic calculation
- Grades or plans against a different Tev/Tcond/η/T_L/T_H than the instance brief / starter files
