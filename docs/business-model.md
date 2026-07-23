# Business Model: Independent Public-Sector Market-Entry & Procurement Compliance Service — Iraq

## Classification

- Repository: `cloud-itonami-iso3166-irq`
- ISO 3166: `IRQ` (Iraq)
- Activity: public-procurement market-entry and ongoing regulatory-
  compliance navigation for an already-incorporated operator, across
  BOTH of Iraq's investment-licensing regimes (federal and Kurdistan
  Region)
- Social impact: broadening SME/foreign-vendor access to Iraqi public
  contracts while keeping every claimed requirement traceable to an
  official source

## Customer

- an already-incorporated operator wanting to bid on an Iraqi
  government contract (federal ministries/agencies, or a Kurdistan
  Regional Government entity)
- a foreign SME or civic-tech vendor entering the Iraqi public sector
  for the first time, in either federal Iraq or the Kurdistan Region
- a client that has just completed Companies Registration Directorate
  (Tasjeel) registration and now needs public-sector market access

## Offer

- registration walkthrough for Iraq's government-contracts framework:
  the Ministry of Planning's General Government Contracts Department
  (دائرة العقود الحكومية العامة) and its 'Instructions for the
  Implementation of Government Contracts No. 2 of 2014' (and amended
  controls), including standard tender documents and contractor
  classification/registration
- business registration checklist: Ministry of Trade company
  registration via Tasjeel (Law on Company No. 21 of 1997, as amended
  in 2004, plus Ministerial Instructions No. 196 of 2004) and General
  Tax Authority (Ministry of Finance) taxpayer registration
- investment-licence navigation across BOTH of Iraq's regimes: the
  National Investment Commission federally (Investment Law No. 13 of
  2006, as amended by Law No. 2 of 2010 and Law No. 50 of 2015), or
  the Kurdistan Board of Investment in the Kurdistan Region's four
  governorates -- Erbil, Sulaymaniyah, Duhok, Halabja (Law No. 4 of
  2006) -- correctly distinguished per engagement, never conflated
- ongoing regulatory-change monitoring subscription
- compliance-audit export package for the client's own records

## Revenue

- per-engagement market-entry fee (one-time registration + checklist
  completion)
- recurring regulatory-change monitoring subscription
- compliance-audit export package

## Trust Controls

- any actual government-contract filing draft or submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off (`:filing/draft`/`:filing/submit` are never
  automated at any phase)
- a false or fabricated regulatory-requirement claim is a HARD hold
  that cannot be overridden by human approval alone — it must be
  corrected against a cited official source first
- citing the WRONG investment authority for an engagement's actual
  governorate (federal National Investment Commission vs. Kurdistan
  Board of Investment) is an independent HARD hold — this vertical's
  flagship check, because Iraq structurally has two licensing regimes
  over two territories, not one
- this service does **not** provide legal or tax advice; characterization
  and filing on the client's behalf beyond checklist/draft assistance
  routes to Iraqi-licensed counsel or a registered agent (federal, or
  Kurdistan Region counsel where the KRG BOI regime applies)
- every requirement cites the official ministry/authority or
  regulation, never invented
- a disputed-territory governorate (e.g. Kirkuk) gets NO automated
  investment-authority assertion — this catalog does not resolve
  contested federal/KRG jurisdiction, and neither does the governor

## Boundary with adjacent actors (read before forking)

- **`cloud-itonami-iso3166-jor`/`-egy`/etc.**: sibling jurisdiction-
  specific blueprints for OTHER countries in this same iso3166 family
  — same governed-actor pattern, different national/regional legal
  facts. This blueprint's facts are IRQ-only; it does not copy another
  sibling's jurisdiction rows.
- **`cloud-itonami-cofog-{code}`**: a jurisdiction-agnostic operator
  template for ONE public function. This blueprint is the orthogonal
  jurisdiction-specific axis — the two compose (fork a COFOG-function
  blueprint AND this one to operate in Iraq).
- **`cloud-itonami-M6910`** (where it exists for a given jurisdiction):
  helps a client BECOME a legal entity (incorporation) — a prior,
  different regulatory phase (company law). This blueprint assumes
  incorporation (Companies Registration Directorate / Tasjeel) is
  already done and handles public-procurement market entry (a
  different regulatory domain).
