(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Iraqi procurement/investment law, whether an engagement's
  claimed governorate actually falls under the investment authority it
  cited, whether a claimed engagement fee actually equals base +
  months x rate, whether a Companies Registration Directorate number
  has been verified for a filing that requires it, or when a draft
  stops being a draft and becomes a real-world government-contract
  submission, so this MUST be a separate system able to *reject* a
  proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  Seven checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. KRG/federal authority
       mismatch                     -- for `:filing/submit`,
                                       INDEPENDENTLY verify the
                                       engagement's own
                                       `:cited-authority` (`:federal`
                                       or `:krg-boi`) matches
                                       `marketentry.facts/expected-
                                       investment-authority` for its
                                       `:governorate`. FLAGSHIP
                                       genuinely new check for the
                                       iso3166 family (grep-verified
                                       absent as a governor check
                                       function name fleet-wide at
                                       build time). Grounded in the
                                       fact that Iraq structurally has
                                       TWO investment-licensing
                                       regimes over two territories --
                                       the National Investment
                                       Commission federally
                                       (Investment Law No. 13 of 2006,
                                       as amended) and the Kurdistan
                                       Board of Investment in Erbil /
                                       Sulaymaniyah / Duhok / Halabja
                                       (Law No. 4 of 2006) -- so citing
                                       the wrong one for the
                                       engagement's actual governorate
                                       is a genuine market-entry
                                       compliance failure, not a
                                       stylistic slip. A disputed
                                       governorate (Kirkuk) gets NO
                                       assertion (`expected-
                                       investment-authority` returns
                                       nil) rather than a guessed one.
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. Corporate-number unverified -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-cr? true`,
                                       INDEPENDENTLY check
                                       `:cr-verified?` -- the Companies
                                       Registration Directorate (Tasjeel)
                                       number, per Company Law No. 21 of
                                       1997 + Ministerial Instructions
                                       No. 196 of 2004. CONDITIONAL on
                                       the engagement's own ground truth.
    6. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real government-contract filing package and submitting a
  real filing are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(Tasjeel商業登記/投資認可/税務登録/代理人確認等)が充足していない状態での提案"}]))))

(defn- krg-authority-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY verify the engagement's own
  `:cited-authority` matches the investment authority that ACTUALLY
  governs its `:governorate` -- the flagship genuinely new check this
  vertical adds. Iraq has TWO investment-licensing regimes (federal
  NIC vs. Kurdistan Board of Investment), so conflating them for a
  specific engagement is a real compliance failure, not a stylistic
  one. A disputed governorate (facts/expected-investment-authority ->
  nil) is NOT flagged -- this catalog does not resolve contested
  jurisdiction, so the governor asserts nothing rather than guessing."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)
          expected (facts/expected-investment-authority (:jurisdiction e) (:governorate e))]
      (when (and expected (not= expected (:cited-authority e)))
        [{:rule :krg-authority-mismatch
          :detail (str subject " (" (:governorate e) ") の投資認可当局は "
                      (name expected) " のはずだが提案は "
                      (some-> (:cited-authority e) name) " を引用 -- 誤った投資認可当局")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- cr-unverified-violations
  "For `:filing/submit`, when the engagement declares
  `:requires-cr? true`, INDEPENDENTLY check `:cr-verified?` -- the
  Companies Registration Directorate (Tasjeel) number. CONDITIONAL on
  the engagement's own ground truth."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-cr? e))
                 (not (true? (:cr-verified? e))))
        [{:rule :cr-unverified
          :detail (str subject " はTasjeel商業登記番号の確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (krg-authority-mismatch-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (cr-unverified-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
