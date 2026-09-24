# Truth pack

## Correctness summary

Candidate profiles the provided Fastify reporting API and removes the per-request full-file read/parse of `data/items.csv` from GET `/summary`, then demonstrates `./scripts/benchmark.sh` passing on the instance default listen port (see starter `src/index.ts` / brief). GET `/` returns HTTP 200 with `ok` true and `message` equal to the instance greeting from starter `src/index.ts`, GET `/health` returns HTTP 200 with status ok, and GET `/summary` returns HTTP 200 JSON for the winning category on the generated CSV using the starter algorithm. The process listens on 0.0.0.0 (or HOST) rather than localhost-only.

Worked example for the generated `data/items.csv` (same generator on every instance: `scripts/generate-data.mjs`, 1_500_000 data rows, seed 20260914). Skip the header; skip empty-category and non-numeric-amount rows (`skipped` = 3111). Winner `group` is `electronics`, `total` 30233695.300000113 (IEEE `parseFloat` accumulation as JSON-serialized by Fastify; do not treat `toFixed` rounding as equivalent). Judge greeting and port against the instance — not a fixed skeleton-only “Hello from Fastify” / port 3000.

A single curl of `/summary` on the starter route is expected to succeed (slowly). The naive handler fails the load harness via heap pressure, event-loop blocking, multi-second p97.5, and/or non-2xx / connection errors.

## Method notes

Inspect leftover `src/` (especially `src/routes/summary.ts`), `scripts/benchmark.sh`, and `AUDIT.md`. From the workspace root, `npm install && npm start` (first start generates `data/items.csv` if missing). Curl GET `/`, GET `/health`, and GET `/summary` on the instance default port. Recompute `/summary` from the generated CSV using the starter rules (`readFileSync` + `split('\n')` + `/^([^,]+),([0-9.]+)/` + `parseFloat` + max total with lexicographic tie-break). Run `./scripts/benchmark.sh` with `PORT` matching the listen port. Confirm the request path no longer re-reads and re-parses the CSV. Confirm listen host is 0.0.0.0 (or HOST) in `src/index.ts`. For the AI-steering item, use Continue chat logs and terminal history: diagnosis before (or during) prompting beats a paste-OOM loop; they should inspect the diff for contract drift before calling the work done.

## Expected artifacts

- Fastify TypeScript project that starts with `npm start` (required)
- Passing `./scripts/benchmark.sh` output (p97.5 under 20ms, 0 errors / timeouts / non-2xx / resets)
- Unchanged generated `data/items.csv` and unchanged public paths
- `AUDIT.md` with root cause, fix, and evidence

## Acceptable approaches

- Pre-aggregate at process startup into an immutable `{ group, total, skipped }` (or equivalent) and return that from GET `/summary`
- One-time memoization on first request, after which later requests do not touch disk
- `fs.watch` (or equivalent) plus in-memory cache, as long as GET `/summary` does not parse the file on the hot path
- Streaming or line-at-a-time parse **only** during the one-time load, not on every GET
- Optional `PORT` / `HOST` environment overrides as already wired in `src/index.ts`

## Failure signals

- Harness not run, still OOM / multi-second p97.5, or non-2xx under load
- Per-request `readFileSync` / `readFile` / `createReadStream` + full parse of `data/items.csv` still on the GET `/summary` path (including “async” or clustered variants that still read every request)
- Only raising `--max-old-space-size` (or similar heap flags) without removing the I/O bottleneck
- `/summary` hardcoded, CSV rewritten, or `toFixed` / other rounding that changes `total` or `skipped`
- GET `/` or GET `/health` broken, or `/health` blocked because the event loop still does sync full-file work per request
- Replaced Fastify with another stack, or introduced Redis / SQLite / Postgres
- Blind “make this faster” prompting with no diagnosis, or accepting an unverified AI patch as the finish
