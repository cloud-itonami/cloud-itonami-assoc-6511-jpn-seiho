(ns association.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest seiho-has-spec-basis
  (let [sb (facts/spec-basis "seiho")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:association-rule/url %) "https://www.seiho.or.jp/") sb))
    (is (every? #(= "6511" (:association-rule/isic %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "keidanren")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["seiho" "keidanren"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["keidanren"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["seiho.business-quality-assessment-guideline-a"]
         (mapv :association-rule/id (facts/by-topic "seiho" :consumer-protection))))
  (is (empty? (facts/by-topic "seiho" :labor)))
  (is (empty? (facts/by-topic "keidanren" :governance))))
