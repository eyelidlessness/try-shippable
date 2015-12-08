(defproject try-shippable "0.1.0-SNAPSHOT"
  :description "Try Shippable"
  :url "https://github.com/eyelidlessness/try-shippable"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170" :scope "provided"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]
                 [prone "0.8.2"]
                 [ring "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-server "0.4.0"]]

  :plugins [[lein-cljsbuild "1.1.1"]]

  :main try-shippable.server

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]]

  :source-paths ["src"]
  :resource-paths ["resources" "target/cljsbuild"]

  :cljsbuild {:builds {:app {:source-paths ["src"]
                             :compiler {:output-to "target/cljsbuild/public/js/built.js"
                                        :output-dir "target/cljsbuild/public/js/out"
                                        :asset-path   "/js/out"
                                        :optimizations :none
                                        :main "try-shippable.wat"}}}}

  :profiles {:dev {:plugins [[test2junit "1.1.1"]]}

             :uberjar {:prep-tasks ["compile" ["cljsbuild" "once"]]
                       :aot :all
                       :omit-source true
                       :cljsbuild {:jar true
                                   :builds {:app
                                             {:compiler
                                              {:optimizations :whitespace
                                               :pretty-print false}}}}}}

  :test2junit-output-dir "./shippable/testresults")
