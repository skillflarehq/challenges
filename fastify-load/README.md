# Fastify load-test challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files |

The skeleton instance is `test-app` / `Hello from Fastify` / port `3000` (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`service` × `port`), applies `variation.apply` templates (brief + `src/index.ts` + `package.json`), and writes a thin layer — **no LLM at runtime**. `src/routes/summary.ts`, `scripts/generate-data.mjs`, `scripts/benchmark.sh`, and `package-lock.json` are not rewritten (same naive route, dataset generator, and dependency ranges).

`data/items.csv` is **not** stored in the package (import skips files over 5MB). `npm start` runs `scripts/generate-data.mjs` first; the generator writes a deterministic ~23MB CSV when the file is missing. Do not commit `data/items.csv`.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/fastify-load
```

Do not put a cached/pre-aggregated solution or answer keys under `workspace/`.


## Layers

This pack is three self-contained files:

| Path | Role |
|------|------|
| `requirements.json` | Job description text and author prompt |
| `challenge.json` | Metadata, skills, tools, video rubric, and combinatorial generation |
| `variation.json` | One locked instance (problem statement and workspace asset paths) |
| `truth_pack.md` | Grader mark scheme, used with `variation.json` |
| `workspace/` | Starter files named by `variation.json` assets |
