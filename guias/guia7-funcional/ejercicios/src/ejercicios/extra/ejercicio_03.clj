; Retornar la lista sin elementos duplicados.

(defn eliminar-repetidos [xs]
      (cond
        (empty? xs)                         '()
        (some #(= (first xs) %) (rest xs))  (eliminar-repetidos (rest xs))
        :else                               (cons (first xs)
                                                  (eliminar-repetidos (rest xs)))))

#_(defn eliminar-repetidos [xs]
        (distinct xs))