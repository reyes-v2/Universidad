package dev.rmpedro.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;

@Service
public class CarreraDAOImpl implements CarreraDAO{
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Optional<Carrera> buscarPorId(Integer id) {
		return carreraRepository.findById(id);
	}

	@Transactional
	@Override
	public Carrera guardar(Carrera carrera) {
		return carreraRepository.save(carrera);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Carrera> buscarTodos() {
		return carreraRepository.findAll();
	}
	
	@Transactional
	@Override
	public void eliminarPorId(Integer id) {
		carreraRepository.deleteById(id);
	}

}
