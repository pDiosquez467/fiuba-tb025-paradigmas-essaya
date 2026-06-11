; Retornar una lista con el doble de cada elemento.

(defn dobles [ns]
      (if (empty? ns)
        '()
        (cons (* (first ns) 2) (dobles (rest ns)))))

; (defn dobles [ns]
;      (map #(* % 2) ns))