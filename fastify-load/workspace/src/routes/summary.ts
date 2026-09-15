import type { FastifyPluginAsync } from 'fastify'
import fs from 'node:fs'
import path from 'node:path'

export const summaryRoute: FastifyPluginAsync = async (app) => {
  app.get('/summary', async () => {
    const csvPath = path.resolve('data/items.csv')

    const fileContent = fs.readFileSync(csvPath, 'utf8')

    const lines = fileContent.split('\n')
    const totals = new Map<string, number>()
    let skipped = 0

    for (let i = 1; i < lines.length; i++) {
      const line = lines[i]!.trim()
      if (!line) continue

      const match = line.match(/^([^,]+),([0-9.]+)/)
      if (!match) {
        skipped++
        continue
      }

      const [, category, rawAmount] = match
      const amount = parseFloat(rawAmount!)
      totals.set(category!, (totals.get(category!) || 0) + amount)
    }

    let winner = { group: '', total: -1 }
    for (const [group, total] of totals.entries()) {
      if (total > winner.total || (total === winner.total && group < winner.group)) {
        winner = { group, total }
      }
    }

    return { ...winner, skipped }
  })
}
