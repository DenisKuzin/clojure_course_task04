(ns clojure_course_task04.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.java.io :refer [file]]
            [me.raynes.laser :as l]))

(defroutes app-routes
  (GET "/" [] (l/document (l/parse (file (str (System/getenv "OPENSHIFT_REPO_DIR") "webapps/html/root.html")))))
  (GET "/about" [] (l/document (l/parse (file (str (System/getenv "OPENSHIFT_REPO_DIR") "webapps/html/about.html")))))
  (GET "/contact" [] (l/document (l/parse (file (str (System/getenv "OPENSHIFT_REPO_DIR") "webapps/html/contact.html")))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
