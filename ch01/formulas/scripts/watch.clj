(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'formulas.core
   :output-to "out/formulas.js"
   :output-dir "out"})
