(ns formulas.core
  (:require [clojure.browser.repl :as repl]))

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(println "Hello world!")

(defn distance 
  "Calculate the distance travelled by an object with acceleration, `a` for time, `t`."
  [a t]
  (/ (* a t t) 2))

(defn kinetic-energy
  "Calculate the kinetic energy of an object with mass, `m` moving with velocity, `v`."
  [m v]
  (/ (* m v v) 2))

(defn centripetal
  "Calculates the centripetal acceleration of an object traveling in a circle of radius, `r` with velocity, `v`."
  [v r]
  (/ (* v v) r))

(def G 6.67408e-11)

(defn gravitational-force
  "Calculates the gravitational force between two objects of masses, `m1` and `m2`, separated by a distance, `r`."
  [m1 m2 r]
  (/ (* G m1 m2) (* r r))) 

(defn monthly-payment
  "Calculate the monthly payment for a loan of principle, `p`, a term of `y` years, and an annual interest rate
(percentage) of `i`."
  [p i y]
  (let [r (/ i 100 12) ;; monthly rate
        n (* y 12) ;; term (number of months)
        compounded-interest (.pow js/Math (+ 1 r) n) ;; interest on $1 over term
        ]
    (* p r (/ compounded-interest (- compounded-interest 1)))))
