package dev.rmpedro.apirest.repositories;


import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository{

   @Query("select p from Profesor p join fetch p.carreras c where c.nombre = ?1")
    Iterable<Persona> findProfesoresByCarrera(String carrera);
    @Query("select p from Profesor p")
    Iterable<Profesor>  buscarTodosProfesor();
    @Query("select p from Profesor p where p.id=?1")
    Optional<Profesor> buscarProfesorPorId(Integer id);


}
