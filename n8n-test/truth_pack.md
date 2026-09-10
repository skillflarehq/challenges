# Truth pack

## Correctness summary

Candidate builds and **executes** an n8n workflow that reads the starter `events.json`, keeps rows whose `status` equals the **instance** keep-status, skips empty `id` and non-numeric `amount`, maps each kept row to `{id, source, amount}`, and writes **NDJSON** to `/data/<output_file>` (same volume as `/config/data`). The skeleton instance is **test-events ingest** / keep-status **paid** / `/data/events.ndjson`. Other combinatorial instances change the pipeline label, keep-status, and output filename — judge the leftover file against the matching table below, not the paid skeleton lines.

A strong finish shows a successful Execute in the n8n editor (desktop launcher or `http://127.0.0.1:5678/`), item count going from one file/blob to **3** kept items, and a leftover NDJSON file a reviewer can `cat`. A Node/Python one-off that never uses n8n can corroborate numbers; it does not replace the n8n work sample.

Login on this image is the baked owner `candidate@skillflare.local` / `Skillflare1`. Do not treat that as a candidate-invented credential.

### Per-instance keys (same `events.json` on every instance)

Skip: empty `id`; `amount` that is not a JSON number; `status` ≠ keep-status. `evt-skip` (`failed`) is never kept. `evt-bad` (`amount: "n/a"`) is never kept. Extra JSON keys on a line are OK if the three required fields match. Amount `12.5` and `12.50` both count. Line order may follow the file or be sorted; all three kept records must be present, and skipped ids must not appear.

| Keep-status | Output lines (id / source / amount) | n lines |
|-------------|-------------------------------------|---------|
| **paid** (skeleton) | evt-101 checkout **12.5**; evt-105 checkout **7.25**; evt-109 checkout **5** | **3** |
| shipped | evt-102 warehouse **20**; evt-106 warehouse **15.5**; evt-110 warehouse **9.75** | **3** |
| open | evt-103 inbox **8**; evt-107 inbox **3**; evt-111 inbox **11** | **3** |
| active | evt-104 subscription **40**; evt-108 subscription **12**; evt-112 subscription **6.5** | **3** |

Identify the instance from the candidate brief / `notes.md` (keep-status and `/data/…` filename). Do not force paid / `events.ndjson` onto another instance.

## Method notes

Score each of the four rubric items independently.

### 1. Working n8n execution (40)

Leftover evidence of a **successful n8n Execute** (editor execution list, success toast, or terminal `n8n` that still drives this workflow). Award YES only if video or narrative shows the editor run completing without an unresolved node error. Building the canvas and never executing = NO. `curl` of a webhook the workflow exposes counts if the run appears in n8n. Spreadsheet-only or `node events.js` / `python` as the whole sample = NO.

### 2. Correct NDJSON output (30)

Leftover `/data/<instance output_file>` (or `/config/data/…`) contains exactly the three kept records for the instance keep-status, with `id`, `source`, and numeric `amount`. Partial: about **10** per matching kept line (correct id + source + amount); **0** for that slice if a skipped id is present (`""`, `evt-bad`, `evt-skip`, or a wrong-status id). Full **30** if all three match and no extras that violate skip rules. Wrong keep-status (skeleton paid file on a shipped instance) = 0. Pretty-printed JSON array instead of NDJSON is YES for this item if the three objects match; the file-write item still wants a leftover file at the documented path.

### 3. Item explode and field map (20)

Leftover workflow shows **one input blob/file becoming multiple n8n items**, then a mapping that keeps `id` / `source` / `amount` (meta dropped or unused). Typical: Code node “Run Once for All Items” returning `[{json:{…}}, …]`; or Extract From File → Split Out → Filter / Edit Fields. Partial **10** if they filter/map but stay on a single item (one JSON array dumped as one line). Native nodes instead of Code are YES if item count is 3 after the flatten. Hardcoding three Set nodes with the answer keys and no read of `events.json` = 0.

### 4. Clarity of deliverables (10)

Confirm n8n editor work **and** a leftover workflow (still in n8n, or exported JSON on the desktop) plus the `/data` file or a labeled `results.md`. Partial **5** if the file exists but the workflow was deleted, or the workflow remains but the file path is undocumented scrap. Never opened n8n = 0.

### Skip / explode mechanics

`events.json` is a JSON **array**. A Read File / Extract From File step often yields **1 item**. Downstream file-write nodes run **once per item**, so exploding to 3 items is what produces 3 NDJSON lines. Writing `JSON.stringify($json)` with “append newline” (or equivalent Convert to File) is the usual NDJSON pattern.

## Expected artifacts

- n8n workflow that reads starter `events.json` and was executed successfully
- Leftover NDJSON (or equivalent) at `/data/<output_file>` with the three kept events
- Optional: exported workflow JSON, `results.md`, copy of input under `/data/events.json`
- Unchanged starter `events.json` contents
- No requirement for GitHub credentials, Redis queue mode, Docker Compose, or a public webhook

## Acceptable approaches

- Manual Trigger → Read/Write Files from Disk (or copy to `/data` first) → Code (“Run Once for All Items”) → Write/Append file
- Extract From File (JSON) → Split Out → Filter (`status` = instance keep-status, non-empty `id`, numeric `amount`) → Edit Fields → Write file
- Webhook / Schedule trigger if they still Execute (or POST) once during the session
- `fs.readFileSync` inside a Code node (`NODE_FUNCTION_ALLOW_BUILTIN` is enabled on this image)
- Amount as number or numeric string in the output line
- `/config/data/<file>` instead of `/data/<file>` (symlink)

## Failure signals

- Never opens n8n (Node/Python/spreadsheet is the whole sample)
- Canvas built but never executed
- Writes all 15 input rows, or includes `evt-skip` / empty id / `evt-bad`
- Uses a different keep-status than the instance brief
- Writes to the desktop only, not `/data` or `/config/data`
- Hardcoded three lines that do not come from `events.json` (especially if the file was rewritten)
- Relies on GitHub, Slack, or other personal/SaaS accounts
- Changes `events.json` so the instance keys no longer apply
