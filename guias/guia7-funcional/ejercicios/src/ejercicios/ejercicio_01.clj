; Definir la función segundos que reciba los cuatro valores (días, horas, minutos y segundos)
; del tiempo que dura un evento y devuelva el valor de ese tiempo expresado solamente en segundos.

(defn a_segundos [dias horas minutos segundos]
  "Recibe los cuatro valores de tiempo que dura un
  evento y devuelve el total en segundos."
  (+ segundos
     (* minutos 60)
     (* horas 60 60)
     (* dias * 60 60 24)))

(assert (= 3660 (a_segundos 0 1 1 0)))