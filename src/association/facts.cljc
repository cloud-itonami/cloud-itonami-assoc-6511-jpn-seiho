(ns association.facts
  "Industry self-regulatory rule catalog for The Life Insurance
  Association of Japan (一般社団法人 生命保険協会 / LIAJ, seiho) -- a 10th
  industry-association-level source (see cloud-itonami-assoc-6419-jpn-zenginkyo,
  -6512-jpn-sonpo, -6612-jpn-jsda, -6419-deu-bankenverband, -6612-usa-finra,
  -6512-usa-naic, -6920-jpn-jicpa, -6920-usa-aicpa, -6419-fra-fbf for the
  first nine) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).
  Aligned to ISIC 6511 (life insurance) -- a NEW industry code, distinct
  from sonpo's ISIC 6512 (general/non-life insurance): life and non-life
  insurance are regulated by separate industry associations in Japan.
  Every entry cites an OFFICIAL seiho.or.jp URL -- never fabricated. A
  rule not in this table has NO spec-basis, full stop; extend `catalog`,
  do not invent an id/url.

  業務品質評価基準ガイドライン（A版） was verified by directly reading the
  source PDF cover page text via the Read tool (same strictest-tier
  verification as sonpo/bankenverband/naic/jicpa/aicpa) -- the FY2026
  edition date (作成日：2026/2/26) is printed on the document's own cover
  page. The codes-of-conduct overview page was directly WebFetch-verified.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"seiho"
   [{:association-rule/id "seiho.conduct-guidelines-overview"
     :association-rule/title "行動規範・指針・自主ガイドライン等 (Codes of Conduct, Directives, and Voluntary Guidelines)"
     :association-rule/association "seiho"
     :association-rule/isic "6511"
     :association-rule/country "JPN"
     :association-rule/kind :governance-program
     :association-rule/url "https://www.seiho.or.jp/activity/guideline/"
     :association-rule/url-provenance :official-association-site
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:governance :member-conduct}}
    {:association-rule/id "seiho.business-quality-assessment-guideline-a"
     :association-rule/title "業務品質評価基準ガイドライン（A版） (Business Quality Assessment Standards Guideline, Version A)"
     :association-rule/association "seiho"
     :association-rule/isic "6511"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.seiho.or.jp/quality/pdf/guideline.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/last-revised-date "2026-02-26"
     :association-rule/retrieved-at "2026-07-15"
     :association-rule/topic #{:consumer-protection :sales-quality}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-6511-jpn-seiho Wave 0 (ADR-2607141700): "
                 (count (get catalog "seiho")) " seiho rules seeded with an "
                 "official seiho.or.jp citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
