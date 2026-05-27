; Definir la función qsort que recibe una secuencia y la ordena usando
; el algoritmo Quicksort.

(defn qsort [xs]
  "
  Recibe una secuencia de elementos y devuelve una nueva ordenada
  de menor a mayor.
  "
  (if (empty? xs)
    xs
    (let [
          pivot (first xs)
          resto (rest xs)
          ]
      (concat
        (qsort (filter #(<= % pivot) resto))
        [pivot]
        (qsort (filter #(> % pivot) resto))))))

(assert (= '(1 2 3 4 5 5 6) (qsort '(2 3 1 5 4 6 5))))