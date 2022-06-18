package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;

import java.util.List;
import java.util.Optional;


public interface ProfesorDAO extends PersonaDAO{
    List<Profesor> findProfesoresByCarrera(String carrera);
    Persona actualizarProfesor(Persona profesorEncontrado, Profesor profesor);
    Iterable <Profesor> buscarTodosProfesor();
    Optional<Profesor> buscarProfesorPorId(Integer id);

}
