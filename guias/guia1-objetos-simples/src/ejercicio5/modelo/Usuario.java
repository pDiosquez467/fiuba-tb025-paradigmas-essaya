package ejercicio5.modelo;

import java.util.*;

public class Usuario {

    private final String id;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final Map<String, Cuenta> cuentas;

    public Usuario(
            String id,
            String nombre,
            String direccion,
            String telefono
    ) {
        this.id        = id;
        this.nombre    = nombre;
        this.direccion = direccion;
        this.telefono  = telefono;
        this.cuentas   = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    public void agregarCuenta(Cuenta cuenta) {
        validarNoExistenciaDeCuenta(cuenta.getId());
        cuentas.put(cuenta.getId(), cuenta);
    }

    public void quitarCuenta(String cuentaId) {
        validarExistenciaDeCuenta(cuentaId);
        cuentas.remove(cuentaId);
    }

    public Cuenta obtenerCuenta(String cuentaId) {
        validarExistenciaDeCuenta(cuentaId);
        return cuentas.get(cuentaId);
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

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Cuenta> getCuentas() {
        return cuentas.values().stream().toList();
    }

    private void validarExistenciaDeCuenta(String cuentaId) {
        if (!cuentas.containsKey(cuentaId)) {
            throw new RuntimeException("El usuario no posee una cuenta con el identificador indicado.");
        }
    }

    private void validarNoExistenciaDeCuenta(String cuentaId) {
        if (cuentas.containsKey(cuentaId)) {
            throw new RuntimeException("El usuario ya posee una cuenta con el identificador indicado.");
        }
    }

}
