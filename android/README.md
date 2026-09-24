# Android Compose challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files |

The skeleton instance is `test-app` / `Hello from Android` / status `ok` (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`app` × `status`), applies `variation.apply` templates (brief + `app/build.gradle.kts` + `strings.xml` + `Screens.kt`), and writes a thin layer — **no LLM at runtime**. `app/src/main/assets/items.csv` and the Gradle wrapper are not rewritten (same CSV and wrapper).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/android
```

Do not put solved screens, `Summary.fromCsv`, or answer-key APKs under `workspace/`.
