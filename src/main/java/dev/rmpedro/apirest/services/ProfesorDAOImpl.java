package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import dev.rmpedro.apirest.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Iterable<Profesor> profesores = ((ProfesorRepository)repository).buscarTodosProfesor();
        if(((List<Profesor>)profesores).isEmpty()){
            throw new NotFoundException("No hay profesores que mostrar");
        }
        return profesores;
    }

    @Override
    public Optional<Profesor> buscarProfesorPorId(Integer id) {
        Optional<Profesor> profesor =((ProfesorRepository)repository).buscarProfesorPorId(id);
        if(!profesor.isPresent()){
            throw new NotFoundException("No existe el profesor con el ID" + id);
        }
        return profesor;
    }


}
