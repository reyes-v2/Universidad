package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;

public interface AlumnoDAO extends PersonaDAO{
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String carrera);
    Persona actualizarAlumno(Persona alumnoEncontrado,Persona alumno);
    Persona asignarCarrera(Persona alumno, Carrera carrera);


}
