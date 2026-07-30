# EER refrigeration challenge (Skillflare package)

Canonical Skillflare challenge package for a **thermodynamics calculation** work sample: vapor-compression cycle cooling EER and heat-pump COP in EU W/W nomenclature (EN 14511-style), plus inverse Carnot comparison for a fixed R22 system. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (parameters + blank calculation template) |

The skeleton instance is Tev −10 °C / Tcond +30 °C / suction SH 10 K / η_comp 0.70 / Carnot ΔT 9 K (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`cycle` × `eta_comp` × `carnot_dt`), applies `variation.apply` templates (brief + `system_parameters.txt` + `calculation_template.md`), and writes a thin layer — **no LLM at runtime**. Refrigerant stays R22; cycle bundles keep temperature lift ≈ 35–45 K for isomorphic difficulty.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/eer-calculation
```

Do not put solved calculations or answer keys under `workspace/`.
