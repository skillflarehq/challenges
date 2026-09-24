# Truth pack

## Correctness summary

Candidate implements the stubbed Java HTTP handlers and runs the provided Maven + JDK `HttpServer` service. After compile/exec and run on the instance’s documented default listen port (see starter `Main.java` / brief), GET / returns HTTP 200 with `ok` true and `message` equal to the instance greeting from the starter `Handlers.java` comment, GET /health returns HTTP 200 with status ok, and GET /summary returns HTTP 200 JSON for the winning category on starter `data/items.csv`. The process listens on 0.0.0.0 (or HOST) rather than localhost-only.

Worked example for starter `data/items.csv` (same CSV on every instance): skip header; skip `electronics,bad-row,not-a-number` and the empty-category `orphan` row (`skipped` = 2). Totals: electronics 65.50, grocery 13.00, hardware 24.50, office 2.00. Winner `group` is `electronics`, `total` 65.5 (65.50 acceptable). Judge greeting and port against the instance — not a fixed skeleton-only “Hello from Java” / port 3000.

## Method notes

Inspect leftover `pom.xml` and sources. Compile and run with Maven from the workspace root so `data/items.csv` resolves, curl GET /, GET /health, and GET /summary on the instance default port. Confirm HTTP 200 (not leftover 501). Recompute `/summary` from the starter CSV using the documented rules. Confirm `/health` and `/summary` contexts are registered in addition to `/` (longest-prefix match). Confirm listen host is 0.0.0.0 (or HOST) in `Main.java`. Judge against the instance package name / greeting / default port.

## Expected artifacts

- pom.xml plus Java sources (required)
- Running server that serves the three GET routes
- Unchanged `data/items.csv`

## Acceptable approaches

- Fill in the existing `Handlers.root` / `health` / `summary` methods and `mvn compile exec:java`
- Manual JSON strings (no Jackson or extra Maven dependencies)
- Reading the CSV with `Files` / `BufferedReader` from `data/items.csv` relative to the working directory
- Optional `PORT` / `HOST` environment overrides as already wired in `Main.java`

## Failure signals

- Routes still 501, missing, or different from the documented JSON
- `/summary` hardcoded without matching the CSV algorithm, or CSV rewritten
- No Maven project / cannot rebuild from the desktop
- One-off `javac` that ignores `pom.xml`
- Only `createContext("/")` so `/health` and `/summary` never dispatch
- Listens only on 127.0.0.1 so a reviewer following the starter host cannot rely on 0.0.0.0
- Replaced JDK HttpServer with Spring or another stack
