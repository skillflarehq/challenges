# Sales manager challenge (Skillflare package)

Canonical Skillflare challenge package for a **field sales manager** last-mile planning work sample. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter CSVs (import into Sheets / use with QGIS) — base-image skeleton |

The skeleton instance is Nankanga / Sunking Pico Plus / 500 units / $10,000 (matches `workspace/` CSVs and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`community` × `product` × `unit_target` × `budget_usd`), applies `variation.apply` templates (brief + CSVs), and writes a thin layer — **no LLM at runtime**.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + `ratio_range` constraints + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/sales-manager
```

Do not put solution plans or answer keys under `workspace/`.
