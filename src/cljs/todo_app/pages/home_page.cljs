(ns todo-app.pages.home-page
  (:require [reagent.core :as r]))

;; GLOBALS
(defonce counter (r/atom 0))

(defonce app-state (r/atom {:tasks (hash-map)}))

;; CHECKBOX
(defn checkbox [tasks id done]
  [:input {:type "checkbox" :checked done
           :on-change #(swap! tasks update-in [id :done] not)}])

;; DELETE
(defn delete-task [tasks id]
  [:button
   {:style {:margin 10
            :padding 10
            :fontWeight "bold"}
    :on-click #(swap! tasks dissoc id)}
   "Delete me"])

;; LIST ITEMS
(defn task-list-comp [tasks]
  [:ul
   (for [[id {done :done title :title}] @tasks]
     ^{:key id}
     [:li title
      [checkbox tasks id]
      [delete-task tasks id done]])])

;; INPUT
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

;; MAIN
(defn home-page []
  [:div 
   [:h2 "Welcometh to todo-app"]
   [:div
    [task-input-comp (r/cursor app-state [:tasks])]
    [task-list-comp (r/cursor app-state [:tasks])]]
   [:div [:a {:href "/about"} "go to about page"]]])

