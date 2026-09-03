# SIL scenario — battery-thermal

Target: battery-thermal SIL controller (battery pack thermal trip / vent)

| Parameter | Value |
|-----------|-------|
| Sensor id | `TEMP1` |
| Unit | deg C |
| Trip threshold (strictly greater than) | `80.0` |
| Actuator id | `VENT1` |
| Trip command | `ON` |
| Emulator TCP port | `9100` |
| Controller HTTP port | `3000` |

Start the emulator first, then the controller (two terminals from this workspace root):

```bash
mvn -q compile exec:java -Dexec.mainClass=com.example.emu.BoardEmulator
mvn -q exec:java
```

Do not modify `BoardEmulator.java`. Implement `DeviceClient` and `Handlers`. Prove the loop with `GET /health`, `GET /status`, and the emulator's `SCENARIO PASS` line.
