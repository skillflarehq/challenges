# Qt Widgets challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files |

The skeleton instance is `test-app` / `Hello from Qt` / status `ok` (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`app` × `status`), applies `variation.apply` templates (brief + `CMakeLists.txt` + `src/mainwindow.cpp`), and writes a thin layer — **no LLM at runtime**. `data/items.csv`, `ui/mainwindow.ui`, and `.vscode/launch.json` / `tasks.json` are not rewritten (same CSV, Designer form, and debug config).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/qt-test
```

Do not put solved `populate()` / `fromCsv` or answer-key binaries under `workspace/`.
