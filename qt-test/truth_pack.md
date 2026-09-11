# Truth pack

## Correctness summary

Candidate implements the stubbed Qt Widgets `populate()` and `fromCsv`, then builds the provided CMake Qt 6 project. After configure/build and run of `./build/app` from the workspace root, Home shows the instance greeting from the starter `src/mainwindow.cpp` comment, Status shows the instance `status_text`, and Summary displays group, total, and skipped for the winning category on starter `data/items.csv`. The leftover project is the CMake Qt Widgets app (Home / Status / Summary tabs remain). The process is a desktop window, not an HTTP server.

Worked example for starter `data/items.csv` (same CSV on every instance): skip header; skip `electronics,bad-row,not-a-number` and the empty-category `orphan` row (`skipped` = 2). Totals: electronics 65.50, grocery 13.00, hardware 24.50, office 2.00. Winner `group` is `electronics`, `total` 65.5 (65.50 acceptable). Judge greeting and status copy against the instance — not a fixed skeleton-only “Hello from Qt” / `ok`.

## Method notes

Inspect leftover `CMakeLists.txt` and sources. Configure and build with CMake (`cmake -S . -B build && cmake --build build`), run `./build/app` from the workspace root so `data/items.csv` resolves, and check Home / Status / Summary tabs. Confirm leftover “not implemented” is gone. Recompute Summary from the starter CSV using the documented rules. Confirm `find_package(Qt6 REQUIRED COMPONENTS Widgets)` remains. Judge against the instance package name / greeting / status copy.

## Expected artifacts

- CMakeLists.txt plus Qt Widgets sources (required)
- Running window that shows the three tabs
- Unchanged `data/items.csv` and `ui/mainwindow.ui`

## Acceptable approaches

- Fill in the existing `MainWindow::populate()` and `fromCsv`, then `cmake --build`
- `QFile` / `ifstream` of `data/items.csv` relative to the working directory, then `fromCsv`
- Extra label prefixes (“Group: electronics”) as long as the values are visible
- Optional Qt Creator kit / VS Code CMake Tools; neither is required if the CMake CLI path works

## Failure signals

- Tabs still say “not implemented”, missing, or different from the documented greeting / status / summary
- Summary hardcoded without matching the CSV algorithm, or CSV rewritten
- No CMake project / cannot rebuild from the desktop
- One-off `g++` that ignores `CMakeLists.txt`
- Dropping a Home / Status / Summary tab so a destination never appears
- Replaced Qt Widgets with a QML-only rewrite, GTK, Electron, or a from-scratch toolkit
