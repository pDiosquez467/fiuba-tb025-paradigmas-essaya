; Retornar una secuencia de enteros entre inicio y fin, ambos inclusive.

;; Versión original: recursión directa
#_(defn entre-numeros [inicio fin]
        (if (> inicio fin)
          '()
          (cons inicio (entre-numeros (inc inicio) fin))))

;; Versión corregida: lazy-seq evita consumir stack
;; `when` devuelve nil (en lugar de '()) cuando la condición no se cumple
(defn entre-numeros
      "Retorna una secuencia lazy de enteros desde inicio hasta fin, ambos inclusive."
      [inicio fin]
      (when (<= inicio fin)
            (lazy-seq (cons inicio (entre-numeros (inc inicio) fin)))))

;; Alternativa con la std lib (range es exclusivo en fin, por eso (inc fin))
#_(defn entre-numeros [inicio fin]
        (range inicio (inc fin)))