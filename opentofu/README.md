# OpenTofu DigitalOcean Postgres challenge

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (requirements scenery only) |

The skeleton instance is `acme-analytics-pg` / region `nyc3` / Postgres `15` / size `db-s-1vcpu-1gb` / node count `1` (matches `workspace/requirements.md` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`cluster` × `region` × `pg_version` × `size` × `node_count`), applies `variation.apply` templates (brief + `requirements.md`), and writes a thin layer — **no LLM at runtime**.

Candidates bring their own DigitalOcean API token and must stop at a successful `tofu plan` (no apply). Do not put solution `.tf` files under `workspace/`.

## Desktop image prerequisite

This package sets `"image": "opentofu"`. Live sessions need a desktop image that includes the OpenTofu CLI (`tofu`). The Skillflare `fly/opentofu` base must install OpenTofu before this challenge is usable in production sessions.

```bash
node challenges/validate-challenge.mjs challenges/opentofu
```
