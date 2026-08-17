# Truth pack

## Correctness summary

Candidate produces an integrated three-statement forecast for the instance company (default skeleton: **Northline Precision Components**) under the instance scenario (default: **base growth**). The deliverable should (1) show that historical statements integrate, (2) apply the provided forecast drivers for FY2025–FY2029, and (3) report Y5 revenue, Y5 net income, Y5 ending cash, cumulative 5-year FCF (CFO + CFI), and Y5 net cash (cash − debt). There is no requirement for a DCF or valuation.

### Skeleton instance reference (Northline × base growth)

Numeric bands below apply to the **default skeleton only**: Northline Precision Components with the base-growth driver file in `workspace/`. For other combinatorial instances, judge **method and driver application first**; do not force these skeleton bands onto a different company or scenario.

Policy used for the reference run (must match `forecast_drivers.txt`):

- Revenue growth Y1–Y5: 6%, 6%, 5%, 5%, 4%
- Gross margin: 38.0%, 38.0%, 38.5%, 38.5%, 39.0%
- OpEx ex-D&A (% sales): 20.0%, 20.0%, 19.5%, 19.5%, 19.0%
- Tax 25% of EBT; dividends 25% of NI
- D&A = 12.5% × beginning net PP&E
- CapEx = 6.3%, 5.6%, 4.9%, 4.5%, 4.2% of that year’s revenue
- DSO/DIO/DPO = 45 / 60 / 35
- Interest Y1–Y5: 560, 540, 520, 500, 480
- Debt paydown $500k each year; common stock flat at $5,000k
- Rounding: nearest USD thousand on statement lines

| Quantity | Reference | Acceptable band |
|----------|-----------|-----------------|
| Y1 revenue | 50,880 | ± 50.5k–51.2k |
| Y5 revenue | 61,840 | ≈ 61.0k–62.7k (±2%) |
| Y5 net income | 6,970 | ≈ 6.6k–7.3k (±5%; tax/interest timing may differ slightly) |
| Y5 ending cash | 21,384 | ≈ 20.0k–22.8k (±7%; WC rounding and interest schedule sensitivity) |
| Cumulative 5-year FCF (Σ CFO+CFI) | 24,647 | ≈ 23.0k–26.5k |
| Y5 debt | 4,500 | Exact if $500k paydown/year from FY2024 debt 7,000 |
| Y5 net cash (cash − debt) | ≈ 16,884 | ≈ 15.5k–18.3k |
| Each forecast year: BS balances | Yes | Required |
| RE rollforward | Yes | ending RE = beg RE + NI − dividends |
| Cash bridge | Yes | ending cash = beg cash + CFO + CFI + CFF |

Approximate year-by-year skeleton path (USD thousands; ± small rounding):

| Year | Revenue | NI | Ending cash | CapEx | FCF (CFO+CFI) |
|------|---------|----|-------------|-------|---------------|
| Y1 FY2025 | 50,880 | 4,658 | 8,075 | 3,205 | 3,363 |
| Y2 FY2026 | 53,933 | 5,008 | 10,295 | 3,020 | 3,972 |
| Y3 FY2027 | 56,630 | 5,763 | 13,471 | 2,775 | 5,117 |
| Y4 FY2028 | 59,462 | 6,161 | 17,031 | 2,676 | 5,600 |
| Y5 FY2029 | 61,840 | 6,970 | 21,384 | 2,597 | 6,595 |

## Method notes

1. **History check** — Confirm FY2022–FY2024 BS balances; RE moves with NI − dividends; cash flow ending cash matches BS cash.
2. **Revenue** — Apply YoY growth to FY2024 revenue (skeleton 48,000) for each forecast year.
3. **COGS / gross profit** — COGS = revenue × (1 − gross margin); GP = revenue − COGS.
4. **OpEx** — OpEx ex-D&A = revenue × opex %.
5. **D&A** — D&A_t = 12.5% × beginning net PP&E; then Net PP&E_t = beg + CapEx − D&A.
6. **Interest / tax / NI** — Use the stated interest schedule; tax = 25% × EBT; NI = EBT − tax.
7. **NWC** — AR = rev × DSO/365; Inventory = COGS × DIO/365; AP = COGS × DPO/365.
8. **Cash flow** — CFO ≈ NI + D&A − ΔAR − ΔInv + ΔAP; CFI = −CapEx; CFF = debt paydown − dividends (no new equity).
9. **Cash plug** — Ending cash from the CF bridge; BS must still balance (it will if RE, PP&E, NWC, and debt follow the policies).
10. **FCF** — For this pack, FCF = CFO + CFI (before financing). Cumulative FCF = sum over Y1–Y5.

Inspect the workbook for driver fidelity first, then compare headline outputs to the skeleton bands only for Northline × base growth. Alternate rounding (e.g. DSO with 365.25, or mid-year CapEx conventions) is acceptable if disclosed and the model still integrates.

## Expected artifacts

- Spreadsheet and/or completed `model_template.md` covering IS, BS, and CF for the forecast years
- Brief assumption / sensitivity notes
- Optional: history reconciliation tab or checks
- No requirement for Dockerfile, application code, DCF, or comps

## Acceptable approaches

- Full linked Excel / LibreOffice model with a drivers tab
- Completing `model_template.md` with clear numeric tables plus a supporting sheet
- Computing NWC from days formulas or from equivalent % of sales if the days math is shown
- Treating FCF as CFO − CapEx when CFI is only CapEx
- Minor rounding differences vs the reference table if integration checks pass and drivers are followed
- An explicitly labeled upside/downside case **in addition to** the required primary driver case

## Failure signals

- Balance sheet does not balance in forecast years
- Net income never reaches retained earnings (or dividends ignored with no note while RE still jumps incorrectly)
- Ending cash invented without a CF bridge
- Ignores provided growth / margin / CapEx / NWC drivers
- Builds only an income statement (no BS/CF)
- Turns the task into a DCF, LBO, or comps valuation as the primary deliverable
- Turns the task into software / dashboard engineering instead of a financial model
- Grades against a different company or scenario than the instance brief / starter files without saying so
