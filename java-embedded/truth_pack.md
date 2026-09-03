# Truth pack

## Correctness summary

Candidate implements the stubbed Java SIL client and HTTP handlers and runs the provided Maven controller against the provided board emulator. After `mvn compile`, starting `com.example.emu.BoardEmulator` then `com.example.app.Main` on the instance ports (see starter `Main.java` / `DeviceClient.java` / `data/scenario.md` / brief), GET / returns HTTP 200 with `ok` true and `message` equal to the instance greeting from the starter `Handlers.java` comment, GET /health returns HTTP 200 with status ok, GET /status returns HTTP 200 JSON for the last valid SENS and the trip command, and the emulator prints `SCENARIO PASS`. The HTTP process listens on 0.0.0.0 (or HOST) rather than localhost-only.

Worked example for the skeleton instance (`TEMP1` / `VENT1` / threshold `80.0` / HTTP `3000` / emulator `9100`; judge other instances against their factor values, not this skeleton):

- Emulator sends `SENS,TEMP1,72.5,28` then `SENS,TEMP1,75.0,2A` (nominal, both below threshold).
- Emulator then sends a high-value `SENS,TEMP1,85.0,<bad checksum>` (payload XOR is `25`; the emulator uses `00` or `FF` instead). This must be dropped.
- Emulator then sends valid `SENS,TEMP1,85.0,25`. Value `85.0` is strictly greater than `80.0`, so the client sends `CMD,VENT1,ON,73`.
- GET /status after the trip: `sensor` `TEMP1`, `value` 85.0 (85 / 85.00 acceptable), `command` `ON`, `linked` true.
- Emulator stdout includes `SCENARIO PASS`.

## Method notes

Inspect leftover `pom.xml` and sources. Compile with Maven from the workspace root. Start the emulator (`mvn -q compile exec:java -Dexec.mainClass=com.example.emu.BoardEmulator`), then the controller (`mvn -q exec:java`). Curl GET /, GET /health, and GET /status on the instance HTTP port. Confirm HTTP 200 (not leftover 501). Confirm emulator stdout `SCENARIO PASS` (not FAIL, and not a rewritten emulator). Recompute the trip: last valid SENS for the instance `sensor_id` with numeric value strictly greater than the instance threshold; command is `CMD,<actuator_id>,ON,<xor checksum>`. Confirm checksum validation via `Protocol.parseLine` / `checksumHex` (or equivalent XOR of payload bytes before the last comma). Confirm listen host is 0.0.0.0 (or HOST) in `Main.java`. Judge greeting, ports, sensor, actuator, and threshold against the instance — not a fixed skeleton-only “Battery thermal controller ready” / port 3000 / `TEMP1`.

## Expected artifacts

- pom.xml plus Java sources (required)
- Running emulator that prints `SCENARIO PASS`
- Running controller that serves GET /, GET /health, and GET /status
- Unchanged emulator sequence (`BoardEmulator.java` logic)

## Acceptable approaches

- Fill in the existing `DeviceClient` loop using the provided `in` / `out` streams and `Protocol` helpers, plus `Handlers.root` / `health` / `status`
- `mvn compile exec:java` with the documented main classes (or equivalent Maven / `java` on the compiled classes)
- Updating `Status` from the TCP thread so `/status` can read the same values
- Optional `PORT` / `HOST` / `EMU_PORT` environment overrides as already wired in `Main.java` / `DeviceClient.java` / `BoardEmulator.java`
- Optional VS Code “Emulator + App” compound debug config

## Failure signals

- Routes still 501, missing, or different from the documented JSON
- No `SCENARIO PASS` (timeout, `SCENARIO FAIL`, compile-only, or health-only)
- `/status` hardcoded without matching a valid trip SENS, or emulator rewritten to print PASS
- Command sent on the bad-checksum high-value frame or on nominal-only frames
- No Maven project / cannot rebuild from the desktop
- One-off `javac` that ignores `pom.xml`
- Only `createContext("/")` so `/health` and `/status` never dispatch
- Listens only on 127.0.0.1 so a reviewer following the starter host cannot rely on 0.0.0.0
- Replaced JDK HttpServer with Spring or another stack
