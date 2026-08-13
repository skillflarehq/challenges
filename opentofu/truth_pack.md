# Truth pack

## Correctness summary

Candidate authors an OpenTofu root module that would create a DigitalOcean managed PostgreSQL cluster matching the instance requirements on the desktop (`requirements.md`: cluster name, region, size slug, Postgres version, node count). They authenticate with their own DigitalOcean API token (environment variable and/or sensitive variable / local tfvars — not hardcoded in deliverable `.tf` files). Success is a completed `tofu init` and successful `tofu plan` that plans creation of `digitalocean_database_cluster` with `engine = "pg"` (or equivalent managed Postgres). `tofu apply` / live cluster creation is out of scope and must not be the completion path.

## Method notes

Inspect `.tf` files on the desktop. Confirm DigitalOcean provider setup and a managed database cluster resource with engine Postgres and parameters matching the instance scenery (not a fixed skeleton-only name/region). Prefer evidence of a successful `tofu plan` in the recording or narrative. Check that tokens are not left hardcoded in committed deliverables. Judge plan-only discipline: apply as the required finish = fail the dry-run / discipline criteria.

## Expected artifacts

- OpenTofu `.tf` files (required) declaring provider + managed Postgres cluster
- Evidence of successful `tofu plan` (required for dry-run criterion)
- Optional short notes on how to set the token; optional `.gitignore` for tfvars/state

## Acceptable approaches

- Root module with `required_providers` for `digitalocean/digitalocean`, provider block, and `digitalocean_database_cluster` with `engine = "pg"`
- Token via `DIGITALOCEAN_TOKEN`, `TF_VAR_…`, or sensitive variable + gitignored `*.tfvars`
- Single-file or multi-file HCL layout as long as a reviewer can init/plan from the desktop path

## Failure signals

- No successful plan / only validate without plan when plan was required
- `tofu apply` or live cluster treated as how they finished
- Wrong engine (e.g. MySQL/Valkey), droplet self-hosted Postgres, or non-DigitalOcean cloud
- Parameters that do not match the instance `requirements.md`
- API token hardcoded in `.tf` left on the desktop
- No `.tf` deliverable / cannot reconstruct how to plan
