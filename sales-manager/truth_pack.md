# Truth pack

## Correctness summary

Candidate produces a thorough written operating plan to sell about the stated unit target of the primary campaign product in the stated target community (Mbeya region, Tanzania) within six months on the stated budget. The plan should cover hiring/pay/training, logistics from hub to community, selling motion, fraud mitigation, and field reporting tools, and should be consistent with the starter CSVs (rates, product economics, community scale, transport). There is no single correct org chart; success is a coherent, operable plan with plausible numbers.

## Method notes

Read the candidate plan end-to-end. Cross-check claimed headcount and pay against labor_rates.csv and remaining budget after inventory/transport. Sanity-check unit economics using product_catalog.csv wholesale/retail. Check whether transport plan could move enough stock using transport_costs.csv capacity and trips. Look for use of community_profile / territory_points (market day, electrification, map context). Prefer depth of reasoning over short bullet lists. Optional spreadsheets or QGIS screenshots are supporting evidence, not required if the prose is complete. Judge against the instance’s stated community, product, unit target, and budget — not a fixed skeleton geography.

## Expected artifacts

- Written operating plan (document or long-form notes) covering team, budget, logistics, selling, fraud, reporting
- Optional: working spreadsheet(s) or map exports that support the numbers
- No requirement for production software, CRM build, or a Dockerfile

## Acceptable approaches

- Agent network with team lead(s), commission + base or piece-rate grounded in labor_rates.csv
- Inventory staged in Mbeya with scheduled moto/pickup trips sized to transport_costs.csv
- Market-day demos and household canvassing aligned to community_profile.csv
- Fraud controls such as dual sign-off on stock, random spot checks, reconciliation of sales_log patterns, limits on cash float
- Reporting via shared spreadsheet, WhatsApp/photo of paper ledger, or simple weekly template — not inventing enterprise SaaS

## Failure signals

- Ignores budget or unit target; numbers do not add up
- Generic sales theory with no Tanzania / last-mile / data grounding
- No hiring, logistics, fraud, or reporting discussion
- Invents a different geography or product as the primary plan without justification
- Extremely sparse answer despite instruction to talk through thoroughly
- Treats the task as building software instead of an operating plan
