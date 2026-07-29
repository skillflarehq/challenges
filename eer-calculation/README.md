# EER refrigeration challenge (Skillflare package)

Canonical Skillflare challenge package for a **thermodynamics calculation** work sample: vapor-compression cycle COP/EER and inverse Carnot comparison for a fixed R22 system. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, variation policy) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (parameters + blank calculation template) |

This package keeps a **shared** instance for every candidate: `shared_axes: []`, `problem_statement.mode: "locked"`, empty `sync_with` / `files`. Candidates may use any tools they like for R22 properties; no property tables or answer keys are shipped under `workspace/`.

```bash
node challenges/validate-challenge.mjs challenges/eer-calculation
```

Do not put solved calculations or answer keys under `workspace/`.
