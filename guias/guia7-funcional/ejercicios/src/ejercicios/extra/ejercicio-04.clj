; Escribir una función aplicar que reciba una secuencia xs y
; una función f (de un solo argumento), y retorne una nueva secuencia
; que contenga los resultados de aplicar f a cada uno de los elementos.

(defn aplicar
      "Retorna una secuencia con los resultados de aplicar la función f a cada elemento de xs."
      [xs f]
      (if (empty? xs)
        '()
        (cons (f (first xs)) (aplicar (rest xs) f))))

; (defn aplicar
;      "Retorna una secuencia con los resultados de aplicar la función f a cada elemento de xs."
;      [xs f]
;      (map f xs))