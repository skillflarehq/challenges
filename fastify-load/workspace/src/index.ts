import Fastify from 'fastify'
import { summaryRoute } from './routes/summary.js'

const app = Fastify({ logger: true })

app.get('/', async () => {
  return { ok: true, message: 'Hello from Fastify' }
})

app.get('/health', async () => {
  return { status: 'ok' }
})

const start = async () => {
  try {
    await app.register(summaryRoute)
    const port = Number(process.env.PORT) || 3000
    const host = process.env.HOST || '0.0.0.0'
    await app.listen({ port, host })
  } catch (err) {
    app.log.error(err)
    process.exit(1)
  }
}

void start()
