package ejercicio5.modelo;

import ejercicio5.modelo.ahorro.DepositoDeCajaDeAhorro;

import java.util.*;

public class Usuario {

    private final String id;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final Map<String, Cuenta> cuentas;
    private final Set<DepositoDeCajaDeAhorro> depositos;

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
        this.depositos = new HashSet<>();
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

    public void agregarDepositoDeCajaDeAhorro(DepositoDeCajaDeAhorro depositoDeCajaDeAhorro) {

        depositos.add(depositoDeCajaDeAhorro);
    }

    public void quitarDepositoDeCajaDeAhorro(DepositoDeCajaDeAhorro depositoDeCajaDeAhorro) {
        depositos.remove(depositoDeCajaDeAhorro);
    }

    public Cuenta obtenerCuenta(String cuentaId) {
        validarExistenciaDeCuenta(cuentaId);
        return cuentas.get(cuentaId);
    }

    public boolean esTitularDe(Cuenta cuenta) {
        return (cuenta != null) && cuentas.containsKey(cuenta.getId());
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

    public List<DepositoDeCajaDeAhorro> getDepositos() {
        return depositos.stream().toList();
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
