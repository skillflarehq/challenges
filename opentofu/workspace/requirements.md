# Managed Postgres requirements

Target: Acme analytics Postgres (analytics warehouse staging)

| Parameter | Value |
|-----------|-------|
| Cluster name | `acme-analytics-pg` |
| Engine | `pg` (PostgreSQL) |
| Postgres version | `15` |
| Region | `nyc3` |
| Size slug | `db-s-1vcpu-1gb` |
| Node count | `1` |

Use OpenTofu with the DigitalOcean provider. Authenticate with your own API token (for example `DIGITALOCEAN_TOKEN` or a sensitive variable). Run `tofu init` and `tofu plan` only — do not apply.
