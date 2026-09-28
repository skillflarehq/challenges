# Spreadsheet column-summary challenge (Skillflare package)

Canonical Skillflare challenge package for an **operations-analyst spreadsheet screen**: open a CSV and leave a labeled min, max, and average, excluding a documented missing code. `challenge.json` states the skills, the video rubric, and how to generate a session. It does not name a subject for the table. A sample extract lives under `workspace/`; grader prose for that extract stays in `truth_pack.md`.

| Path | Role |
|------|------|
| `requirements.json` | Job description text and author prompt (skills only) |
| `challenge.json` | Metadata, skills, tools, video rubric, and session generation |
| `truth_pack.md` | Grader mark scheme for the sample extract in `workspace/` |
| `workspace/` | Sample starter CSV and data notes |

Each generated session keeps the same skill demand: three numeric columns, one missing sentinel, nine statistics, leftover formulas over contiguous ranges. The subject of the rows can change. The files in `workspace/` are one sample, not the challenge definition.

```bash
node challenges/validate-challenge.mjs challenges/weather-basics
```

Do not put solved workbooks or answer keys under `workspace/`.
