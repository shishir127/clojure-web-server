(ns clojure-web-server.config
  (:refer-clojure :exclude [delay])
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.tools.logging :as log]
            [aero.core :as aero]
            [medley.core :refer [dissoc-in]]
            [mount.core :refer [defstate]]))

(defn read-env
  []
  (or (some-> "ENV"
              (System/getenv)
              string/lower-case
              keyword)
      :dev))

(defmethod aero/reader 'nil-long
  [_ _ value]
  (some-> value
          str
          (Long/parseLong)))


(defmethod aero/reader 'nil-bool
  [_ _ value]
  (some-> value
          str
          (Boolean/valueOf)))

(defn read-config
  [environment]
  (let [config-file "config.edn"
        config      (-> config-file
                        (clojure.java.io/resource)
                        (aero/read-config {:profile environment}))]
    (log/info "Loaded config from file:" config-file)
    (log/info "Environment: " environment)
    (when-not (= environment :test)
      (println "Config:\n" (with-out-str (-> config
                                             (dissoc-in [:db :password])
                                             (clojure.pprint/pprint)))))
    (assoc config :env environment)))
