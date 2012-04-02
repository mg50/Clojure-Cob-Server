A Clojure web server that passes the cob spec. Install the dependencies with `lein deps`, and make sure there's a copy of the cob spec in the cob_spec/ folder at the top-level of the project. Run `lein run` from the top level to begin the server. To start Fitnesse, go into the cob_spec folder while the server is running and enter `java -jar fitnesse.jar -p 9090", then go to localhost:9090 in your browser.

To run the unit tests, enter `lein test cob-server.router-test`.
