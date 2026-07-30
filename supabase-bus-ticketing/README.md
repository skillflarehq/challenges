# Supabase bus-ticketing challenge (Skillflare package)

Canonical Skillflare challenge package for a **full-stack Vue + Supabase** Kenya intercity bus ticketing work sample. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, variation invariants) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Empty by design — candidates build the Vue app and Supabase schema from scratch |

This package has **no combinatorial variation catalog** yet: every candidate gets the same locked brief and empty workspace. Invariants document authoring intent only.

```bash
node challenges/validate-challenge.mjs challenges/supabase-bus-ticketing
```

Do not put solution apps, migrations, or answer keys under `workspace/`.
