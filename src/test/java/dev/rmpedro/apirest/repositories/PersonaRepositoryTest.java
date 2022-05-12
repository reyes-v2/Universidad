package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.EmpleadoDatosDummy;
import dev.rmpedro.apirest.entities.Alumno;
import dev.rmpedro.apirest.entities.Empleado;
import dev.rmpedro.apirest.entities.Persona;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static dev.rmpedro.apirest.datos.EmpleadoDatosDummy.*;
import static dev.rmpedro.apirest.datos.ProfesorDatosDummy.*;
import static dev.rmpedro.apirest.datos.AlumnoDatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonaRepositoryTest {

    @Autowired
    @Qualifier("repositorioAlumnos")
    private PersonaRepository alumnoRepository;

    @Autowired
    @Qualifier("repositorioEmpleados")
    private PersonaRepository empleadoRepository;

    @Autowired
    @Qualifier("repositorioProfesores")
    private PersonaRepository profesorRepository;


    @Test
    @DisplayName("Buscar Por nommbre y apellido")
    void buscarPorNombreApeliido() {
        //Given
       Persona personaEmpleado= empleadoRepository.save(empleado01());
        //When
        Optional<Persona> expected = empleadoRepository.buscarPorNombreApeliido(empleado01().getNombre(),empleado01().getApellido());
        //Then
        assertThat(expected.get()).isInstanceOf(Empleado.class);
        assertThat(expected.get()).isEqualTo(personaEmpleado);
    }

    @Test
    void buscarPorDni() {
        //Given
        Persona personaAlumno = alumnoRepository.save(alumno01());
        //When
        Optional<Persona> expected = alumnoRepository.buscarPorDni(alumno01().getDni());
        //Then
        assertThat(expected.get()).isInstanceOf(Alumno.class);
        assertThat(expected.get()).isEqualTo(personaAlumno);
    }

    @Test
    void buscarPorApellido() {
        //Given
       Iterable<Persona> profesores = profesorRepository.saveAll(Arrays.asList(profesor01(),profesor02()
       ,profesor03()));
        //When
        List<Persona> expected = (List<Persona>) profesorRepository.buscarPorApellido("Reyes");
        //Then
        assertThat(expected.size()==2).isTrue();
    }
}