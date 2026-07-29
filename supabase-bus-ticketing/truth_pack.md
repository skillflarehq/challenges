# Truth pack

## Correctness summary

Candidate builds a greenfield Vue + Supabase passenger booking MVP for Kenya intercity buses. A reviewer can search trips on a Kenya corridor (KES), authenticate, complete a booking, and see it persisted. Schema and RLS are coherent; seat/capacity oversell is mitigated rather than ignored.

## Method notes

1. Read the candidate README for how to start Supabase (CLI local or hosted) and the Vue app; use their documented env setup — do not invent a different stack.
2. Create or use a test user via Supabase Auth; confirm the Vue app signs in (or signs up) successfully.
3. Search for trips using seeded Kenya routes; confirm results come from Supabase (not hard-coded mock arrays with no backend).
4. Complete a booking; inspect the bookings (or tickets) table for a new row owned by that user.
5. Spot-check RLS: as anon or as a second user, attempt to read/write another user’s bookings — expect denial. Open `WITH CHECK (true)` / `USING (true)` on booking writes = fail Auth/RLS.
6. Spot-check integrity: try booking the same seat twice or exceeding capacity; expect a constraint, RPC failure, or explicit hold — silent double success = weak integrity.
7. Confirm no service-role key or password is committed in the repo; anon key in `.env.example` style docs is fine if not a production secret dump.

## Expected artifacts

- Vue application source that talks to Supabase
- SQL migrations, schema dump, or equivalent applyable schema (tables + RLS policies)
- README or run notes covering frontend + backend setup
- Seeded or manually insertable Kenya trip data sufficient for a demo
- No requirement for Docker/Kubernetes, payment providers, or operator/fleet tooling

## Acceptable approaches

- Vue 3 + Vite (or similar) with `@supabase/supabase-js` called directly from the client
- Optional Edge Function or Postgres RPC for atomic seat allocation
- Seat map UI or a simple remaining-capacity / auto-assigned seat model
- Local `supabase start` with migrations, or a hosted Supabase free project with documented URL + anon key setup
- Confirmed booking rows without payment, or explicit “held” status pending payment stub

## Failure signals

- Frontend-only mock with no real Supabase persistence
- RLS disabled or policies that allow anyone to insert/select all bookings
- No mitigation for double-booking / overselling capacity
- Primary UI is React/Next (or similar) instead of Vue
- Rabbit hole on payment gateways, GPS tracking, or multi-operator marketplace with no working search → book path
- Empty, unrunnable, or undocumented deliverable; service-role secrets committed to the repo
