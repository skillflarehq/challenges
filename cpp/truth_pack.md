# Truth pack

## Correctness summary

Candidate implements the stubbed C++ HTTP handlers and runs the provided CMake + cpp-httplib server. After configure/build and run on the instance’s documented default listen port (see starter `src/main.cpp` / brief), GET / returns HTTP 200 with `ok` true and `message` equal to the instance greeting from the starter `src/handlers.cpp` comment, GET /health returns HTTP 200 with status ok, and GET /summary returns HTTP 200 JSON for the winning category on starter `data/items.csv`. The process listens on 0.0.0.0 (or HOST) rather than localhost-only.

Worked example for starter `data/items.csv` (same CSV on every instance): skip header; skip `electronics,bad-row,not-a-number` and the empty-category `orphan` row (`skipped` = 2). Totals: electronics 65.50, grocery 13.00, hardware 24.50, office 2.00. Winner `group` is `electronics`, `total` 65.5 (65.50 acceptable). Judge greeting and port against the instance — not a fixed skeleton-only “Hello from C++” / port 3000.

## Method notes

Inspect leftover `CMakeLists.txt` and sources. Configure and build with CMake, run the binary from the workspace root so `data/items.csv` resolves, curl GET /, GET /health, and GET /summary on the instance default port. Confirm HTTP 200 (not leftover 501). Recompute `/summary` from the starter CSV using the documented rules. Confirm listen host is 0.0.0.0 (or HOST) in `src/main.cpp`. Judge against the instance package name / greeting / default port.

## Expected artifacts

- CMakeLists.txt plus C++ sources (required)
- Running server that serves the three GET routes
- Unchanged `data/items.csv` and vendored `include/httplib.h`

## Acceptable approaches

- Fill in the existing `handle_root` / `handle_health` / `handle_summary` functions and `cmake --build`
- Manual JSON strings via `ostringstream` / `set_content` (no extra JSON library required)
- Reading the CSV with `ifstream` from `data/items.csv` relative to the working directory
- Optional `PORT` / `HOST` environment overrides as already wired in `main.cpp`

## Failure signals

- Routes still 501, missing, or different from the documented JSON
- `/summary` hardcoded without matching the CSV algorithm, or CSV rewritten
- No CMake project / cannot rebuild from the desktop
- One-off `g++` that ignores `CMakeLists.txt`
- Listens only on 127.0.0.1 so a reviewer following the starter host cannot rely on 0.0.0.0
- Replaced cpp-httplib with another stack
