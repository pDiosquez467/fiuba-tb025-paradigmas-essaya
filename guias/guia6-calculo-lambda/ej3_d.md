### Árbol de la expresión

Expresión: `(λx.((λz.z x) (λx.x))) y`

        @
       / \
     λx   y
     |
     @
    / \
  λz   λx
  |    |
  @    x
 / \
z   x

- λx externo → cuerpo ((λz.z x) (λx.x))
- λz → cuerpo (z x)
- λx interno → cuerpo x (independiente)

### Reducción en orden normal

(λx.((λz.z x) (λx.x))) y
= ((λz.z y) (λx.x))             → β [x := y]
= (λx.x) y                      → β [z := (λx.x)]
= y                             → β [x := y]

### Reducción en orden aplicativo

(λx.((λz.z x) (λx.x))) y
= (λx.((λx.x) x)) y             → β interna [z := (λx.x)]
= (λx.x) y                      → β [x := y]
= y                             → β [x := y]

**Forma normal:** y
