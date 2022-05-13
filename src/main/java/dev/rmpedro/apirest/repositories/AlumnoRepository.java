package dev.rmpedro.apirest.repositories;


import dev.rmpedro.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository{
    @Query("select a from Alumno a where a.carrera.nombre = ?1")
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String carrera);

}
