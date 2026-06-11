; Retornar solo los elementos de la lista mayores a n.

(defn mayores-que [ns x]
      (cond
        (empty? ns)        '()
        (> (first ns) x)   (cons (first ns) (mayores-que (rest ns) x))
        :else              (mayores-que (rest ns) x)))

;; Versión idiomática: filter con función anónima como predicado
(defn mayores-que [ns x]
      (filter #(> % x) ns))