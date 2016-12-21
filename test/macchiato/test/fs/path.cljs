(ns macchiato.test.fs.path
  (:require
    [macchiato.fs.path :as path]
    [cljs.test :refer-macros [is are deftest testing use-fixtures]]))

;; TODO: Further path tests. Most functions are simple wrappers, but still.

(deftest with-separator-test
  (is (= "test/" (path/with-separator "test")))
  (is (= "test/" (path/with-separator "test/")))
  (is (= "test.txt/" (path/with-separator "test.txt")))     ; Does not check if it's an actual folder
  (is (= "for/test/" (path/with-separator "for/test"))))
