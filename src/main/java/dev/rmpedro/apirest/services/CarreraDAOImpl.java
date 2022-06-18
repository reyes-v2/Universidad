package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;

import java.util.List;

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
		List<Carrera> carreras = (List<Carrera>) repository.buscarCarrerasPorProfesorNombreYApellido(nombre,apellido);
		if(carreras.isEmpty()){
			throw new NotFoundException("No se encontraron carreras con el profesor: " + nombre + " " + apellido);
		}
		return carreras;
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
