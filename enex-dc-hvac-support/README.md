# Enex DC HVAC technical support challenge (Skillflare package)

Canonical-style Skillflare challenge package for an **Emicon CRAH performance / incident** work sample aimed at an Enex Technologies **Technical Support Engineer HVAC** role (data-centre cooling support). Closed-world content lives in `skillflare.json`; grader prose stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `skillflare.json` | Manifest (metadata, role, problem_statement, rubric, combinatorial variation catalog) |
| `truth_pack.md` | Grader mark scheme (required `##` headings) |
| `workspace/` | Candidate starter files (incident parameters + blank calculation and service-report templates) |

## Skeleton instance

Hall A / **CRAH-A3**: Q_design 80 kW; design supply/return 18/28 °C; design airflow 24000 m³/h; measured supply/return 20.0/29.5 °C; measured airflow 16500 m³/h; P_meas 24 kW; alarms `LOW_AIRFLOW` + `RETURN_AIR_HIGH`; CW approach normal. Reference Q_meas ≈ 52.5 kW (~34% shortfall); primary fault = low airflow / restricted air path.

With **variations enabled** at challenge create, packaging fans out buffer slots; each run picks an unused valid permutation of vetted factor options (`scenario` × `sla_urgency`), applies `variation.apply` templates (brief + workspace files), and writes a thin layer — **no LLM at runtime**. Scenario bundles keep measured data, alarms, and CW notes coherent with a single primary fault.

`variation.strategy: "combinatorial"` is the builder-output shape: factor option bundles + mustache-style `{{key}}` templates. Invariants document authoring intent; runtime does not interpret free-text invariants.

Do not put solved calculations or answer keys under `workspace/`.
