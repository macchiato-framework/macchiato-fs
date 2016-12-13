(ns macchiato.test.fs.core-test
  (:require
    [macchiato.fs :as fs]
    [cljs.test :refer-macros [is are deftest testing use-fixtures]]))

(def text-path "test/data/foo.txt")
(def data-path "test/data/foo.edn")

(deftest file-test
  (is (true? (fs/exists? "project.clj")))
  (is (true? (fs/file? "project.clj")))
  (is (false? (fs/directory? "project.clj")))
  (is (true? (fs/directory? "test"))))

(deftest spit-slurp-test
  (let [text "this is a test"
        data {:foo "bar"}]
    (fs/spit text-path text)
    (fs/spit data-path data)
    (is (= text (fs/slurp text-path)))
    (is (= (str data) (fs/slurp data-path)))))

(deftest stat-test
  (is
    (= [:ino :uid :atime :mode :size :gid :ctime :nlink :blocks :blksize :birthtime :dev :rdev :mtime]
       (keys (fs/stat text-path)))))
