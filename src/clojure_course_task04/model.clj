(ns clojure_course_task04.model
  (:use [korma db core]))

;(defdb clojure (postgres
;                {:db "clojure"
;                 :user "postgres"
;                 :password "postgres"}))
(def env (into {} (System/getenv)))
(def dbhost (get env "OPENSHIFT_MYSQL_DB_HOST"))
(def dbport (get env "OPENSHIFT_MYSQL_DB_PORT"))
(def dbuser (get env "OPENSHIFT_MYSQL_DB_USERNAME"))
(def dbpass (get env "OPENSHIFT_MYSQL_DB_PASSWORD"))
(def dburl (get env "OPENSHIFT_MYSQL_DB_URL"))
;(def default-conn {:classname "com.mysql.jdbc.Driver"
;                   :subprotocol "mysql"
;                   :user dbuser
;                   :password dbpass
;                   :subname dburl;;(str "//" dbhost ":" dbport "/clojure?useUnicode=true&characterEncoding=utf8")
;                   :delimiters "`"})

(defdb clojure (mysql
                {:db "clojure"
                 :host dbhost
                 :port dbport
                 :user dbuser
                 :password dbpass
                 }))

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
