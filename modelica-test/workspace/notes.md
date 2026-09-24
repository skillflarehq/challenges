# Lab test-rig translational fixture

Complete `Plant.mo`, simulate it in OpenModelica (`omc simulate.mos` or OMEdit), and record the step-response KPIs in `results.md` (or an equivalent leftover note).

## Given data

- Mass `m` = 2.0 kg
- Spring stiffness `k` = 50.0 N/m
- Damping `d` = 4.0 N·s/m
- Constant force `F` = 10.0 N
- Simulate to `stopTime` = 20 s
- Initial conditions (already set on `mass`): `s = 0`, `v = 0`

## Required topology

Spring and damper in **parallel** between the fixed wall and the mass. Constant force applied to the mass. Use the Modelica Standard Library translational components already declared in `Plant.mo`.

## KPIs to report

- Steady-state mass position `s_ss` (m)
- Peak mass position `s_peak` (m) over `0 … stopTime`
- Damping classification: underdamped, critically damped, or overdamped
