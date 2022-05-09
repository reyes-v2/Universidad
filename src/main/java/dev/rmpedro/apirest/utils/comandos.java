package dev.rmpedro.apirest.utils;

import dev.rmpedro.apirest.entities.Aula;
import dev.rmpedro.apirest.entities.Pabellon;
import dev.rmpedro.apirest.entities.Persona;
import dev.rmpedro.apirest.enums.Pizarron;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import dev.rmpedro.apirest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;

import java.util.Optional;

@Component
public class comandos implements CommandLineRunner {

    @Autowired
    private ProfesorDAO profesorDAO;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private CarreraDAO carreraDAO;
    @Autowired
    private AulaDAO aulaDAO;
    @Autowired
    private PabellonDAO pabellonDAO;
    @Override
    public void run(String... args) throws Exception {
	/*
		Carrera finanzas = new Carrera(null,"Ingenieria en Finanzas",20,3);
		Carrera carrera = carreraDAO.guardar(finanzas);
		System.out.print(carrera);
		*/
	/*	Carrera carrera = null;
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
		*/

	/*	Iterable<Persona> profesoresFinanzas = profesorDAO.findProfesoresByCarrera("Ingenieria en Finanzas");
		profesoresFinanzas.forEach(System.out::println);*/

	/*	Iterable<Persona> empleados = empleadoDAO.findEmpleadoByTipoEmpleado(TipoEmpleado.MANTENIMIENTO);
		empleados.forEach(System.out::println);*/
		/*Iterable<Carrera> carreras = carreraDAO.buscarCarrerasPorProfesorNombreYApellido("Pedro","Reyes");
		carreras.forEach(System.out::println);*/
		/*Iterable<Aula> aulas = aulaDAO.findAulaByPizarronEquals(Pizarron.PIZARRA_BLANCA);
		aulas.forEach(System.out::println);*/

		/*Iterable<Aula> aulas = aulaDAO.findAulaByPabellonNombre("Pabellon Arreola");
		aulas.forEach(System.out::println);*/

        Iterable<Pabellon> pabellon = pabellonDAO.findPabellonsByDireccionLocalidad("Cd Guzman");
        pabellon.forEach(System.out::println);



    }

}