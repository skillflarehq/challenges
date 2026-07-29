# Docker challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`. Not loaded by the live app yet.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, variation policy) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files |

This example keeps a **shared** instance for every candidate: `shared_axes: []`, `problem_statement.mode: "locked"`, empty `sync_with` / `files`. When you add varyable files later, point `sync_with` at those `files[].path` values and list the story dimensions in `shared_axes`.

```bash
node challenges/validate-challenge.mjs challenges/docker
```

Do not put solution Dockerfiles or answer keys under `workspace/`.
