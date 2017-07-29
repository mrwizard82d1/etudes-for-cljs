(ns daylight.formulas)

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

(defn radians
  "Convert degrees (of latitute) to radians."
  [degrees]
  (* (/ (.-PI js/Math) 180) degrees))

(defn daylight-in-minutes
  "Calculates the amount of daylight, in minutes, for the day, `julian-day` for a location on `latitude`.

   This function uses the algorithm described at 
   http://mathforum.org/library/drmath/view/56478.html."
  [julian-day latitude-degrees]
  (let [arcsin #(.asin js/Math %)
        arccos #(.acos js/Math %)
        arctan #(.atan js/Math %)
        sin #(.sin js/Math %)
        cos #(.cos js/Math %)
        tan #(.tan js/Math %)
        latitude (radians latitude-degrees)
        P (arcsin (* 0.39795
                     (cos (+ 0.2163108
                             (* 2 
                                (arctan (* 0.9671396
                                           (tan (* 0.00860
                                                   (- julian-day 186))))))))))
        D (- 24
             (* 7.63944
                (arccos (/ (+ (sin 0.01454)
                              (* (sin latitude)
                                 (sin P)))
                           (* (cos latitude)
                              (cos P))))))]
    (* 60 D) ;; convert daylight hours to minutes
    ))

