(ns clojure_course_task04.view
  (:require [me.raynes.laser :as l]
            [clojure.java.io :refer [file]]))

(defn template [name]
  (l/parse
   (slurp (clojure.java.io/resource (str "public/html/" name ".html")))))

(l/defragment problem-frag "<option></option>" [{:keys [name description]}]
  (l/element= :option) (l/content name))

(l/defragment solution-frag "<h3></h3><div class=\"expression\"></div><div class=\"result\"></div>" [{:keys [problem expression result]}]
  (l/element= :h3) (l/content problem)
  (l/class= "expression") (l/content expression)
  (l/class= "result") (l/content result))

(defn show-root []
  (l/document (template "root")))

(defn show-about []
  (l/document (template "about")))

(defn show-contact []
  (l/document (template "contact")))

(defn show-timeit [problems solutions]
  (l/document (template "timeit")
              (l/id= "inputProblem")
              (l/content
               (for [problem problems]
                 (problem-frag problem)))
              (l/id= "results")
              (l/content
               (for [solution solutions]
                 (solution-frag solution)))))

(defn show-add-problem []
  (l/document (template "timeit_add_problem")))
