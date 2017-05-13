(ns macchiato.fs.path
  (:require [clojure.string :refer [ends-with?]]
            [macchiato.fs.util :refer [js-apply obj->map]]
            [cljs.nodejs :as node]))

(def js-path (node/require "path"))
(def delimiter (.-delimiter js-path))
(def separator (.-sep js-path))

(defn basename
  "Returns the basename (file without directory) of the path
   args: path
   return: string"
  [path]
  (.basename js-path path))

(defn dirname
  "Returns the dirname of the path
   args: [path]
   return: string"
  [path]
  (.dirname js-path path))

(defn extension
  "Returns the extension of the file path (dot included)
   args: [path]
   return: string"
  [path]
  (.extname js-path path))

(defn format
  "Turns a map of the form returned by `parse` into a string
   args [map]
   returns: string"

  [{:keys [dir root base name ext]}]
  (.format js-path #js {:dir dir :root root :base base :name name :ext ext}))

(defn absolute?
  "true if path is absolute
   args: [path]
   returns: bool"
  [path]
  (.isAbsolute js-path path))

(defn join
  "Joins path segments together
   args: [& segments]
   returns: string"
  [& ps]
  (js-apply (.-join js-path) nil ps))

(defn normalize
  "Resolves . and .. segments of path
   args: [path]
   returns: string"
  [path]
  (.normalize js-path path))

(defn parse
  "Returns a map describing the file path
   args: [path]
   returns: map with keys (all string values):
     :root :dir :base :ext :name"
  [path]
  (obj->map (.parse js-path path)))

(defn relative
  "The relative path from `from` to `to`
   args: [from to]
   returns: string"
  [from to]
  (.relative js-path from to))

(defn resolve
  "Joins the given path segments and absolutifies
   args [& segments]
   returns: string"
  [& ps]
  (js-apply (.-resolve js-path) nil ps))

(defn with-separator
  "Receives a path, and returns the same value if it ends in a path separator,
  or the path with the path separator appended at the end if otherwise."
  [path]
  (if (ends-with? path separator)
    path
    (str path separator)))
