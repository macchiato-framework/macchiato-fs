(ns macchiato.test.runner
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [macchiato.test.fs.core-test]
    [macchiato.test.fs.path]))

(doo-tests 'macchiato.test.fs.core-test
           'macchiato.test.fs.path)
