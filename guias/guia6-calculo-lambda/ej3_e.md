## Árbol de la expresión

((λy.(λx.((λx.λy.x) x)) y) M) N

@
├── @
│   ├── λy
│   │   └── @
│   │       ├── λx
│   │       │   └── @
│   │       │       ├── λx
│   │       │       │   └── λy
│   │       │       │       └── x
│   │       │       └── x
│   │       └── y
│   └── M
└── N

## Orden normal

Reducimos siempre la β-redex más externa y más a la izquierda.

((λy.(λx.((λx.λy.x) x)) y) M) N

=β ((λx.((λx.λy.x) x)) M) N

=β ((λx.λy.x) M) N

=β (λy.M) N

=β M

## Orden aplicativo

Reducimos siempre la β-redex más interna y más a la izquierda.

((λy.(λx.((λx.λy.x) x)) y) M) N

=β ((λy.(λx.λy.x) y) M) N

=α ((λy.(λx.λu.x) y) M) N

=β ((λy.λu.y) M) N

=β (λu.M) N

=β M
