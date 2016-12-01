(ns macchiato.fs
  (:refer-clojure :exclude [exists?]))

(def fs (js/require "fs"))

(defn exists? [filename]
  (.existsSync fs filename))

(defn slurp [filename & {:keys [encoding] :or {encoding "utf8"}}]
  (when (exists? filename)
    (.readFileSync fs filename encoding)))

(defn spit [filename data & {:keys [encoding mode flag]
                             :or {encoding "utf8"
                                  mode "0o666"
                                  flag "w"}}]
  (.writeFileSync fs filename data encoding mode flag))
