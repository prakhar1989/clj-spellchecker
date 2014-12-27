(ns spellchecker.core
  (:gen-class))

(def alphabets "abcdefghijklmnopqrstuvwxyz")

(defn get-words
  "returns a list of words in a text"
  [text]
  (re-seq #"\w+" (.toLowerCase text)))

(defn get-map-count 
  "increments count of word in provided map(counts)"
  [counts word]
  (assoc counts word 
         (inc (get counts word 0))))

(defn count-words
  "returns a map of counts of words in a text"
  [text]
  (reduce get-map-count {} (get-words text)))

;; change filename for better accuracy
(def nwords (count-words (slurp "resources/words-file.txt")))

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

(defn replaces
  "returns all words with one character replaced by another alphabet"
  [word]
  (flatten (for [x (get-word-splits word)
        :let [y (last x)]
        :when (not (empty? y))]
    (map (fn [a] (str (first x) a 
                      (apply str (drop 1 y)))) 
         alphabets))))

(defn inserts
  "returns all words with one alphabet added in a word"
  [word]
  (flatten (map (fn [x] 
         (map #(str (first x) % (last x)) alphabets)) 
       (get-word-splits word))))

(defn edits1
  "returns a set of all words within a edit distance of 1 from the word"
  [word]
  (set (concat (replaces word) (transposes word) 
               (inserts word) (deletes word))))

(defn known-words
  "returns a set of words from words which are in set of nwords, nil otherwise"
  [words nwords]
  (let [result (set (for [x words :when (nwords x)] x))]
    (if (empty? result) nil result)))

(defn known-edits2
  "returns a set of words at an edit distance of 2 
  from word which are part of nwords, nil otherwise"
  [word nwords]
  (let [result (set (for [e1 (edits1 word) 
                          e2 (edits1 e1) 
                          :when (nwords e2)] e2))]
    (if (empty? result) nil result)))

(defn get-candidates
  "returns a set of correction candidates of a word using the set of nwords as dictionary"
  [word nwords]
  (or (known-words [word] nwords) (known-words (edits1 word) nwords)
      (known-edits2 word nwords) [word]))

(defn correct
  "returns the correction for a word using the set of nwords as dictionary"
  [word words]
  (let [candidates (get-candidates word words)]
  (apply max-key #(get nwords % 1) candidates)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Run `lein repl` and (correct <word> nwords) to play"))
