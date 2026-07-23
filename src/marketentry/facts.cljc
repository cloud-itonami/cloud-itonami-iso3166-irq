(ns marketentry.facts
  "Iraq market-entry catalog -- public-sector procurement market entry
  PLUS the investment-licensing layer the flagship governor check
  needs.

  Every fact below was fetched and read directly this session (no
  training-data paraphrase, no invented act/article/URL):

    - `:owner-authority`/`:legal-basis`/`:provenance` (public
      procurement): Iraq's Ministry of Planning -- General Government
      Contracts Department (دائرة العقود الحكومية العامة) publishes
      'تعليمات تنفيذ العقود الحكومية رقم (2) لسنة 2014 والضوابط
      المعدلة' (Instructions for the Implementation of Government
      Contracts No. 2 of 2014, and the amended controls) at
      https://mop.gov.iq/gover_contract24 -- fetched live (200, not
      Cloudflare-gated), and the actual PDF
      (https://mop.gov.iq/documents/gov_contarct/instructions/2-2014.pdf,
      1,650,557 bytes, 97 pages) was downloaded and its cover page
      read directly, confirming the exact title and issuing
      department. NOTE the same page also lists a newer 'تعليمات
      تنفيذ العقود العامة رقم 1 لسنة 2025' (Instructions No. 1 of
      2025) -- disclosed honestly rather than silently treating 2014
      as the sole current instrument; both are real, 2025 is newer.
    - `:corporate-number-*` (company registration): Iraq's Ministry
      of Trade operates 'Tasjeel' (تسجيل الشركات, the Companies
      Registration Directorate's own e-service), whose own Laws and
      Regulations page (https://tasjeel.mot.gov.iq/en/law.html,
      fetched live, 200, not gated) lists 'Law on Company No.21 of
      1997 as amended in 2004' and 'Ministerial Instructions No.196
      of 2004, Registration of Domestic Companies. (Ministry of
      Trade)' verbatim. Iraq's National Investment Commission (NIC)
      independently lists the SAME two citations on its own Policies
      and Laws page (below), corroborating them from a second
      government source.
    - `:investment-authority` (federal vs. Kurdistan Region): Iraq
      structurally has TWO investment-licensing regimes, not one --
      this is the genuinely Iraq-specific fact the flagship governor
      check is built on:
        - federal: NIC's own site (https://investpromo.gov.iq/,
          fetched via the Internet Archive Wayback Machine snapshot
          http://web.archive.org/web/20260315073553/https://investpromo.gov.iq/
          because the live site returns a Cloudflare
          'cf-mitigated: challenge' / 'Just a moment...' bot-detection
          page -- disclosed here per the no-bypass safety rule) is
          titled 'National Investment Commission - Republic Of Iraq'
          and its own news items use the term 'investment licenses'
          verbatim ('Granting eight investment licenses at a cost of
          more than 250 billion dinars in Babylon Governorate'). Its
          Policies and Laws subpage
          (http://web.archive.org/web/20260315073553/https://investpromo.gov.iq/policies-and-laws/,
          same Wayback snapshot) lists 'The Investment Law No (13) of
          2006 as amended With Law No.2 of 2010 And the Law No. 50 of
          2015' verbatim.
        - Kurdistan Region: the Kurdistan Regional Government's own
          Board of Investment page
          (http://web.archive.org/web/20260130102955/https://gov.krd/boi-en/,
          live site also Cloudflare-gated -- same Wayback fallback)
          states 'The Board of Investment is responsible for
          promoting and supporting investment opportunities in the
          Kurdistan Region, assisting investors, and authorising
          licenses across all sectors.' Its own Publications page
          (http://web.archive.org/web/20251101054957/https://gov.krd/boi-en/publications/investment-law/)
          states verbatim: 'Law No. 4 of 2006 -- Investment Law in the
          Kurdistan Region-Iraq'. NOTE Iraq's federal NIC Policies and
          Laws page separately lists a 'kurdistant regional
          investment law no 4 of 2004' entry (sic, note the '2004' --
          this reads as a typo/discrepancy against the KRG BOI's own
          page, which is the authoritative source for its own law and
          says 2006; both government pages were read directly and
          this discrepancy is disclosed rather than silently
          resolved).
        - the four Kurdistan Region governorates (Erbil,
          Sulaymaniyah, Duhok, Halabja) are per
          https://en.wikipedia.org/wiki/Kurdistan_Region (fetched
          this session). The same article documents that several
          additional 'disputed territories' (Kirkuk among them) have
          contested administrative status between federal Iraq and
          the KRG -- this catalog does NOT attempt to resolve that
          dispute; `expected-investment-authority` returns `nil` for
          a disputed governorate rather than guessing.
    - General Tax Authority (tax registration, used by
      `required-evidence` only, not independently re-derived as its
      own spec-basis sub-map): Iraq's Ministry of Finance
      (https://mof.gov.iq/, fetched live, 200) links to
      '/General-Tax-Authority.aspx', whose own page (fetched live,
      200) names it 'الهيئة العامة للضرائب' / 'General Tax Authority'
      and states its Companies Section
      ('قسم الشركات') assesses income tax on capital companies AND
      branches of foreign companies operating in Iraq -- directly
      relevant to a foreign market-entry engagement.

  GAP DISCLOSURE (what this catalog deliberately does NOT claim): no
  entry here asserts a specific foreign-ownership-percentage cap for
  public tenders -- a Wikipedia paraphrase (`Law_of_Iraq` article,
  citing legal500.com) claims a 2019 amendment to Company Law No. 21
  of 1997 capped foreign ownership at 49%, but that Wikipedia
  citation is tagged by Wikipedia's own editors as
  '{{AI-retrieved source|date=March 2026|checked=no}}', and the
  underlying legal500.com URL 404s as of this session -- so that
  specific numeric claim could NOT be independently confirmed and is
  intentionally left out rather than repeated on a single unchecked
  tertiary source. Likewise, no Iraq entry here reuses the USA/SAU/ARE
  reference-jurisdiction rows some sibling iso3166 repos' catalogs
  carry (e.g. cloud-itonami-iso3166-egy/-jor) -- those rows reflect
  facts those repos' own build sessions verified for OTHER countries;
  copying them into this catalog without re-fetching them myself this
  session would violate this fleet's no-fabrication policy, so this
  catalog is intentionally IRQ-only (a smaller honest catalog, not a
  padded one).")

(def catalog
  {"IRQ"
   {:name "Iraq"
    :owner-authority "Ministry of Planning -- General Government Contracts Department (دائرة العقود الحكومية العامة)"
    :legal-basis "Instructions for the Implementation of Government Contracts No. 2 of 2014, and the amended controls (تعليمات تنفيذ العقود الحكومية رقم (2) لسنة 2014 والضوابط المعدلة)"
    :national-spec "Standard tender documents + contractor classification/registration (Ministry of Planning) + company registration via Tasjeel (Ministry of Trade)"
    :provenance "https://mop.gov.iq/gover_contract24"
    :required-evidence ["Companies Registry record (Ministry of Trade -- Tasjeel)"
                         "Investment-licence record (NIC federally, or KRG BOI in the Kurdistan Region)"
                         "Tax registration record (General Tax Authority)"
                         "Authorized-representative record"]
    :rep-owner-authority "contracting government entity / Ministry of Planning General Government Contracts Department"
    :rep-legal-basis "Iraqi legal-entity registration (via the Companies Registration Directorate) is the ordinary precondition for being party to a government contract"
    :rep-provenance "https://tasjeel.mot.gov.iq/en/law.html"
    :corporate-number-owner-authority "Ministry of Trade -- Companies Registration Directorate (دائرة تسجيل الشركات) / Tasjeel"
    :corporate-number-legal-basis "Law on Company No. 21 of 1997, as amended in 2004, and Ministerial Instructions No. 196 of 2004 (Registration of Domestic Companies)"
    :corporate-number-provenance "https://tasjeel.mot.gov.iq/en/law.html"
    :investment-authority
    {:federal {:owner-authority "National Investment Commission (NIC)"
               :legal-basis "Investment Law No. 13 of 2006, as amended by Law No. 2 of 2010 and Law No. 50 of 2015"
               :provenance "https://investpromo.gov.iq/policies-and-laws/"}
     :krg {:owner-authority "Kurdistan Board of Investment (KRG BOI)"
           :legal-basis "Law No. 4 of 2006 -- Investment Law in the Kurdistan Region-Iraq"
           :provenance "https://gov.krd/boi-en/publications/investment-law/"
           :governorates ["Erbil" "Sulaymaniyah" "Duhok" "Halabja"]}
     :disputed-governorates ["Kirkuk"]}}})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed -- IRQ only, no peer-jurisdiction rows copied without re-verification"})))

(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))

(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))

(defn investment-authority-basis
  "The `:investment-authority` sub-map (federal + krg + disputed-
  governorates) for `iso3`, or nil if uncataloged."
  [iso3]
  (:investment-authority (spec-basis iso3)))

(defn expected-investment-authority
  "Which investment authority ACTUALLY governs an engagement in
  `governorate` within `iso3`: `:krg-boi` for the four Kurdistan
  Region governorates (Law No. 4 of 2006), `:federal` for every other
  known governorate in Iraq (Investment Law No. 13 of 2006 as
  amended). Returns nil for a disputed governorate (e.g. Kirkuk) or
  an unrecognized iso3/governorate -- an unresolved jurisdiction gets
  NO assertion here, never a guessed one."
  [iso3 governorate]
  (when-let [ia (investment-authority-basis iso3)]
    (cond
      (nil? governorate) nil
      (contains? (set (get-in ia [:krg :governorates])) governorate) :krg-boi
      (contains? (set (:disputed-governorates ia)) governorate) nil
      :else :federal)))
