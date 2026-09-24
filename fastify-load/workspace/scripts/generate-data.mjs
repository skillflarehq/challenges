import fs from 'node:fs'
import path from 'node:path'
import { fileURLToPath } from 'node:url'

const root = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..')
const dataDir = path.join(root, 'data')
const csvPath = path.join(dataDir, 'items.csv')

const ROW_COUNT = 1_500_000
const SEED = 20260914
const MIN_BYTES = 10_000_000

if (fs.existsSync(csvPath) && fs.statSync(csvPath).size >= MIN_BYTES) {
  console.log(`data/items.csv already present (${fs.statSync(csvPath).size} bytes)`)
  process.exit(0)
}

fs.mkdirSync(dataDir, { recursive: true })

function mulberry32(seed) {
  let a = seed >>> 0
  return function rand() {
    a |= 0
    a = (a + 0x6d2b79f5) | 0
    let t = Math.imul(a ^ (a >>> 15), 1 | a)
    t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296
  }
}

const rand = mulberry32(SEED)

function pickCategory() {
  const r = rand()
  if (r < 0.4) return 'electronics'
  if (r < 0.65) return 'grocery'
  if (r < 0.85) return 'hardware'
  return 'office'
}

console.log(`Generating ${ROW_COUNT} rows at ${csvPath}...`)

const out = fs.createWriteStream(csvPath)
out.write('category,amount\n')

for (let i = 0; i < ROW_COUNT; i++) {
  const roll = rand()
  if (roll < 0.001) {
    out.write(`,${(1 + rand() * 99).toFixed(2)}\n`)
    continue
  }
  if (roll < 0.002) {
    out.write(`${pickCategory()},not-a-number\n`)
    continue
  }
  out.write(`${pickCategory()},${(1 + rand() * 99).toFixed(2)}\n`)
}

await new Promise((resolve, reject) => {
  out.on('error', reject)
  out.end(resolve)
})

console.log(`Wrote ${fs.statSync(csvPath).size} bytes`)
