package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Persona;


public interface ProfesorDAO extends PersonaDAO{
    Iterable<Persona> findProfesoresByCarrera(String carrera);
}
