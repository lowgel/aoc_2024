(ns aoc-2024.day2)

(def test-input "7 6 4 2 1
                 1 2 7 8 9
                 9 7 6 2 1
                 1 3 2 4 5
                 8 6 4 4 1
                 1 3 6 7 9")

(def real-input (slurp "resources/day2input.txt"))

;; parse the file into readable input
(defn parse-input [input]
  (map #(map parse-long (clojure.string/split % #"\s")) 
       (clojure.string/split input #"\n")))



;;-- not fast but really clean to look at :) might fix later
(defn asc? [coll]
  (if (= coll (sort coll))
    true
    false))

(defn dec? [coll]
  (if (= coll (reverse (sort coll)))
    true
    false))
;;--
(defn within1-3? [x y]
  (if (and (not (= x y)) (<= (abs (- x y)) 3))
    true
    false))
(defn close? [coll]
  (let [pairs (partition 2 1 coll)]
    (every? true? (map #(within1-3? (first %) (second %)) pairs)))) 

(defn safe? [coll]
  (if (and (or (asc? coll) (dec? coll)) (close? coll))
    true
    false))

(defn check-input [input]
  (reduce #(if (= %2 true) (+ 1 %1) %1) 0 (map safe? (parse-input input))))

(check-input real-input) ;;answer = 502
