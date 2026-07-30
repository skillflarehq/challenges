# Docker challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files |

The skeleton instance is `test-app` / `Hello from Fastify` / port `3000` (matches `workspace/app` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`service` × `port`), applies `variation.apply` templates (brief + `app/index.js` + `app/package.json`), and writes a thin layer — **no LLM at runtime**. `package-lock.json` is not rewritten (same Fastify dependency).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/docker-node
```

Do not put solution Dockerfiles or answer keys under `workspace/`.
