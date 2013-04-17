(ns clojure_course_task04.handler
  (:use compojure.core
;        criterium.core
        clojure_course_task04.model
        clojure_course_task04.view)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :as resp]))

(defn add-problem [problem]
  (insert-problem problem)
  (resp/redirect "/timeit"))

(defn add-solution [problem expression]
  (let [solution {:problem problem :expression expression 
                  :result (with-out-str (time (load-string expression)))}]
                                         ;(with-out-str (benchmark (load-string expression)))}]
    (insert-solution solution)
    (resp/redirect "/timeit")))

(defroutes app-routes
  (GET "/" [] (show-root))
  (GET "/about" [] (show-about))
  (GET "/contact" [] (show-contact))
  (GET "/timeit" [] (show-timeit (select-problems) (select-solutions)))
  (POST "/timeit" [problem expression] (add-solution problem expression))
  (GET "/timeit-add-problem" [] (show-add-problem))
  (POST "/timeit-add-problem" [req] (add-problem (:params req)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
