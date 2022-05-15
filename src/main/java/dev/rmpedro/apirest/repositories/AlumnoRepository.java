package dev.rmpedro.apirest.repositories;


import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository{
    @Query("select a from Alumno a where a.carrera.nombre = ?1")
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String carrera);
    @Query("select a from Alumno a")
    Iterable<Alumno>  buscarTodosAlumnos();
    @Query("select a from Alumno a where a.id=?1")
    Optional<Alumno> buscarAlumnoId(Integer id);


}
