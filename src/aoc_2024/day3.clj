(ns aoc-2024.day3)

;;PART 1
 (defn find-valid [input]
  (let [matches (re-seq #"mul\((\d+),(\d+)\)" input)
        products (map #(* (parse-long (second %)) (parse-long (last %))) matches)
        ans (reduce + products)]
    ans))

  (find-valid (slurp "resources/day3input.txt")); => 160672468

;;PART 2
(defn clean [input]
  (let [fixed (str "do()" input "don't")
        ans (re-seq #"do\(\)(?s).*?don't\(\)" fixed)]
    (apply str ans)))

(find-valid (clean (slurp "resources/day3input.txt"))); => 84893551
