(ns macchiato.fs.path
  (:refer-clojure :exclude [resolve])
  (:require [clojure.string :refer [ends-with?]]
            [macchiato.fs.util :refer [js-apply obj->map]]
            ["path" :as js-path]))

(def delimiter (.-delimiter js-path))
(def separator (.-sep js-path))

(defn basename
  "Returns the basename (file without directory) of the path
   args: path
   return: string"
  [path]
  (js-path/basename path))

(defn dirname
  "Returns the dirname of the path
   args: [path]
   return: string"
  [path]
  (js-path/dirname path))

(defn extension
  "Returns the extension of the file path (dot included)
   args: [path]
   return: string"
  [path]
  (js-path/extname path))

(defn format
  "Turns a map of the form returned by `parse` into a string
   args [map]
   returns: string"

  [{:keys [dir root base name ext]}]
  (js-path/format #js {:dir dir :root root :base base :name name :ext ext}))

(defn absolute?
  "true if path is absolute
   args: [path]
   returns: bool"
  [path]
  (js-path/isAbsolute path))

(defn join
  "Joins path segments together
   args: [& segments]
   returns: string"
  [& ps]
  (js-apply js-path/join nil ps))

(defn normalize
  "Resolves . and .. segments of path
   args: [path]
   returns: string"
  [path]
  (js-path/normalize path))

(defn parse
  "Returns a map describing the file path
   args: [path]
   returns: map with keys (all string values):
     :root :dir :base :ext :name"
  [path]
  (obj->map (js-path/parse path)))

(defn relative
  "The relative path from `from` to `to`
   args: [from to]
   returns: string"
  [from to]
  (js-path/relative from to))

(defn resolve
  "Joins the given path segments and absolutifies
   args [& segments]
   returns: string"
  [& ps]
  (js-apply js-path/resolve nil ps))

(defn with-separator
  "Receives a path, and returns the same value if it ends in a path separator,
  or the path with the path separator appended at the end if otherwise."
  [path]
  (if (ends-with? path separator)
    path
    (str path separator)))
