; Escribir una función repetidos que reciba dos secuencias,
; lista y otra, y retorne una nueva secuencia que contenga
; únicamente los elementos que están presentes en ambas.

(defn repetidos
      "Retorna una secuencia con los elementos presentes en ambas colecciones."
      [lista otra]
      (cond
        (empty? lista) '()
        (some #(= (first lista) %) otra) (cons (first lista) (repetidos (rest lista) otra))
        :else (repetidos (rest lista) otra)))