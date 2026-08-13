# Truth pack

## Correctness summary

Candidate produces an air-side cooling-capacity calculation and a service report for an Emicon CRAH incident using the **instance** design and measured data from the brief / `system_parameters.txt`. The deliverable should include Q_meas from ρ, V̇, c_p, and ΔT; comparison to Q_design with shortfall in kW and %; optional Q/P using P_meas; a primary fault class consistent with measurements and BMS alarms; and a complete service report (situation, findings, root cause, corrective actions, customer-facing summary).

### Skeleton instance reference (Hall A / CRAH-A3, low airflow)

Numeric bands below apply to the **default skeleton only**: Q_design = 80 kW; design supply/return 18/28 °C; design airflow 24000 m³/h; measured supply/return 20.0/29.5 °C; measured airflow 16500 m³/h; P_meas = 24 kW; alarms LOW_AIRFLOW + RETURN_AIR_HIGH; CW/dry-cooler approach normal. For other combinatorial scenarios, judge method first, then whether Q_meas and the primary fault match that instance’s sheet (do not force skeleton bands onto a different hall or fault_id).

| Quantity | Reference | Acceptable band |
|----------|-----------|-----------------|
| V̇_meas | 16500/3600 = 4.583 m³/s | Exact conversion |
| ΔT_meas | 29.5 − 20.0 = 9.5 K | Exact from sheet |
| Q_meas | 1.20 × 4.583 × 1.005 × 9.5 ≈ **52.5 kW** | ±3% (≈ 50.9–54.1 kW) |
| Shortfall kW | 80 − 52.5 ≈ **27.5 kW** | Coherent with their Q_meas |
| Shortfall % | ≈ **34%** of design | Coherent with their Q_meas |
| Q/P | 52.5/24 ≈ **2.19** | Coherent with their Q_meas |
| Primary fault | **Low airflow / restricted air path** (filters, fan/VFD, dampers, containment) | Must align with low V̇ + LOW_AIRFLOW; not primary heat-rejection when CW note is normal |

Design check (optional): V̇_design = 24000/3600 = 6.667 m³/s; Q ≈ 1.20 × 6.667 × 1.005 × 10 ≈ 80.4 kW ≈ 80 kW.

## Method notes

1. **Convert airflow:** V̇ (m³/s) = V (m³/h) / 3600.
2. **Sensible air-side capacity:** Q (kW) = 1.20 × V̇ × 1.005 × (T_return − T_supply) with temperatures in °C (ΔT in K).
3. **Shortfall:** Q_design − Q_meas (kW) and 100 × shortfall / Q_design (%).
4. **Performance ratio:** Q_meas / P_meas (dimensionless); useful context, not a substitute for shortfall.
5. **Fault mapping (skeleton / low-airflow scenarios):** Large drop in V̇ vs design + LOW_AIRFLOW (+ return/filter alarms) + normal CW approach → primary **airflow restriction**. Do not primary-blame condenser/dry cooler when the CW note says approach is in band.
6. **Heat-rejection scenarios** (e.g. `hall_a_heat_rejection`): airflow near design, elevated supply, HIGH_CW_APPROACH → primary **heat rejection / CW side**.
7. **Service report:** Must be actionable for operations and readable by a customer contact; urgency should reflect the instance SLA label (standard vs critical hall).

Inspect arithmetic before debating nuance of root cause. Property libraries are not required for this air-side pack.

## Expected artifacts

- Calculation notes or spreadsheet with V̇, ΔT, Q_meas, shortfall (kW and %), and Q/P
- Completed service report (situation, findings, root cause, corrective actions, escalation/safety, customer-facing summary)
- No requirement for live BMS access, CAD, or application code

## Acceptable approaches

- Hand calculation or spreadsheet using the mandated ρ and c_p
- Restating Q_design from design airflow and design ΔT as a sanity check
- Naming specific airflow causes (dirty filters, failed fan, closed damper, containment bypass) under the low-airflow class
- Mentioning secondary contributors if they do not contradict the primary fault

## Failure signals

- Forgets to divide m³/h by 3600 (Q off by ~3600×)
- Uses T_supply − T_return (sign error) or wet-bulb psychrometrics when the brief asks for sensible ΔT with given constants
- Omits shortfall % or never compares to Q_design
- Primary diagnosis is heat rejection on the skeleton while CW approach is normal and LOW_AIRFLOW is active
- Ignores the alarm excerpt entirely, or invents alarms/measurements not on the sheet
- Service report missing corrective actions or customer-facing explanation
- Treats the task as building software or requiring proprietary Emicon tools
- Grades against a different operating point than the instance brief / starter files
