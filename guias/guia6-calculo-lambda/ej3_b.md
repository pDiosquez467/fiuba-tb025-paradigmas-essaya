### Árbol de la expresión

Expresión: `(λx.λy.x y) (z y)`

        @
       / \
     λx   (z y)
     |
     λy
     |
     @
    / \
   x   y

- `@` = aplicación  
- `λx` → cuerpo `λy.(x y)`  
- `λy` → cuerpo `(x y)`  
- Variable libre en `(z y)`: `y` (requiere α‑conversión)

---

### Reducción en orden normal (y aplicativo, coinciden)

(λx.λy.x y) (z y)  

= (λx.λa.x a) (z y)          → α‑conversión de `λy` a `λa` (a fresca, evita captura de `y`)

= λa.(z y) a                 → β‑reducción: sustituir `x := (z y)` en `λa.x a`

= z y                        → η‑reducción: `λa.(z y) a` con `a` no libre en `(z y)`

**Forma normal:** `z y`

---

### Nota

No hay diferencias entre orden normal y aplicativo aquí, porque la única β‑redex es la más externa.  
La α‑conversión es obligatoria para evitar que el `y` libre en el argumento sea capturado por `λy`.
