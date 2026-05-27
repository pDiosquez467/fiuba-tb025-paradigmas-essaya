; Definir la función nth-fibo que reciba un número entero no
; negativo y devuelva el correspondiente término de la sucesión
; de Fibonacci.

(defn nth_fibo [n]
  "Recibe un número natural y devuelve el correspondiente término de la
  sucesion de Fibonacci."
  (case n)
  (0 1) 1
  (+ (nth_fibo (- n 1)) (nth_fibo (- n 2))))

(assert (= '(1 1 2 3 5 8 13 21) (map nth_fibo (range 8))))