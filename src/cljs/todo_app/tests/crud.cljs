(ns todo-app.tests.crud
  (:require  [cljs.test :refer-macros [deftest is testing run-tests]]
             [todo-app.pages.home-page :as page]
             [reagent.core :as r]))

(enable-console-print!)
(.log js/console "hey, hola!")

(defonce test-app-state (r/atom {:tasks (hash-map)}))

(deftest can-render?
  (r/render [page/one-item (r/cursor test-app-state [:tasks]) 1 "test" false] js/document.body)
  (is (= "<input value=\"on\" type=\"checkbox\"><label></label><button style=\"margin: 10px; padding: 10px; font-weight: bold;\">Delete me</button>"
         js/document.body.firstChild.innerHTML)))
