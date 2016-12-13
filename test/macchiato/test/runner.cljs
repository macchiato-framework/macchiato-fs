(ns macchiato.test.runner
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [macchiato.test.fs.core-test]))

(doo-tests 'macchiato.test.fs.core-test)
