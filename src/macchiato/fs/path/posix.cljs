(ns macchiato.fs.path.posix
  (:refer-clojure :exclude [resolve])
  (:require [macchiato.fs.util :refer [js-apply obj->map]]
            ["path" :as path]))

(def posix (.-posix path))

(defn basename
  "Returns the basename (file without directory) of the path
   args: path
   return: string"
  [path]
  (.basename posix path))

(defn dirname
  "Returns the dirname of the path
   args: [path]
   return: string"
  [path]
  (.dirname posix path))

(defn extension
  "Returns the extension of the file path (dot included)
   args: [path]
   return: string"
  [path]
  (.extname posix path))

(defn format
  "Turns a map of the form returned by `parse` into a string
   args [map]
   returns: string"
  [{:keys [dir root base name ext]}]
  (.format posix #js {:dir dir :root root :base base :name name :ext ext}))

(defn absolute?
  "true if path is absolute
   args: [path]
   returns: bool"
  [path]
  (.isAbsolute posix path))

(defn join
  "Joins path segments together
   args: [& segments]
   returns: string"
  [& ps]
  (js-apply (.-join posix) nil ps))

(defn normalize
  "Resolves . and .. segments of path
   args: [path]
   returns: string"
  [path]
  (.normalize posix path))

(defn parse
  "Returns a map describing the file path
   args: [path]
   returns: map with keys (all string values):
     :root :dir :base :ext :name"
  [path]
  (obj->map (.parse posix path) true))

(defn relative
  "The relative path from `from` to `to`
   args: [from to]
   returns: string"
  [from to]
  (.relative posix from to))

(defn resolve
  "Joins the given path segments and absolutifies
   args [& segments]
   returns: string"
  [& ps]
  (js-apply (.-resolve posix) nil ps))
