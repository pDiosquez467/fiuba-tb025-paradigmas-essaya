### Árbol de la expresión

Expresión: `(λx.λy.x) x y`

Nota: La aplicación asocia a la izquierda → `((λx.λy.x) x) y`

        @
       / \
     @    y
    / \
  λx   x
  |
  λy
  |
  x

- `λx` → cuerpo `λy.x`  
- `λy` → cuerpo `x`  
- Variable libre: `x` (el argumento de la primera aplicación) e `y` (el argumento externo)  
- No hay conflicto de nombres porque el `x` en `λy.x` está ligado por `λx`, y el `x` argumento es libre. No se requiere α‑conversión.

---

### Reducción en orden normal

(λx.λy.x) x y  

= ((λx.λy.x) x) y          → (asociación implícita)

= (λy.x) y                 → β‑reducción de (λx.λy.x) x con [x := x]  
                           (se sustituye la variable ligada `x` por la variable libre `x`; el resultado es `λy.x`)

= x                        → β‑reducción de (λy.x) y con [y := y]  
                           (el cuerpo `x` no contiene `y`, por lo que da `x`)

**Forma normal:** `x`

---

### Reducción en orden aplicativo

(λx.λy.x) x y  

= ((λx.λy.x) x) y          → asociación

= (λy.x) y                 → β‑reducción de (λx.λy.x) x (única redex, misma que antes)

= x                        → β‑reducción de (λy.x) y

**Forma normal:** `x`

Ambas estrategias coinciden exactamente porque no hay redex internas que reducir antes.

---

### Revisión de la solución del usuario (demo.txt)

**Orden normal:**  
`(λx.λy.x) x y = ((λx.(λy.x)) x) y = (beta) (λy.x) y = (beta) x` ✅

**Orden aplicativo:**  
`(λx.λy.x) x y = ((λx.(λy.x)) x) y = (beta) (λy.x) y = (beta) x` ✅

**Resultado final:** `x`
