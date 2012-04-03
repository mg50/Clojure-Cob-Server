(ns cob-server.main
  (:use server.app cob-server.router)
  (:gen-class))



(defn -main [& args]
  (run-server cob-server-router))
