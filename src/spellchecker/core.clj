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

(defn word-split
  "splits a word at the nth position"
  [n word]
  (list (apply str (take n word))
        (apply str (drop n word))))

(defn get-word-splits
  "returns a list of all word splits"
  [word]
  (map #(word-split % word)
    (range (inc (count word)))))

(defn deletes
  "returns all combinations of a char deleted from a word"
  [word]
  (for [x (get-word-splits word)
        :let [y (last x)]
        :when (not (empty? y))]
    (str (first x) (apply str (drop 1 y)))))

(defn transposes
  "returns all transposes of one character in a word"
  [word]
  (for [x (get-word-splits word)
        :let [y (last x)]
        :when (> (count y) 1)]
    (str (first x) (get y 1) 
         (first y) (apply str (drop 2 y)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (count-words "tis for tis")))
