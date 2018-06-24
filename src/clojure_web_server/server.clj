(ns clojure-web-server.server
  (:require  [clojure-web-server.router :as routes]
             [mount.core :as mount :refer [defstate]]
             [ring.adapter.jetty :as ring-jetty]
             [clojure.tools.logging :as log])
  (:import (org.eclipse.jetty.server Server)))

(defn- start [handler]
    (log/info "Starting server on port:" 3000)
    (ring-jetty/run-jetty handler {:port                 3000
                                   :min-threads          10
                                   :max-threads          10
                                   :join?                false
                                   :send-server-version? false}))

(defn- stop [^Server server]
  (.stop server)
  (log/info "Stopped server"))

(defstate server
  :start (start (routes/make-handler))
  :stop (stop server))
