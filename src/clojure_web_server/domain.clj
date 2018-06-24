(ns clojure-web-server.domain
  (:require [ring.util.response :as resp]))

(defn test-api
  [& args]
  (resp/response (str "this is a test API")))
