# Weather station spreadsheet challenge (Skillflare package)

Canonical Skillflare challenge package for an **operations-analyst spreadsheet screen**: min, max, and average on a year of GHCN daily weather. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter CSV and data notes — base-image skeleton |

The skeleton instance is **Petersburg 2 N, North Dakota** / calendar year **2010** / 365 daily rows (matches `workspace/station_daily.csv` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`extract`: 2010, 2009, or 2013), applies `variation.apply` templates (brief + `station_daily.csv` + `data_notes.md`), and writes a thin layer — **no LLM at runtime**.

Extracts are isomorphic GHCN daily files for the same station (`GHCND:USC00327027`): same 9-column layout, stored tenths units, `9999` missing code, and the same nine required statistics. Years 2011–2012 were skipped because precipitation coverage is too gappy to keep difficulty comparable.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/weather-basics
```

Do not put solved workbooks or answer keys under `workspace/`.
