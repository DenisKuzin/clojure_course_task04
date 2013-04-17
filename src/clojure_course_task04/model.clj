(ns clojure_course_task04.model
  (:use [korma db core]))

;(defdb clojure
;  {:classname "org.postgresql.Driver"
;   :subprotocol "postgresql"
;   :subname "clojure"
;   :user "postgres"
;   :password "postgres"})

(defdb clojure (postgres
                {:db "clojure"
                 :user "postgres"
                 :password "postgres"}))

(defentity problem)

(defentity solution
  (has-one problem))

(defn select-problems []
  (select problem))

(defn select-solutions []
  (select solution))

(defn insert-problem [item]
  (insert problem (values item)))

(defn insert-solution [item]
  (insert solution (values item)))
