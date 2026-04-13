package ejercicio3.modelo.us;

import validaciones.Validaciones;

import java.util.Objects;

public abstract class Usuario {

    private final String id;
    private final String nombre;
    private final String credencial;

    public Usuario(
            String id,
            String nombre,
            String credencial
    ) {
        Validaciones.validarNotBlank(id, "id");
        Validaciones.validarNotBlank(nombre, "nombre");
        Validaciones.validarNotBlank(credencial, "credencial");
        this.id = id;
        this.nombre = nombre;
        this.credencial = credencial;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCredencial() {
        return credencial;
    }
}
