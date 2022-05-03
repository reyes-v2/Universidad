package dev.rmpedro.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera,CarreraRepository> implements CarreraDAO{
	
	@Autowired
	public CarreraDAOImpl(CarreraRepository repository) {
		super(repository);
	}



}
