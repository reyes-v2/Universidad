package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import dev.rmpedro.apirest.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO{


    @Autowired
    public ProfesorDAOImpl(@Qualifier("repositorioProfesores")PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Persona> findProfesoresByCarrera(String carrera) {
        return ((ProfesorRepository)repository).findProfesoresByCarrera(carrera);
    }
    @Override
    public Persona actualizarProfesor(Persona profesorEncontrado, Profesor profesor) {
        Persona profesorActualizado = null;
        profesorEncontrado.setNombre(profesor.getNombre());
        profesorEncontrado.setApellido(profesor.getApellido());
        profesorEncontrado.setDireccion(profesor.getDireccion());
        ((Profesor)profesorEncontrado).setSueldo(profesor.getSueldo());
       profesorActualizado=repository.save(profesorEncontrado);
        return profesorActualizado;
    }

    @Override
    public Iterable<Profesor> buscarTodosProfesor() {
        return ((ProfesorRepository)repository).buscarTodosProfesor();
    }

    @Override
    public Optional<Profesor> buscarProfesorPorId(Integer id) {
        return ((ProfesorRepository)repository).buscarProfesorPorId(id);
    }


}
