# Sales manager challenge (Skillflare package)

Canonical Skillflare challenge package for a **field sales manager** last-mile planning work sample. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, variation policy) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter CSVs (import into Sheets / use with QGIS) |

The skeleton instance uses Nankanga / Sunking Pico Plus / 500 units / $10,000. With **variations enabled** at challenge create, publish generates isomorphic instances from `variation.shared_axes` and resamples the workspace CSVs (`mode: "resample"`) so each candidate gets coherent but distinct community, product, budget, and unit-target data.

```bash
node challenges/validate-challenge.mjs challenges/sales-manager
```

Do not put solution plans or answer keys under `workspace/`.
