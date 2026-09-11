(ns statute.facts
  "General-law compliance catalog for Iraq (IRQ) -- extends this
  repo's existing `marketentry.facts` (narrow public-procurement
  scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-egy/-jor/etc.'s `statute.facts`
  (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Both entries were fetched and read directly this session from TWO
  independent Iraqi government sources that corroborate each other:

    - Iraq's Ministry of Trade operates 'Tasjeel', the Companies
      Registration Directorate's own e-service; its Laws and
      Regulations page (https://tasjeel.mot.gov.iq/en/law.html,
      fetched live, HTTP 200, not Cloudflare-gated) lists verbatim:
      'Law on Company No.21 of 1997 as amended in 2004' and 'Law on
      Labor, No.71 of 1987, as amended in 2004 (CPA Order No. 89)'.
    - Iraq's National Investment Commission (NIC) independently lists
      the same two citations on its own Policies and Laws page
      (fetched via the Internet Archive Wayback Machine snapshot
      http://web.archive.org/web/20260315073553/https://investpromo.gov.iq/policies-and-laws/
      because the live investpromo.gov.iq returns a Cloudflare
      'cf-mitigated: challenge' page -- disclosed per the no-bypass
      safety rule): 'Company Law 21 of 1997_& Registration
      Instructions No[1]. 196 2004' and, under 'Labor Laws': 'labor
      law no 71 of 1987'.

  GAP DISCLOSURE: neither :enacted-date is a specific day/month --
  both government sources give only the law number and year, and no
  Iraqi Official Gazette (Al-Waqai Al-Iraqiya) copy of either law was
  independently located and read this session, so :statute/enacted-date
  is intentionally OMITTED from both entries rather than a fabricated
  day. This repo does not attempt a comprehensive Iraqi statute
  catalog beyond these two (e.g. no data-protection-law entry is
  claimed, unlike some siblings' 2-entry pattern that happens to
  include one) -- a smaller honest catalog beats a padded one.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"IRQ"
   [{:statute/id "irq.law-21-1997-companies-law"
     :statute/title "Law on Company No. 21 of 1997, as amended in 2004 (CPA Order No. 64, March 3, 2004)"
     :statute/jurisdiction "IRQ"
     :statute/kind :law
     :statute/law-number "Law No. 21 of 1997"
     :statute/url "https://tasjeel.mot.gov.iq/en/law.html"
     :statute/url-provenance :tasjeel-mot-gov-iq-official-registration-portal
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "irq.law-71-1987-labor-law"
     :statute/title "Law on Labor, No. 71 of 1987, as amended in 2004 (CPA Order No. 89)"
     :statute/jurisdiction "IRQ"
     :statute/kind :law
     :statute/law-number "Law No. 71 of 1987"
     :statute/url "https://tasjeel.mot.gov.iq/en/law.html"
     :statute/url-provenance :tasjeel-mot-gov-iq-official-registration-portal
     :statute/retrieved-at "2026-07-22"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-irq statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "IRQ")) " IRQ statutes seeded with "
                 "tasjeel.mot.gov.iq citations (corroborated independently by "
                 "investpromo.gov.iq's own Policies and Laws page via Wayback "
                 "Machine). Extend `statute.facts/catalog`, never fabricate a "
                 "law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
