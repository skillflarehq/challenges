# Industrial heat-pump design challenge (Skillflare package)

Canonical Skillflare challenge package for a **thermodynamics calculation** work sample: industrial high-temperature vapor-compression heat-pump heating COP in SI W/W, plus reverse Carnot comparison from external source/delivery water temperatures for a fixed R1233zd(E) system. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (parameters + blank calculation template) |

The skeleton instance is Tev +35 °C / Tcond +100 °C / useful & suction SH 6 K / η_comp 0.76 / T_L 40 °C / T_H 95 °C (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`cycle` × `eta_comp`), applies `variation.apply` templates (brief + `system_parameters.txt` + `calculation_template.md`), and writes a thin layer — **no LLM at runtime**. Refrigerant stays R1233zd(E); cycle bundles keep temperature lift ≈ 60–70 K and ≈ 5 K approach on both HX sides for isomorphic difficulty.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/heat-pump-design
```

Do not put solved calculations or answer keys under `workspace/`.
