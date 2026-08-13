# Supabase bus-ticketing challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Empty by design — candidates build the Vue app and Supabase schema from scratch |

The skeleton instance is **Safari Coach** / **Nairobi–Mombasa** (fare hint `2500` KES, secondary `Nairobi–Kisumu`) — matches `problem_statement`. With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`product` × `corridor`), applies `variation.apply` templates (`candidate_brief` only), and writes a thin layer — **no LLM at runtime**. `apply.files` is empty by design (greenfield; no starter scaffolds under `workspace/`).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/supabase-bus-ticketing
```

Do not put solution apps, migrations, or answer keys under `workspace/`.
