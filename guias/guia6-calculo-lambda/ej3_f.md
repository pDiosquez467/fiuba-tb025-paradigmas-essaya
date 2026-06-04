(((λx.λy.λx.x y z) (λx.λy.y)) M) N

Aplicación
├── Aplicación
│   ├── Aplicación
│   │   ├── λx
│   │   │   └── λy
│   │   │       └── λx
│   │   │           └── Aplicación
│   │   │               ├── Aplicación
│   │   │               │   ├── x
│   │   │               │   └── y
│   │   │               └── z
│   │   └── λx
│   │       └── λy
│   │           └── y
│   └── M
└── N

## Orden normal:

(λx.λy.λx.x y z) (λx.λy.y) M N

=α (λa.λy.λu.u y z) (λx.λy.y) M N

=β (λy.λu.u y z) M N

=β (λu.u M z) N

=β N M z

## Orden aplicativo:

(λx.λy.λx.x y z) (λx.λy.y) M N

=α (λa.λy.λu.u y z) (λx.λy.y) M N

=β (λy.λu.u y z) M N

=β (λu.u M z) N

=β N M z
