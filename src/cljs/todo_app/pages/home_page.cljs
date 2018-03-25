(ns todo-app.pages.home-page
  (:require [reagent.core :as r]))

(defonce counter (r/atom 0))

(defonce app-state (r/atom {:tasks (hash-map)}))

(defn delete-task [id title]
  [:button
   {:style {:margin 10
            :padding 10
            :fontWeight "bold"}
    }
   "Delete me"])

(defn task-list-comp [tasks]
  [:ul
   (for [[id {done :done title :title}] @tasks]
     ^{:key id}
     [:li title
      [delete-task id title]])])

(defn task-input-comp [tasks]
  (let [task-input (r/atom "")]
    (fn [tasks]
      [:div
       [:input
        {:type "text"
         :placeholder "Enter a new task"
         :value @task-input
         :on-change
         #(reset! task-input (-> % .-target .-value))
         :on-key-up
         (fn [e]
           (when (= "Enter" (.-key e))
             (let [id (swap! counter inc)]
               (swap! tasks assoc id {:title @task-input :done false}))
             (reset! task-input "")
             (.preventDefault e)))} ]])))

(defn home-page []
  [:div 
   [:h2 "Welcometh to todo-app"]
   [:div
    [task-input-comp (r/cursor app-state [:tasks])]
    [task-list-comp (r/cursor app-state [:tasks])]]
   [:div [:a {:href "/about"} "go to about page"]]])

