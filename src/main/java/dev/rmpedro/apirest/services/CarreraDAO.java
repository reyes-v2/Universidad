package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera>{
    Iterable<Carrera> findByCantidadAnios(Integer cantidad);
    Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
    Carrera actualizarCarrera(Carrera carreraEncontrada,Carrera carrera );
	


}
