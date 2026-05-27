; Definir la función capicua? que reciba un número entero no
; negativo de hasta 5 dígitos y devuelva true si el número es
; capicúa; si no, false.

(defn _capicua? [s]
  "Indica si la secuencia dada es capicúa."
  (case (count s)
    (0 1 ) true
    (let [f (first s)
          m (rest (butlast s))
          l (last s)]
      (and (= f l)
           (_capicua? m)))))

(defn capicua? [n]
  "
  Indica si el número dado es capicúa.
  Pre: El número dado debe ser un entero no negativo.
  "
  (_capicua? (seq (str n))))

(assert (capicua? 5))
(assert (capicua? 55))
(assert (capicua? 585))
(assert (capicua? 59895))
(assert (not (capicua? 5435)))