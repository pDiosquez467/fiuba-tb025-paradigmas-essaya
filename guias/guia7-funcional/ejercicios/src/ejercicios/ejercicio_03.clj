;; Aproximación de Pi mediante la serie de Leibniz.

(defn alt-sign
  "Devuelve 1 si el índice `i` es par, o -1 si es impar.
  Se utiliza para alternar el signo de los términos sucesivos en la serie."
  [i]
  (if (even? i) 1 -1))

(defn denominador
  "Calcula el n-ésimo denominador de la serie.
  Responde a la fórmula 2n + 1."
  [n]
  (+ (* 2 n) 1))

(defn term-pi
  "Calcula el valor del n-ésimo término individual de la secuencia:
  (-1)^n / (2n + 1)."
  [n]
  (* (alt-sign n) (/ 1 (denominador n))))

(defn terms-pi
  "Genera una secuencia (lazy sequence) con los primeros n términos
  de la serie."
  [n]
  (map term-pi (range n)))

(defn aprox-pi
  "Calcula una aproximación del número pi utilizando n términos de la serie
  de Gregory-Leibniz."
  [n]
  (double (* 4 (reduce + (terms-pi n)))))