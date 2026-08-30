# Financial analyst challenge (Skillflare package)

Canonical Skillflare challenge package for a **financial analyst** manufacturing three-statement forecasting work sample. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter CSVs, forecast drivers, and blank model template — base-image skeleton |

The skeleton instance is **Northline Precision Components** / **base growth** / FY2024 revenue **$48,000k** (matches `workspace/` files and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`company` × `scenario`), applies `variation.apply` templates (brief + CSVs + drivers + context), and writes a thin layer — **no LLM at runtime**.

Companies are isomorphic light manufacturers in a similar revenue band. Scenarios change growth, margins, CapEx (% of sales), and NWC days while keeping D&A policy and tax/dividend rules aligned so difficulty stays comparable.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/financial-analyst
```

Do not put solved forecast workbooks or answer keys under `workspace/`.
