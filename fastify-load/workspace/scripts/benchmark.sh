#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT"

PORT="${PORT:-3000}"
URL="http://127.0.0.1:${PORT}/summary"
OUT="${ROOT}/benchmark_result.json"

echo "Warming up and running 10s load test against ${URL}..."

npx autocannon \
  -c 50 \
  -d 10 \
  --json \
  "$URL" > "$OUT"

node --input-type=module <<'EOF'
import fs from 'node:fs'

const result = JSON.parse(fs.readFileSync('benchmark_result.json', 'utf8'))
const latency = result.latency ?? {}
const p975 = Number(latency.p97_5 ?? 9999)
const errors = Number(result.errors ?? 0)
const timeouts = Number(result.timeouts ?? 0)
const non2xx = Number(result.non2xx ?? 0)
const resets = Number(result.resets ?? 0)

console.log('---')
console.log(`p97.5 Latency: ${p975} ms`)
console.log(`Errors: ${errors}`)
console.log(`Timeouts: ${timeouts}`)
console.log(`Non-2xx: ${non2xx}`)
console.log(`Resets: ${resets}`)

let failed = false
if (errors + timeouts + non2xx + resets > 0) {
  console.log('FAIL: Service produced errors under load (crash / event loop lag).')
  failed = true
}
if (!(p975 < 20)) {
  console.log('FAIL: p97.5 latency must be under 20ms.')
  failed = true
}
if (failed) process.exit(1)
console.log('PASS: Load test SLA met.')
EOF
