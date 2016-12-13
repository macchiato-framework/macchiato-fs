(ns macchiato.fs
  (:refer-clojure :exclude [exists?]))

(def fs (js/require "fs"))

(def path-separator (.-sep (js/require "path")))

(defn- obj->clj [obj]
  (reduce
    (fn [props k]
      (assoc props (keyword k) (aget obj k)))
    {}
    (js/Object.keys obj)))

(defn exists? [path]
  (.existsSync fs path))

(defn file? [path]
  (.isFile (.lstatSync fs path)))

(defn directory? [path]
  (.isDirectory (.lstatSync fs path)))

(defn slurp [filename & {:keys [encoding] :or {encoding "utf8"}}]
  (when (exists? filename)
    (if encoding
      (.readFileSync fs filename encoding)
      (.readFileSync fs filename))))

(defn spit [filename data & {:keys [encoding mode flag]
                             :or   {encoding "utf8"
                                    mode     "0o666"
                                    flag     "w"}}]
  (let [data (if (string? data) data (str data))]
    (.writeFileSync fs filename data encoding mode flag)))

(defn stat [path]
  (when (exists? path)
    (obj->clj (.statSync fs path))))

(defn stream [path]
  (.createReadStream fs path))
