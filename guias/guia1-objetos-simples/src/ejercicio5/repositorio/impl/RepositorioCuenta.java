package ejercicio5.repositorio.impl;

import ejercicio5.modelo.Cuenta;
import ejercicio5.repositorio.Repositorio;
import ejercicio5.repositorio.validaciones.Validaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioCuenta implements Repositorio<Cuenta> {

    private final Map<String, Cuenta> db;

    public RepositorioCuenta() {
        this.db = new HashMap<>();
    }

    @Override
    public void agregar(Cuenta cuenta) {
        Validaciones.validarNoExistenciaEnLaBD(this, cuenta.getId(), "La cuenta ya existe en la BD");
        db.put(cuenta.getId(), cuenta);
    }

    @Override
    public void quitar(String id) {
        Validaciones.validarExistenciaEnLaBD(this, id, "La cuenta no existe en la BD");
        db.remove(id);
    }

    @Override
    public boolean existe(String id) {
        return db.containsKey(id);
    }

    @Override
    public Optional<Cuenta> obtener(String id) {
        return Optional.ofNullable(db.get(id));
    }
}
