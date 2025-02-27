(ns aoc-2024.day2)
;;PART 1

(def real-input (slurp "resources/day2input.txt"))

;; parse the file into readable input
(defn parse-input [input]
  (map #(map parse-long (clojure.string/split % #"\s")) 
       (clojure.string/split input #"\n")))


(defn in-order? [coll]
  (or (apply < coll) (apply > coll)))


(defn within1-3? [x y]
  (if (and (not (= x y)) (<= (abs (- x y)) 3))
    true
    false))

(defn close? [coll]
  (let [pairs (partition 2 1 coll)]
    (every? true? (map #(within1-3? (first %) (second %)) pairs)))) 

(defn safe? [coll]
  (if (and (in-order? coll) (close? coll))
    true
    false))

(defn check-input [input]
  (count (filter true?  (map safe? (parse-input input)))))

(check-input real-input) ;; => 502

;; PART 2
(defn get-permutations [input]
  (for [i (range (+ 1 (count input)))]
    (keep-indexed #(when (not= %1 i) %2) input)))

(defn p2-safe? [input]
  (->> input
       (get-permutations)
       (map safe?)
       (filter true?)
       (first)))

(defn p2-check-input [input]
  (count (filter true? (map p2-safe? (parse-input input)))))



(p2-check-input real-input) ;; => 544
