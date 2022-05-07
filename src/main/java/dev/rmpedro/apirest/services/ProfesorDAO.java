package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Persona;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


public interface ProfesorDAO extends PersonaDAO{
    Iterable<Persona> findProfesoresByCarrera(String carrera);
}
