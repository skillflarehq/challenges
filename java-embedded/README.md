# Java SIL controller challenge (example repo layout)

Canonical sketch of a **strict** Skillflare challenge package. Almost everything closed-world lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files |

The skeleton instance is `battery-thermal` / `TEMP1` trip `80.0` / HTTP `3000` / emulator `9100` (matches `workspace/` and `problem_statement`). With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an **unused valid permutation** of vetted factor options (`device` × `http_port` × `emu_port`), applies `variation.apply` templates (brief + `pom.xml` + `Main.java` + `DeviceClient.java` + `Handlers.java` + `BoardEmulator.java` + `data/scenario.md`), and writes a thin layer — **no LLM at runtime**. `Protocol.java`, `Status.java`, and `.vscode/launch.json` are not rewritten (same checksum math and debug config).

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

```bash
node challenges/validate-challenge.mjs challenges/java-embedded
```

Do not put a solved `DeviceClient` / `Handlers` or answer keys under `workspace/`.
