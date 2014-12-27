(ns spellchecker.core-test
  (:require [clojure.test :refer :all]
            [spellchecker.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))


(deftest get-words-test
  (testing "Get words function"
    (is (= '("hello world") 
