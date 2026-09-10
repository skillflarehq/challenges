# n8n events-to-NDJSON challenge (Skillflare package)

Canonical Skillflare challenge package for an **n8n** work sample: build and execute a workflow that reads a local JSON extract, explodes it into items, and writes filtered NDJSON under `/data`. Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (`events.json`, notes, blank results) |

The skeleton instance is **test-events ingest** / keep-status **paid** / `/data/events.ndjson` (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`pipeline` × `status`), applies `variation.apply` templates (brief + `notes.md` + `results.md`), and writes a thin layer — **no LLM at runtime**. `events.json` is not rewritten (same 15-row extract; every keep-status keeps exactly three well-formed rows).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/n8n-test
```

Do not put a solved workflow JSON or answer-key NDJSON under `workspace/`.
