(ns cob-server.router
  (:use [server.request.router :only [defrouter GET PUT POST ANY*]]
        [server.response.actions :only [echo serve-file dir-list]]
        [server.core :only [config]]))

(defrouter cob-server-router [request params]
  (GET "/foobar"
       [404 "Not Found"])
  (GET "/"
       (echo "OK"))
  (PUT "/form"
       (echo "OK"))
  (POST "/form"
        (echo "OK"))
  (POST "/data_form"
        (echo (str params)))
  (GET "/file1"
       (serve-file "file1"))
  (GET "/public"
       (dir-list "./"))
  (GET "/redirect"
       {:status-code 301 :body "Moved permanently" :Location "http://www.google.com"})
  (ANY* ".*"
        [404 "Not Found"]))
