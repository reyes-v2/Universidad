package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GenericoDAOImpl <E,R extends CrudRepository<E,Integer>> implements GenericoDAO<E>{


    protected final R repository;
    public GenericoDAOImpl(R repository){
        this.repository=repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> buscarPorId(Integer id) {
        Optional<E> optionalE = repository.findById(id);
        if(!optionalE.isPresent()){
            throw new NotFoundException("No existe objeto con el Id: " + id);
        }
        return optionalE;
    }

    @Override
    @Transactional
    public E guardar(E entidad) {
        return repository.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> buscarTodos() {
        Iterable<E> objTodos =  repository.findAll();
        if (((List)objTodos).isEmpty()){
            throw new NotFoundException("No hay elementos que mostrar");
        }
        return objTodos;
    }

    @Override
    public void eliminarPorId(Integer id) {
        Optional<E> obeto = buscarPorId(id);
        repository.deleteById(id);
    }
}
