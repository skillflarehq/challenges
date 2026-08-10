# CO2 chiller sizing challenge (Skillflare package)

Canonical Skillflare challenge package for a **thermodynamics + capacity-sizing** work sample: single-stage **transcritical R744** chiller for vineyard wine storage — cooling EER in SI W/W, inverse Carnot from cellar/ambient temperatures, and mass-flow / compressor-displacement sizing for a given design cooling load. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (parameters + blank calculation template) |

The skeleton instance is Tev −2 °C / P_gc 9.0 MPa / gas-cooler exit +35 °C / SH 5 K / η_comp 0.70 / η_vol 0.85 / Q_design 18 kW / cellar 13 °C / ambient 32 °C (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`cycle` × `eta_comp` × `load`), applies `variation.apply` templates (brief + `system_parameters.txt` + `calculation_template.md`), and writes a thin layer — **no LLM at runtime**. Refrigerant stays R744; instances stay single-stage transcritical (gas cooler), not subcritical cascade.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/co2-chiller-sizing
```

Do not put solved calculations or answer keys under `workspace/`.
