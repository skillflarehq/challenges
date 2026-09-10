# test-events ingest

Build and run an n8n workflow that reads `events.json`, keeps rows whose `status` is **paid**, drops malformed rows, and writes one NDJSON line per kept event to `/data/events.ndjson`.

## Input

- File: `events.json` on the desktop (typical path `/config/Desktop/events.json`). You may copy it to `/data/events.json` before reading.
- Each object has `id`, `source`, `status`, `amount`, and nested `meta`.

## Keep / skip rules

- Keep only rows where `status` equals `paid`.
- Skip rows with an empty `id`.
- Skip rows whose `amount` is not a number.
- Drop `meta` (and any other extra fields) from the output.

## Output

Write newline-delimited JSON to **`/data/events.ndjson`** (`/data` is the same volume as `/config/data`). Each line is one object:

```json
{"id":"<id>","source":"<source>","amount":<number>}
```

Use a Manual Trigger (or equivalent) and **Execute workflow** in the n8n editor. Do not replace n8n with a one-off Node/Python script as the work sample.
