(ns culture.facts
  "Country-level regional-culture catalog for Iraq (IRQ) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  the `marketentry.facts` / `statute.facts` catalogs of the iso3166
  siblings (ADR-2607141700); city-level counterparts live in the
  cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"IRQ"
   [{:culture/id "irq.dish.masgouf"
     :culture/name "Masgouf"
     :culture/country "IRQ"
     :culture/kind :dish
     :culture/summary "Mesopotamian dish of seasoned, grilled carp, often considered the national dish of Iraq."
     :culture/url "https://en.wikipedia.org/wiki/Masgouf"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irq.dish.quzi"
     :culture/name "Quzi"
     :culture/country "IRQ"
     :culture/kind :dish
     :culture/summary "Rice-based dish with slowly cooked lamb, roasted nuts and raisins, considered one of Iraq's national dishes, with variations across the Middle East."
     :culture/url "https://en.wikipedia.org/wiki/Quzi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irq.dish.kleicha"
     :culture/name "Kleicha"
     :culture/country "IRQ"
     :culture/kind :dish
     :culture/summary "Middle Eastern cookie originating from ancient Mesopotamia, traditionally filled with dates, nuts or coconut and flavored with cardamom and rose water; associated primarily with Iraq and also with Saudi Arabia and Syria."
     :culture/url "https://en.wikipedia.org/wiki/Kleicha"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irq.dish.samoon"
     :culture/name "Samoon"
     :culture/country "IRQ"
     :culture/kind :dish
     :culture/summary "Yeast bread consumed mainly in Iraq, baked in traditional stone ovens."
     :culture/url "https://en.wikipedia.org/wiki/Samoon"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irq.craft.mudhif"
     :culture/name "Mudhif"
     :culture/country "IRQ"
     :culture/kind :craft
     :culture/summary "Traditional reed house constructed by the Marsh Arabs of the southern Iraqi swamps, historically used as a ceremonial gathering space and guest house."
     :culture/url "https://en.wikipedia.org/wiki/Mudhif"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irq.heritage.babylon"
     :culture/name "Babylon"
     :culture/country "IRQ"
     :culture/kind :heritage
     :culture/summary "Ancient Mesopotamian city on the lower Euphrates in present-day Iraq, capital of the Old and Neo-Babylonian Empires; inscribed as a UNESCO World Heritage Site in 2019."
     :culture/url "https://en.wikipedia.org/wiki/Babylon"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "irq.heritage.citadel-of-erbil"
     :culture/name "Citadel of Erbil"
     :culture/country "IRQ"
     :culture/kind :heritage
     :culture/summary "Ancient occupied mound in Iraq's Kurdistan Region with continuous settlement since the 5th millennium BC, possibly the oldest continuously occupied settlement on Earth; a UNESCO World Heritage Site inscribed in 2014."
     :culture/url "https://en.wikipedia.org/wiki/Citadel_of_Erbil"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-irq culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "IRQ"))
                 " IRQ entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
