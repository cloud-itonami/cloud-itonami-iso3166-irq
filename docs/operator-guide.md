# Operator Guide

## First Deployment

1. Confirm the client's incorporation/legal-entity status is complete
   via the Ministry of Trade's Companies Registration Directorate
   (Tasjeel) — route to local counsel first if not.
2. Register the client's intake: business type, target public
   function, prior filing history in Iraq if any, and the GOVERNORATE
   the engagement actually operates in (this determines which
   investment authority applies).
3. Run the advisor in read-only mode against the Ministry of
   Planning's General Government Contracts Department framework
   ('Instructions for the Implementation of Government Contracts
   No. 2 of 2014' and amended controls).
4. Compare the checklist against the client's current documentation
   (Tasjeel company-registry entry, General Tax Authority tax
   registration, and the correct investment-licence authority for the
   engagement's governorate).
5. Enable gated filing-draft assistance once the Market-Entry
   Compliance Governor contract is trusted; actual submission always
   requires human sign-off.

## Federal vs. Kurdistan Region — do not conflate

Iraq has TWO investment-licensing regimes:

- **Federal Iraq** (everywhere except the Kurdistan Region): National
  Investment Commission (NIC), Investment Law No. 13 of 2006, as
  amended by Law No. 2 of 2010 and Law No. 50 of 2015.
- **Kurdistan Region** (Erbil, Sulaymaniyah, Duhok, Halabja
  governorates): Kurdistan Board of Investment (KRG BOI), Law No. 4
  of 2006 ('Investment Law in the Kurdistan Region-Iraq').

Before drafting or submitting a filing, confirm which authority the
engagement's actual governorate falls under. The governor's flagship
`krg-authority-mismatch` check independently re-verifies this and
HARD-holds a mismatch — it cannot be overridden by human approval
alone, the citation must be corrected first. A disputed governorate
(e.g. Kirkuk) is out of scope for this automated check; route those
engagements to counsel for a manual jurisdiction determination.

## Minimum Production Controls

- client-owned data store for business/tax registration documents
- clear provenance (official ministry/authority citation) for every
  requirement surfaced
- approval workflow for any government-contract filing draft or
  submission
- named referral relationship with Iraqi-licensed counsel or a
  registered agent for anything beyond checklist/draft assistance
  (federal counsel, or Kurdistan Region counsel where KRG BOI applies)
- monthly audit export

## Certification

Certified operators must prove data provenance, audit traceability,
that automated actions cannot bypass the Market-Entry Compliance
Governor, correct federal-vs-Kurdistan-Region authority routing, and a
working referral relationship with Iraqi-licensed counsel or a
registered agent for whatever licensed representation the law of Iraq
requires for actual public-procurement filings.
