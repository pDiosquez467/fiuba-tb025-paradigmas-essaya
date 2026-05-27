; Definir una función que devuelva true si una frase es un pangrama
; (es decir, si contiene todas las letras del alfabeto); si no, false.
; Por ejemplo:
;(pangrama? "Fabio me exige, sin tapujos, que añada cerveza al whisky") → true

(ns ejercicios.ejercicio_07
  (:require
    [clojure.string :refer [lower-case includes?]]))

(defn pangrama? [frase]
  "Indica si la frase contiene todas las letras del alfabeto."
  (let [
        alfabeto "abcdefghijklmnñopqrstuvwxyz"
        frase (lower-case frase)
        ]
    (every? #(includes? frase (str %)) alfabeto)))