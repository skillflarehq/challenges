# Solar minigrid PV + siting challenge (Skillflare package)

Canonical Skillflare challenge package for a **minigrid design** work sample on the **solar** desktop (QGIS, LibreOffice Calc, QCAD): size PV kWp and battery kWh from a village load list, then choose the generation plot that can host the array. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (`loads.csv`, `plots.geojson`, design notes, blank results) |

The skeleton instance is **Kipeta health-post** / E_served = **30.0** kWh/day / `loss_frac` = **0.20** / PSH = **5.0** h / PR = **0.72** / autonomy = **2.0** d / DoD = **0.80** / η_rt = **0.90** / `m2_per_kwp` = **10** (matches `workspace/` and `problem_statement`): E_day = **36.0** kWh, PV = **10.0** kWp, battery = **100** kWh, array = **100** m², plot **C**. With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`village` × `design`), applies `variation.apply` templates (brief + `loads.csv` + `plots.geojson` + `design_parameters.txt` + `notes.md` + `results.md`), and writes a thin layer — **no LLM at runtime**. Every design bundle has **exactly one** valid plot (A is always the excluded floodplain; d4 selects **B** so the answer is not always C).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/solar-test
```

Do not put solved KPIs, a filled `results.md`, or an answer-key plot choice under `workspace/`.
