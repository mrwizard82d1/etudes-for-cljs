(ns move-zeros.core
  (:require [domina :as d]
            [domina.events :as events]))

(enable-console-print!)

(println "This text is printed from src/move-zeros/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:original [1 0 2 0 3 0 4 0 5 0 6]}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

