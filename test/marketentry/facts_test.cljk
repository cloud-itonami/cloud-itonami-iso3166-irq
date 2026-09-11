(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest irq-has-spec-basis
  (let [sb (facts/spec-basis "IRQ")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "IRQ")))
    (is (some? (facts/corporate-number-spec-basis "IRQ")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "IRQ")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "IRQ" all)))
    (is (not (facts/required-evidence-satisfied? "IRQ" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["IRQ" "ATL" "ZZZ"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "ZZZ"] (:missing-jurisdictions c)))))

(deftest krg-federal-authority-split
  (testing "the four Kurdistan Region governorates expect :krg-boi"
    (is (= :krg-boi (facts/expected-investment-authority "IRQ" "Erbil")))
    (is (= :krg-boi (facts/expected-investment-authority "IRQ" "Sulaymaniyah")))
    (is (= :krg-boi (facts/expected-investment-authority "IRQ" "Duhok")))
    (is (= :krg-boi (facts/expected-investment-authority "IRQ" "Halabja"))))
  (testing "any other known governorate expects :federal"
    (is (= :federal (facts/expected-investment-authority "IRQ" "Baghdad")))
    (is (= :federal (facts/expected-investment-authority "IRQ" "Basra"))))
  (testing "a disputed governorate gets no assertion, not a guessed one"
    (is (nil? (facts/expected-investment-authority "IRQ" "Kirkuk"))))
  (testing "an uncataloged iso3 or nil governorate gets no assertion"
    (is (nil? (facts/expected-investment-authority "ATL" "Erbil")))
    (is (nil? (facts/expected-investment-authority "IRQ" nil)))))

(deftest investment-authority-basis-cites-both-regimes
  (let [ia (facts/investment-authority-basis "IRQ")]
    (is (= "National Investment Commission (NIC)" (get-in ia [:federal :owner-authority])))
    (is (= "Kurdistan Board of Investment (KRG BOI)" (get-in ia [:krg :owner-authority])))
    (is (= ["Erbil" "Sulaymaniyah" "Duhok" "Halabja"] (get-in ia [:krg :governorates])))))
