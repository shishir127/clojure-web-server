(defproject clojure-web-server "0.1.0-SNAPSHOT"
  :description "spike for a clojure web server"
  :url "http://shishir127.github.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clojure-web-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
