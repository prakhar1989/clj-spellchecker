(defproject spellchecker "0.1.0-SNAPSHOT"
  :description "Norvig's SpellChecker in Clojure"
  :url "http://github.com/prakhar1989/spellchecker"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot spellchecker.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
