(ns spellchecker.core-test
  (:require [clojure.test :refer :all]
            [spellchecker.core :refer :all]))

;; dummy word for testing
(def test-word "teach")
(def N (count test-word))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest get-words-test
  (testing "Get words function"
    (is (= '("hello" "world") 
          (get-words "hello world")))))

(deftest get-map-count-test
  (testing "Get map count function"
    (is (= {"hello" 2 "world" 1}
           (get-map-count {"hello" 1 "world" 1} "hello")))))

(deftest count-words-test
  (testing "count words function"
    (is (= {"hello" 2 "world" 1}
           (count-words "hello world hello")))))

(deftest word-split-test
  (testing "word split function"
    (is (= '("te" "ach")
           (word-split 2 test-word)))))

;; reasoning for test below (from the blogpost)
;; For a word of length n, there will be n deletions, n-1 transpositions, 
;; 26n alterations, and 26(n+1) insertions, 
;; for a total of 54n+25 (of which a few are typically duplicates)
(deftest deletes-test
  (testing "delete function"
    (is (= N (count (deletes test-word))))))

(deftest transposes-test
  (testing "transposes function"
    (is (= (dec N) (count (transposes test-word))))))

(deftest replaces-test
  (testing "replaces function"
    (is (= (* 26 N) (count (replaces test-word))))))

(deftest inserts-test
  (testing "inserts function"
    (is (= (* 26 (inc N)) (count (inserts test-word))))))

