package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Persona;

public interface AlumnoDAO extends PersonaDAO{
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String carrera);


}
