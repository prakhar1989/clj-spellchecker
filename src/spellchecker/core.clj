(ns spellchecker.core
  (:gen-class))

(defn get-words
  "returns a list of words in a text"
  [text]
  (re-seq #"\w+" 
          (clojure.string/lower-case text)))

(defn get-map-count 
  "increments count of word in provided map(counts)"
  [counts word]
  (assoc counts word 
         (inc (get counts word 0))))

(defn count-words
  "returns a map of counts of words in a text"
  [text]
  (reduce get-map-count {} (get-words text)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (count-words "tis for tis")))
