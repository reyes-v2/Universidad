package dev.rmpedro.apirest.services;

import java.util.Optional;

import dev.rmpedro.apirest.entities.Carrera;

public interface CarreraDAO {
	
	Optional<Carrera> buscarPorId(Integer id);
	Carrera guardar(Carrera carrera);
	Iterable<Carrera> buscarTodos();
	void eliminarPorId(Integer id);
	

}
