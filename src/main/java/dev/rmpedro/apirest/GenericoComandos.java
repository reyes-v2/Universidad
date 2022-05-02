package dev.rmpedro.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;
import dev.rmpedro.apirest.services.CarreraDAO;


@Component
public class GenericoComandos implements CommandLineRunner {

	@Autowired
	private CarreraDAO carreraDAO;
	@Override
	public void run(String... args) throws Exception {
		
		/*Carrera finanzas = new Carrera(null,"Ingenieria en Finanzas",20,3);
		Carrera carrera = carreraDAO.guardar(finanzas);
		System.out.print(carrera);*/
		
		
		
	}

}
