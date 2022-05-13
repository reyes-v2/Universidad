package dev.rmpedro.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.rmpedro.apirest.models.entities.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Integer>{

    Iterable<Carrera> findByCantidadAnios(Integer cantidad);
    Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
    @Query("Select c from Carrera c join fetch c.profesores p where p.nombre=?1 and p.apellido = ?2")
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);


}
