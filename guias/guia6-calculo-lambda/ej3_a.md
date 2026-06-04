### Árbol de la expresión

Expresión: `(λx. ((λy.y) x)) z`

        @
       / \
     λx   z
     |
     @
    / \
  λy    x
  |
  y

- `@` = aplicación  
- `λx` → cuerpo `((λy.y) x)`  
- `λy` → cuerpo `y`

---

### Reducción en orden normal

(λx. ((λy.y) x)) z  
= ((λy.y) z)          → β‑reducción de (λx. ...) z con [x := z]  
= z                   → β‑reducción de (λy.y) z con [y := z]

**Forma normal:** `z`

---

### Reducción en orden aplicativo

(λx. ((λy.y) x)) z  
= (λx. x) z           → β‑reducción interna: (λy.y) x con [y := x]  
= z                   → β‑reducción de (λx.x) z con [x := z]

**Forma normal:** `z`

---

Ambas estrategias producen `z`. No se requirió α‑conversión ni η‑reducción.
