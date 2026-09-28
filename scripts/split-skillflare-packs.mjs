/**
 * Split each active pack's skillflare.json into requirements.json, challenge.json, and variation.json.
 * Run from the challenges repo: node scripts/split-skillflare-packs.mjs
 */
import fs from "node:fs"
import path from "node:path"

const root = path.resolve(import.meta.dirname, "..")
const skip = new Set(["deprecated", "scripts", "node_modules", ".git"])
const skipDir = new Set(["node_modules", ".git", "dist", "build", ".gradle"])

function walkFiles(dir, prefix = "") {
  const out = []
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    if (skipDir.has(entry.name)) continue
    const rel = prefix ? `${prefix}/${entry.name}` : entry.name
    const abs = path.join(dir, entry.name)
    if (entry.isDirectory()) out.push(...walkFiles(abs, rel))
    else if (entry.isFile()) out.push(rel)
  }
  return out
}

function roleToJd(role) {
  const lines = [
    role.title,
    role.seniority,
    role.company_context,
    "",
    "Day to day:",
    ...(role.day_to_day ?? []).map((line) => `- ${line}`),
    "",
    "Required skills:",
    ...(role.required_skills ?? []).map((line) => `- ${line}`),
    "",
    "Nice to have:",
    ...(role.nice_to_have_skills ?? []).map((line) => `- ${line}`),
    "",
    "Constraints:",
    ...(role.constraints ?? []).map((line) => `- ${line}`),
  ]
  return lines.filter((line, index, all) => line !== "" || all[index - 1] !== "").join("\n").trim()
}

function externalizeLargeValues(packDir, variation) {
  const factors = variation?.factors
  if (!Array.isArray(factors)) return variation
  for (const factor of factors) {
    if (!factor || !Array.isArray(factor.options)) continue
    for (const option of factor.options) {
      if (!option?.values || typeof option.values !== "object") continue
      for (const [key, value] of Object.entries(option.values)) {
        if (typeof value !== "string" || value.length < 1500) continue
        const rel = `generation/${factor.id}/${option.id}/${key}.txt`
        const abs = path.join(packDir, "workspace", rel)
        fs.mkdirSync(path.dirname(abs), { recursive: true })
        fs.writeFileSync(abs, value)
        option.values[key] = `@file:${rel}`
      }
    }
  }
  return variation
}

function writeJson(file, value) {
  fs.writeFileSync(file, `${JSON.stringify(value, null, 2)}\n`)
}

const packs = fs.readdirSync(root, { withFileTypes: true })
  .filter((entry) => entry.isDirectory() && !skip.has(entry.name) && !entry.name.startsWith("."))
  .map((entry) => entry.name)

for (const name of packs) {
  const packDir = path.join(root, name)
  const manifestPath = path.join(packDir, "skillflare.json")
  if (!fs.existsSync(manifestPath)) continue
  const manifest = JSON.parse(fs.readFileSync(manifestPath, "utf8"))
  const role = manifest.role ?? {}
  const problem = manifest.problem_statement ?? {}
  const variation = externalizeLargeValues(packDir, structuredClone(manifest.variation ?? {}))
  const compute = manifest.compute_provider === "local" || manifest.compute_provider === "vm"
    ? manifest.compute_provider
    : "container"

  const requirements = {
    schema_version: 1,
    source_url: null,
    jd: roleToJd(role),
    prompt: typeof problem.summary === "string" ? problem.summary : "",
  }

  const workspace = fs.existsSync(path.join(packDir, "workspace"))
    ? walkFiles(path.join(packDir, "workspace")).filter((rel) => !rel.startsWith("generation/"))
    : []

  const challenge = {
    schema_version: 1,
    title: manifest.title,
    slug: manifest.slug,
    version: manifest.version ?? 1,
    image: manifest.image,
    compute_provider: compute,
    machine_size: manifest.machine_size ?? "c3-standard-4",
    ...(typeof manifest.needs_dind === "boolean" ? { needs_dind: manifest.needs_dind } : {}),
    duration_minutes: manifest.duration_minutes,
    skills_assessed: manifest.skills_assessed ?? [],
    tools: Array.isArray(manifest.tools) ? manifest.tools : [],
    before_you_start: Array.isArray(manifest.before_you_start) ? manifest.before_you_start : [],
    ...(compute === "local" ? { prep_links: manifest.prep_links ?? [] } : {}),
    role: {
      title: role.title ?? "",
      seniority: role.seniority ?? "",
      company_context: role.company_context ?? "",
      day_to_day: role.day_to_day ?? [],
      required_skills: role.required_skills ?? [],
      nice_to_have_skills: role.nice_to_have_skills ?? [],
      constraints: role.constraints ?? [],
    },
    rubric: manifest.rubric ?? [],
    ...(manifest.evaluation ? { evaluation: manifest.evaluation } : {}),
    generation: {
      mode: "combinatorial",
      instructions: "Pick one unused valid factor combination and render generation.apply into the locked problem and starter files.",
      invariants: Array.isArray(variation.invariants) ? variation.invariants : [],
      factors: variation.factors ?? [],
      constraints: variation.constraints ?? [],
      apply: variation.apply,
    },
  }

  const locked = {
    schema_version: 1,
    title: manifest.title,
    slug: manifest.slug,
    version: manifest.version ?? 1,
    image: manifest.image,
    compute_provider: compute,
    machine_size: manifest.machine_size ?? "c3-standard-4",
    ...(typeof manifest.needs_dind === "boolean" ? { needs_dind: manifest.needs_dind } : {}),
    duration_minutes: manifest.duration_minutes,
    skills_assessed: manifest.skills_assessed ?? [],
    tools: Array.isArray(manifest.tools) ? manifest.tools : [],
    before_you_start: Array.isArray(manifest.before_you_start) ? manifest.before_you_start : [],
    ...(compute === "local" ? { prep_links: manifest.prep_links ?? [] } : {}),
    role: challenge.role,
    problem_statement: problem,
    rubric: manifest.rubric ?? [],
    ...(manifest.evaluation ? { evaluation: manifest.evaluation } : {}),
    assets: workspace.map((rel) => ({ path: rel })),
  }

  writeJson(path.join(packDir, "requirements.json"), requirements)
  writeJson(path.join(packDir, "challenge.json"), challenge)
  writeJson(path.join(packDir, "variation.json"), locked)
  fs.unlinkSync(manifestPath)

  const readmePath = path.join(packDir, "README.md")
  if (fs.existsSync(readmePath)) {
    let readme = fs.readFileSync(readmePath, "utf8")
    if (!readme.includes("requirements.json")) {
      readme += `

## Layers

This pack is three self-contained files:

| Path | Role |
|------|------|
| \`requirements.json\` | Job description text and author prompt |
| \`challenge.json\` | Metadata, skills, tools, video rubric, and combinatorial generation |
| \`variation.json\` | One locked instance (problem statement and workspace asset paths) |
| \`truth_pack.md\` | Grader mark scheme, used with \`variation.json\` |
| \`workspace/\` | Starter files named by \`variation.json\` assets |
`
      fs.writeFileSync(readmePath, readme)
    }
  }
  console.log(name)
}
