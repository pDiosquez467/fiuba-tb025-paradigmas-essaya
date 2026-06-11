; Retornar una lista con la longitud de cada sublista.

(defn longitud [xs]
      (if (empty? xs)
        0
        (+ 1 (longitud (rest xs)))))

(defn longitudes [xss]
      (if (empty? xss)
        '()
        (cons (longitud (first xss)) (longitudes (rest xss)))))

;; Las funciones son valores.
;; (defn longitudes [xss]
;;      (map longitud xss))