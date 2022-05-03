package dev.rmpedro.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;
import dev.rmpedro.apirest.services.CarreraDAO;

import java.util.Optional;


@Component
public class GenericoComandos implements CommandLineRunner {

	@Autowired
	private CarreraDAO carreraDAO;
	@Override
	public void run(String... args) throws Exception {
	/*
		Carrera finanzas = new Carrera(null,"Ingenieria en Finanzas",20,3);
		Carrera carrera = carreraDAO.guardar(finanzas);
		System.out.print(carrera);
		*/
		Carrera carrera = null;
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(1);
		if(oCarrera.isPresent()){
			 carrera = oCarrera.get();
			System.out.println(carrera.toString());
		}
		else{
			System.out.println("Carrera no encontrada");
		}
		carrera.setCantidadAnios(12);
		carrera.setCantidadMaterias(48);
		carreraDAO.guardar(carrera);
		
		
	}

}
