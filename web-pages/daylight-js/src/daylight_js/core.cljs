(ns daylight-js.core
  (:require [daylight-js.formulas :as formulas]))

(enable-console-print!)

(println "This text is printed from src/daylight-js/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn getElement
  "Get the DOM element identified by `id` in `document`."
  [document id]
  (.getElementById document id))

(defn get-numeric-value
  "Gets a numeric value from component identified by `id` in `document`."
  [id document]
  (->> id
       (getElement document)
       (.-value)
       (.parseFloat js/window)))

(defn get-latitude
  "Get the latitude from the appropriate control on the page."
  [document]
  (get-numeric-value "latitude" document))

(defn get-day-of-year
  "Get the day of year (Julian day) from the appropriate control on the page."
  [document]
  (get-numeric-value "julian" document))

(defn on-calculate [evt]
  (let [latitude (get-latitude js/document)
        day-of-year (get-day-of-year js/document)
        daylight (formulas/daylight-in-minutes day-of-year latitude)]
    (set! (->> "result"
               (getElement js/document)
               (.-innerHTML))
          daylight)))

(let [btn (getElement js/document "calculate")]
  (.addEventListener btn "click" on-calculate))
