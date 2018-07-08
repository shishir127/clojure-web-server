(ns clojure-web-server.core
	(:require [clojure.string :as string]
						[clojure.tools.logging :as log]
						[mount.core :as mount]
            [clojure-web-server.server]
            [clojure-web-server.config :as config]))

(defn- start-api
  []
  (try
    (mount/stop)
    (mount/start #'config/config)
    (mount/start)
    (log/info "Started api service")
    (catch Exception e
      (log/error e "Error in starting api service")
      (mount/stop)
      (System/exit 1))))

(defn- add-shutdown-hook
  []
  (.addShutdownHook
   (Runtime/getRuntime)
   (Thread. ^Runnable #(do
                         (mount/stop)
                         (shutdown-agents))
            "Shutdown-handler")))

(defn -main
	[& [command]]
  (add-shutdown-hook)
  (case command
     nil (start-api)
     "api" (start-api)
     (do
       (print "Must supply a valid command to run")
       (System/exit 1))))
