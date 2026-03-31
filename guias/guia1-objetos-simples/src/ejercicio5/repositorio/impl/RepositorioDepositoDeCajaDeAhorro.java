package ejercicio5.repositorio.impl;

import ejercicio5.modelo.ahorro.DepositoDeCajaDeAhorro;
import ejercicio5.repositorio.Repositorio;

import java.util.Optional;

public class RepositorioDepositoDeCajaDeAhorro implements Repositorio<DepositoDeCajaDeAhorro> {
    @Override
    public void agregar(DepositoDeCajaDeAhorro depositoDeCajaDeAhorro) {

    }

    @Override
    public void quitar(String id) {

    }

    @Override
    public boolean existe(String id) {
        return false;
    }

    @Override
    public Optional<DepositoDeCajaDeAhorro> obtener(String id) {
        return Optional.empty();
    }
}
