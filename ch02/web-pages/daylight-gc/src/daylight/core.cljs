(ns daylight.core
  (:require [daylight.formulas :as formulas]
            [goog.dom :as dom]
            [goog.events :as events]))

(enable-console-print!)

(println "This text is printed from src/daylight/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn get-numeric-value
  "Gets a numeric value from component identified by `id`."
  [id]
  (->> id
       (dom/getElement)
       (.-value)
       (.parseFloat js/window)))

(defn get-latitude
  "Get the latitude from the appropriate control on the page."
  []
  (get-numeric-value "latitude"))

(defn get-day-of-year
  "Get the day of year (Julian day) from the appropriate control on the page."
  []
  (get-numeric-value "julian"))

(defn on-calculate [evt]
  (let [latitude (get-latitude)
        day-of-year (get-day-of-year)
        daylight (formulas/daylight-in-minutes day-of-year latitude)]
    (dom/setTextContent (dom/getElement "result") daylight)))

(let [btn (dom/getElement "calculate")]
  (events/listen btn "click" on-calculate))
