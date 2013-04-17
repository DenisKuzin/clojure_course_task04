(ns clojure_course_task04.handler
  (:use compojure.core
        clojure_course_task04.model
        clojure_course_task04.view)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]))

(defn add-problem [name description]
  (let [problem {:name name :description description}]
    (insert-problem problem)
    (resp/redirect "/timeit")))

(defn add-solution [problem expression]
  (let [solution {:problem problem :expression expression 
                  :result (with-out-str (time (load-string expression)))}]
    (insert-solution solution)
    (resp/redirect "/timeit")))

(defroutes app-routes
  (GET "/" [] (show-root))
  (GET "/about" [] (show-about))
  (GET "/contact" [] (show-contact))
  (GET "/timeit" [] (show-timeit (select-problems) (select-solutions)))
  (POST "/timeit" [problem expression] (add-solution problem expression))
  (GET "/timeit-add-problem" [] (show-add-problem))
  (POST "/timeit-add-problem" [name description] (add-problem name description))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
