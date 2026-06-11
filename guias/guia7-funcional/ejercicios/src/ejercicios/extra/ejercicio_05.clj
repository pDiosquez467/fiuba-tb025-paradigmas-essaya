; Retornar la suma de todos los elementos de una lista de enteros

(defn sumar [ns]
      (if (empty? ns)
        0
        (+ (first ns) (sumar (rest ns)))))

; (defn sumar [ns]
;      (reduce + 0 ns))