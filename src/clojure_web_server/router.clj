(ns clojure-web-server.router
  (:require [ring.logger :refer [wrap-with-logger]]
            [clojure-web-server.domain :as domain]
            [bidi.ring :as bidi]
            [clojure-web-server.config :as config]))

(defn- ping [_request]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "pong"})

(def v1-public-routes
  [["test" {:get domain/test-api}]])

(def routes
  ["/" [["ping" {:get ping}]
        ["v1/" v1-public-routes]
        [true (constantly {:status 404})]]])

(defn make-handler []
  (-> routes
      bidi/make-handler))
