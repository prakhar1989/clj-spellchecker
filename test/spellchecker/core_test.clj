(ns spellchecker.core-test
  (:require [clojure.test :refer :all]
            [spellchecker.core :refer :all]))

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
           (word-split 2 "teach")))))
