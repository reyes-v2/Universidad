package dev.rmpedro.apirest;

import dev.rmpedro.apirest.entities.Alumno;
import dev.rmpedro.apirest.entities.Direccion;
import dev.rmpedro.apirest.entities.Persona;
import dev.rmpedro.apirest.services.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class UniversidadRestApplication {

	@Autowired
	private AlumnoDAO alumnoDao;

	public static void main(String[] args) {
		SpringApplication.run(UniversidadRestApplication.class, args);

	}

	//@Bean
	public CommandLineRunner runner(){
		return args ->{
			/*Direccion direccion = new Direccion("Heriberto jara","17","49088",
					null,null,"guzman");
			Persona alumno = new Alumno(null,"Ximena","Cruz","4234324234",direccion);
			Persona personaGuardada = alumnoDao.guardar(alumno);*/
			List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
			alumnos.forEach(System.out::println);
		};
	}

}
