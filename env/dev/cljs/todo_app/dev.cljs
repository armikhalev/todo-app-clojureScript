(ns ^:figwheel-no-load todo-app.dev
  (:require
    [todo-app.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
