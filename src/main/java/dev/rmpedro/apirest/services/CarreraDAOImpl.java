package dev.rmpedro.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera,CarreraRepository> implements CarreraDAO{
	
	@Autowired
	public CarreraDAOImpl(CarreraRepository repository) {
		super(repository);
	}


	@Override
	public Iterable<Carrera> findByCantidadAnios(Integer cantidad) {
		return repository.findByCantidadAnios(cantidad);
	}

	@Override
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) {
		return repository.findCarrerasByNombreContainsIgnoreCase(nombre);
	}

	@Override
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios) {
		return repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
	}

	@Override
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido) {
		return repository.buscarCarrerasPorProfesorNombreYApellido(nombre,apellido);
	}

	@Override
	public Carrera actualizarCarrera(Carrera carreraEncontrada, Carrera carrera) {
		Carrera carreraActualizada = null;
		carreraEncontrada.setCantidadAnios(carrera.getCantidadAnios());
		carreraEncontrada.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraActualizada=repository.save(carreraEncontrada);
		return carreraActualizada;

	}
}
