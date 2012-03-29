(ns cob-server.main
  (:use server.app cob-server.router)
  (:gen-class))



(defn -main []
  (run-server cob-server-router))
