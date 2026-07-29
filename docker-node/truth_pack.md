# Truth pack

## Correctness summary

Candidate produces a working container image for the provided Fastify app. After build and run with a published host port, GET / returns the existing hello payload and GET /health returns status ok. Final image prefers a multi-stage or otherwise lean Node base and does not require root for the app process when feasible.

## Method notes

Inspect Dockerfile (and optional compose). Build the image, run with a port map, curl / and /health. Check USER instruction or equivalent non-root practice. Confirm package-lock or npm ci usage for reproducible installs.

## Expected artifacts

- Dockerfile (required)
- Optional docker-compose.yml or equivalent run docs
- Built image that serves the Fastify routes

## Acceptable approaches

- Multi-stage Alpine or slim Node build with npm ci and CMD node index.js
- Single-stage lean base if justified and still small enough
- Compose that builds from the desktop context and publishes one host port

## Failure signals

- App routes broken or different from starter behavior
- No Dockerfile / cannot rebuild from desktop
- Bloaty full OS base with no rationale
- Runs only via host node, never via container
- Listens only on localhost inside the container so port publish fails
