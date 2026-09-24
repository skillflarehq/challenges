# OpenModelica mass-spring-damper challenge (Skillflare package)

Canonical Skillflare challenge package for a **Modelica / OpenModelica** work sample: complete a stubbed 1D translational mass-spring-damper, simulate it, and report step-response KPIs. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (stubbed `Plant.mo`, `simulate.mos`, notes, blank results) |

The skeleton instance is **lab test-rig** / m = **2.0** kg / k = **50.0** N/m / d = **4.0** N·s/m / F = **10.0** N / stopTime = **20** s (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`plant` × `params`), applies `variation.apply` templates (brief + `Plant.mo` + `simulate.mos` + `notes.md` + `results.md`), and writes a thin layer — **no LLM at runtime**. Every params bundle stays underdamped (ζ ≈ 0.20–0.30) so peak overshoot remains a required KPI.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/modelica-test
```

Do not put solved `connect` equations or answer keys under `workspace/`.
