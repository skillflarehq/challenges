# Sales manager challenge (Skillflare package)

Canonical Skillflare challenge package for a **field sales manager** last-mile planning work sample. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, variation policy) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter CSVs (import into Sheets / use with QGIS) |

This package keeps a **shared** instance for every candidate: `shared_axes: []`, `problem_statement.mode: "locked"`, empty `sync_with` / `files`. Candidates use the starter CSVs plus desktop tools (spreadsheets, maps); no solved plan is shipped under `workspace/`.

```bash
node challenges/validate-challenge.mjs challenges/sales-manager
```

Do not put solution plans or answer keys under `workspace/`.
