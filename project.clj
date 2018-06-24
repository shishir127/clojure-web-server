(defproject clojure-web-server "0.1.0-SNAPSHOT"
  :description "spike for a clojure web server"
  :url "http://shishir127.github.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
  				 [bidi "2.0.14"]
  				 [ring/ring "1.5.0"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [ring-logger "0.7.6"]
                 [mount "0.1.10"]
                 [aero "1.1.2"]
                 [medley "1.0.0"]]
  :main ^:skip-aot clojure-web-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
