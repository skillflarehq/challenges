# Supabase bus-ticketing challenge (Skillflare package)

Canonical Skillflare challenge package for a **full-stack Vue + Supabase** Kenya intercity bus ticketing work sample. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, variation policy) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Empty by design — candidates build the Vue app and Supabase schema from scratch |

This package keeps a **shared** instance for every candidate: `shared_axes: []`, `problem_statement.mode: "locked"`, empty `sync_with` / `files`. No starter scaffolds or seed data are shipped under `workspace/`.

```bash
node challenges/validate-challenge.mjs challenges/supabase-bus-ticketing
```

Do not put solution apps, migrations, or answer keys under `workspace/`.
