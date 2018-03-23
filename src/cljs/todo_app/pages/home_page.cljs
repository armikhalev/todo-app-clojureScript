(ns todo-app.pages.home-page
  (:require [reagent.core :as r]))

(defonce app-state  (r/atom {:tasks ["one", "two"]}))

;; -------------------------
;; Views

(defn task-list-comp [tasks]
  [:ul
   (for [[index task] (map-indexed vector @tasks)]
     ^{:key index}
     [:li task])])

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
             (swap! tasks #(conj % @task-input))
             (reset! task-input "")
             (.preventDefault e)))} ]])))

(defn home-page []
  [:div {:style {:color "red"
                 :backgroundColor "yellow"}}
   [:h2 "Welcometh to todo-app"]
   [:div
    [task-input-comp (r/cursor app-state [:tasks])]
    [task-list-comp (r/cursor app-state [:tasks])]]
   [:div [:a {:href "/about"} "go to about page"]]])

