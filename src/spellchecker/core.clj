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

;; TODO remove the word itself
(defn deletes
  "returns all combinations of a character deleted from a word"
  [word]
  (map #(str (first %) 
             (apply str (drop 1 (last %))))
    (get-word-splits word)))

;; same issue as above
(defn transposes
  "returns all transposes of one character in a word"
  [word]
  (map #(str (first %) (get (last %) 1) 
             (first (last %)) (apply str (drop 2 (last %))))
       (get-word-splits word)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (count-words "tis for tis")))
