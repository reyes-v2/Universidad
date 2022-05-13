package dev.rmpedro.apirest.services;

import java.util.Optional;

public interface GenericoDAO <E>{

    Optional<E> buscarPorId(Integer id);
    E guardar(E entidad);
    Iterable<E> buscarTodos();
    void eliminarPorId(Integer id);

}
