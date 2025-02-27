(ns aoc-2024.day1)

(defn seperate 
"seperates the input into 2 vectors"
[input] (loop [i input
               l1 []
               l2 []]
          (if (empty? i)
            [(sort l1) (sort l2)]
            (recur (rest (rest i)) (conj l1 (first i)) (conj l2 (second i))))))


(defn find-val
"finds the difference between each index of the two lists" 
[lists] (loop [l1 (first lists)
               l2 (second lists)
               val 0]
          (if (empty? l1)
            val
            (recur (rest l1) (rest l2) (+ val (abs (- (first l1) (first l2))))))))



;part 1 testing
(find-val (seperate [3   4
                     4   3
                     2   5
                     1   3
                     3   9
                     3   3]))


;part 1 solution
(def input  (map parse-long (clojure.string/split (slurp "resources/day1input.txt") #"\s+")))
(find-val (seperate input))



;part 2 solution
(defn sim-score
  "Takes a vector of two lists. finds the similarity score of the 2 lists"
  [lists] (let [l1 (first lists)
                l2 (second lists)
                freq-map (frequencies l2)
                ans (reduce #(+ %1 (if (get freq-map %2) (* %2 (get freq-map %2)) 0)) 0 l1)] ans)) ;if statement is a little messy but necessary in case value isnt found in the frequency map

(sim-score (seperate input))



