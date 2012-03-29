(ns cob-server.router-test
  (:use cob-server.router clojure.test))

(deftest test-not-found
  (let [req {:request-line {:method :GET :request-uri "/foobar"}}]
    (is (= [404 "Not Found"] (cob-server-router req)))))

(deftest test-GET
  (let [req {:request-line {:method :GET :request-uri "/"}}]
    (is (= [200 "OK"] (cob-server-router req)))))

(deftest test-PUT
  (let [req {:request-line {:method :PUT :request-uri "/form"}}]
    (is (= [200 "OK"] (cob-server-router req)))))

(deftest test-POST
  (let [req {:request-line {:method :POST :request-uri "/form"}}]
    (is (= [200 "OK"] (cob-server-router req)))))

(deftest test-data-post-1
  (let [req {:request-line {:method :POST :request-uri "/data_form"}
             :body-params {}}]
    (is (= (cob-server-router req)
           [200 "{}"]))))

(deftest test-data-post-2
  (let [req {:request-line {:method :POST :request-uri "/data_form"}
             :body-params {:a 1}}]
    (is (= (cob-server-router req)
           [200 "{:a 1}"]))))

(deftest test-data-post-3
  (let [req {:request-line {:method :POST :request-uri "/data_form"}
             :body-params {:a 1 :b 2}}]
    (is (= (cob-server-router req)
           [200 "{:b 2, :a 1}"]))))

(deftest test-get-file1
  (let [req {:request-line {:method :GET :request-uri "/file1"}}]
    (is (= [200 "file1 contents"] (cob-server-router req)))))

(deftest test-get-file1
  (let [req {:request-line {:method :GET :request-uri "/public"}}]
    (is (= [200 "file1\nfile2"] (cob-server-router req)))))
