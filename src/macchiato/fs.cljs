(ns macchiato.fs
  (:refer-clojure :exclude [exists?])
  (:require ["fs" :as fs]))

(defn- obj->clj [obj]
  (reduce
    (fn [props k]
      (assoc props (keyword k) (aget obj k)))
    {}
    (js/Object.keys obj)))

(defn exists? [path]
  (fs/existsSync path))

(defn file? [path]
  (.isFile (fs/lstatSync path)))

(defn directory? [path]
  (.isDirectory (fs/lstatSync path)))

(defn read-dir-sync
  "Reads a folder synchronously and returns the file names as a Clojure vector."
  [path]
  (js->clj (fs/readdirSync path)))

(defn slurp [filename & {:keys [encoding]}]
  (.toString
   (if encoding
     (fs/readFileSync filename encoding)
     (fs/readFileSync filename))))

(defn slurp-async [filename cb & {:keys [encoding]}]
  (let [str-cb (fn [err data] (cb err (.toString data)))]
    (if encoding
      (fs/readFile filename encoding str-cb)
      (fs/readFile filename str-cb))))

(defn spit [filename data & {:keys [encoding mode flag]
                             :or   {encoding "utf8"
                                    mode     "0o666"
                                    flag     "w"}}]
  (let [data (if (string? data) data (str data))]
    (fs/writeFileSync filename data encoding mode flag)))

(defn spit-async [filename data on-error & {:keys [encoding mode flag]
                                            :or   {encoding "utf8"
                                                   mode     "0o666"
                                                   flag     "w"}
                                            :as   opts}]
  (fs/writeFile filename data (clj->js opts) on-error))

(defn delete [file]
  (fs/unlinkSync file))

(defn delete-async [file on-error]
  (fs/unlink file on-error))

(defn stat [path]
  (when (exists? path)
    (obj->clj (fs/statSync path))))

(defn stat-async [path cb]
  (when (exists? path)
    (fs/stat path (fn [err stats] (cb err (obj->clj stats))))))

(defn read-stream [path]
  (fs/createReadStream path))

(defn write-stream [path]
  (fs/createReadStream path))

(defn pipe [input-stream output-stream]
  (fs/pipe input-stream output-stream))
