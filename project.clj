(defproject macchiato/fs "0.0.5"
  :description "filesystem utilities"
  :url "https://github.com/yogthos/macchiato-framework/macchiato-fs"
  :scm {:name "git"
        :url  "https://github.com/macchiato-framework/macchiato-fs.git"}
  :license {:name "MIT License"
            :url  "http://opensource.org/licenses/MIT"}
  :clojurescript? true
  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.293" :scope "provided"]]
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-codox "0.10.2"]
            [lein-doo "0.1.7"]
            [lein-npm "0.6.2"]]
  :npm {:dependencies []}
  :profiles {:test
             {:cljsbuild
                   {:builds
                    {:test
                     {:source-paths ["src" "test"]
                      :compiler     {:main          macchiato.test.runner
                                     :output-to     "target/test/core.js"
                                     :target        :nodejs
                                     :optimizations :none
                                     :source-map    true
                                     :pretty-print  true}}}}
              :doo {:build "test"}}}
  :aliases
  {"test"
   ["do"
    ["npm" "install"]
    ["clean"]
    ["with-profile" "test" "doo" "node" "once"]]
   "test-watch"
   ["do"
    ["npm" "install"]
    ["clean"]
    ["with-profile" "test" "doo" "node"]]})
