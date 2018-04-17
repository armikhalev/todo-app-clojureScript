(ns todo-app.tests.test-runner
  (:require  [cljs.test :refer-macros [run-tests]]
             [todo-app.tests.crud]
             [todo-app.tests.e2e]))
(defn run-all-tests
  []
  (run-tests 'todo-app.tests.crud
             'todo-app.tests.e2e))
