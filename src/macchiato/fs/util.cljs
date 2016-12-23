(ns macchiato.fs.util
  (:require [cljs.nodejs :as node]))

(defn js-apply
  "Applies a javascript function to a 'this' context and arguments
   args: [f this args]
   returns: result of calling f with args"
  [f this args]
  (.apply f this (to-array args)))

(defn obj->map
  [o & {:keys [keywordize? transform]
        :or   {keywordize? true
               transform   identity}}]
  (reduce
    (fn [props k]
      (assoc props (if keywordize? (keyword k) k) (transform (aget o k))))
    {}
    (js/Object.keys o)))




