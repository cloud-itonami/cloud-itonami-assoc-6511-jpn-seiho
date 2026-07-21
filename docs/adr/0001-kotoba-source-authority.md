# ADR 0001: Kotoba is the Seiho catalog source authority

- Status: Accepted
- Date: 2026-07-21

`src/association_facts.kotoba` is the sole production source. It preserves both
absent establishment dates, the first absent revision date, the complete
2026-02-26 second revision date, Japanese UTF-8 titles, official citations, and
the two distinct ordered topic sets. Unknown associations, aliases, fields,
topics, and indexes fail closed; no effects are declared.

Conformance is observable semantics across the reference evaluator, restricted
JavaScript, and instantiated typed WebAssembly, including the typed ABI, bounds,
effects, and rejection behavior. Compiler-output byte identity is not a language
gate. Clojure and the JVM are compiler/test hosts only.
