package dev.rmpedro.apirest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class UniversidadRestApplication {

	/*@Autowired
	private ProfesorDAO profesorDao;
*/
	public static void main(String[] args) {
		SpringApplication.run(UniversidadRestApplication.class, args);

	}

	/*@Bean
	public CommandLineRunner runner(){
		return args ->{
			Direccion direccion = new Direccion("Heriberto jara","17","49088",
					null,null,"guzman");
			Persona alumno = new Alumno(null,"Ximena","Cruz","4234324234",direccion);
			Persona personaGuardada = alumnoDao.guardar(alumno);
			List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
			alumnos.forEach(System.out::println);
		};
	}*/

}
