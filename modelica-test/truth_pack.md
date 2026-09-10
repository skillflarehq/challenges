# Truth pack

## Correctness summary

Candidate completes the stubbed Modelica translational plant (`Plant.mo`) and simulates it in OpenModelica (`omc simulate.mos` or OMEdit). The leftover model uses the declared MSL components with **spring and damper in parallel** between the fixed wall and the mass, and the constant force acting on the mass. After a successful simulate to the instance `stopTime`, they report:

- Steady-state mass position `s_ss = F / k`
- Peak mass position `s_peak` on the underdamped step from rest
- Damping classification **underdamped** (every combinatorial instance has ζ < 1)

The skeleton instance is **lab test-rig** / m = 2.0 kg / k = 50.0 N/m / d = 4.0 N·s/m / F = 10.0 N / stopTime = 20 s. Other instances change the fixture label and/or the `(m, k, d, F, stopTime)` bundle — judge KPIs against the matching table below, not the skeleton numbers.

A strong finish leaves `Plant.mo` with `connect(...)` statements, a successful `omc` or OMEdit run, and a labeled leftover `results.md` (or equivalent note / plot) a reviewer can read. Analytic checks of `F/k` and the underdamped overshoot formula may corroborate numbers; they do not replace a leftover Modelica model and simulation.

### Per-instance keys (underdamped step from rest)

Accept about **±0.01 m** on `s_ss` and `s_peak` (or ±3%, whichever is looser). `s` at `stopTime` matching `s_ss` counts as the steady-state criterion. Peak is the first overshoot of `mass.s` (not a later numerical blip).

| Params | m (kg) | k (N/m) | d (N·s/m) | F (N) | stopTime (s) | ζ | s_ss (m) | s_peak (m) | Damping |
|--------|--------|---------|-----------|-------|--------------|---|----------|------------|---------|
| **m2_k50** (skeleton) | 2.0 | 50.0 | 4.0 | 10.0 | 20 | 0.20 | **0.200** | **0.305** | underdamped |
| m1_k64 | 1.0 | 64.0 | 3.2 | 16.0 | 20 | 0.20 | **0.250** | **0.382** | underdamped |
| m4_k36 | 4.0 | 36.0 | 7.2 | 18.0 | 25 | 0.30 | **0.500** | **0.686** | underdamped |
| m3_k48 | 3.0 | 48.0 | 6.0 | 12.0 | 20 | 0.25 | **0.250** | **0.361** | underdamped |

Identify the instance from the candidate brief / `notes.md` parameters. Do not force skeleton 0.200 / 0.305 onto another bundle.

Skeleton check: s_ss = 10/50 = 0.20 m; ω_n = √(k/m) = 5 rad/s; ζ = d / (2√(km)) = 4/20 = 0.20; s_peak = s_ss (1 + exp(−ζπ / √(1−ζ²))) ≈ 0.305 m.

## Method notes

Score each of the four rubric items independently. If a leftover KPI cannot be read, zero **that item only**.

### 1. Working OpenModelica simulation (40)

Leftover `Plant.mo` **compiles and simulates** in OpenModelica. Award YES only if video or narrative shows a successful `omc simulate.mos` (or OMEdit Simulate) after the connects are filled — translation/simulation completes, not a still-empty `equation` section. Instant fail for this item: never launching omc/OMEdit, simulation error that is not then fixed, or “probably works” with no run. Spreadsheet-only or hand calc of F/k with no Modelica run = NO.

### 2. Correct step-response KPIs (30)

Number match against the instance table: `s_ss` within band, `s_peak` within band, and damping class **underdamped**. Partial: about **10** for matching s_ss only, about **10** for matching s_peak only, about **10** for underdamped (ζ computed or clearly inferred from an oscillatory plot). Full **30** if all three match. Unlabeled unit conversion (mm vs m) that does not match the metre keys = 0 for that KPI. Critical/overdamped classification on these instances = 0 for the class slice.

### 3. Parallel spring-damper topology (20)

Leftover `Plant.mo` keeps the declared MSL translational components and connects **spring ∥ damper** between `wall` and `mass`, with `force` on the mass. Flange_a / flange_b swaps on spring, damper, or mass are OK (1D elements are two-port). Partial **10** if the model simulates and is recognizably this plant but spring and damper are in **series**, or the force is applied to the wall. Replacing MSL with a single `der()` mass-spring equation can still earn this item if the equations are the parallel (m s̈ + d ṡ + k s = F) plant — that is the same 1D model. A different physical system (electrical RLC only, two masses, extra springs) = 0.

### 4. Clarity of deliverables (10)

Confirm OpenModelica work (omc terminal and/or OMEdit) **and** a leftover `Plant.mo` plus labeled KPI block (`results.md`, notes, or plot annotations) a reviewer can find. Partial **5** if the model is leftover but KPIs are only spoken, or KPIs are written but the `.mo` was deleted. Viewing `Plant.mo` in an editor without simulating, or a cleared desktop = 0.

### Topology and KPI mechanics

- Parallel: `connect(wall.flange, spring.flange_a)`, `connect(wall.flange, damper.flange_a)`, `connect(spring.flange_b, mass.flange_a)`, `connect(damper.flange_b, mass.flange_a)`, `connect(force.flange, mass.flange_b)` (or isomorphic flange pairing).
- Series (wrong for this brief): wall–spring–damper–mass as a single chain. Steady state may still be F/k, but the transient / peak will not match the table.
- s_ss = F/k because at rest the damper force is 0 and the spring takes the whole load.
- Underdamped peak from rest: s_peak = s_ss (1 + exp(−ζπ / √(1−ζ²))) with ζ = d / (2√(k m)).

## Expected artifacts

- Completed `Plant.mo` with `connect` (or equivalent equations) for the parallel plant
- Successful OpenModelica simulation to the instance `stopTime` (`omc simulate.mos` and/or OMEdit)
- Labeled leftover KPIs: s_ss, s_peak, damping class (`results.md` or equivalent)
- Unchanged instance parameters (`m`, `k`, `d`, `F`)
- No requirement for 3D CAD, FMI export, or a written controls memo

## Acceptable approaches

- Textual `connect(...)` in VS Code, then `omc simulate.mos`
- Drawing the same connections in OMEdit and simulating from the GUI
- Adding `val(mass.s, stopTime)` (and a search for the peak) to the `.mos` script
- Reading s_ss / s_peak from the OMEdit plot cursor or the `*_res.mat` result
- Computing ζ and the overshoot formula to **check** a simulated peak
- Writing the equivalent `m*der(v) = F - k*s - d*v; der(s) = v` plant if MSL connects are abandoned but the 1D parallel dynamics and leftover simulation still match
- Minor display rounding when the underlying simulated values match the band

## Failure signals

- Never runs OpenModelica (CSV/spreadsheet/Python-only work sample)
- `equation` section still empty; `simulate` fails and is not fixed
- Spring and damper in series, or force connected only to the wall, with no correction
- Reports skeleton 0.200 / 0.305 on a different `(m, k, d, F)` bundle
- Peak taken from a different signal (`spring.s_rel` with opposite sign, `force.f`, etc.) without matching `mass.s`
- Changes `m` / `k` / `d` / `F` so the instance keys no longer apply
- Deletes `Plant.mo` and leaves only spoken numbers
- Replaces the task with a different Modelica tutorial (bouncing ball, electrical circuit) that is not this plant
