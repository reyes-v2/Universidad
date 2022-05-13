package dev.rmpedro.apirest.repositories;


import dev.rmpedro.apirest.datos.AlumnoDatosDummy;
import dev.rmpedro.apirest.datos.CarreraDatosDummy;
import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlumnoRepositoryTest {

    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private CarreraRepository carreraRepository;

    @BeforeEach
    void setUp() {
        repository.save(AlumnoDatosDummy.alumno01());
        repository.save(AlumnoDatosDummy.alumno02());
        repository.save(AlumnoDatosDummy.alumno03());
        carreraRepository.save(CarreraDatosDummy.carrera01());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void buscarAlumnosPorNombreCarrera() {
        Optional<Carrera> carreraFound = carreraRepository.findById(1);

        if (carreraFound.isEmpty())
            throw new EntityNotFoundException("No se encontró una carrera con id " + 1);

        Optional<Persona> alumnoFound = repository.findById(1);

        if (alumnoFound.isEmpty())
            throw new EntityNotFoundException("No se encontró un alumno con id " + 1);

        Alumno alumno = (Alumno) alumnoFound.get();

        alumno.setCarrera(carreraFound.get());
        repository.save(alumno);
        List<Persona> alumnosSistemas = (List<Persona>) repository.buscarAlumnoPorNombreCarrera("Ingenieria en Sistemas");

        assertThat(alumnosSistemas.size()).isGreaterThan(0);

        Alumno alumnoSistemas = (Alumno) alumnosSistemas.get(0);
        assertThat(alumnoSistemas).isEqualTo(alumno);
    }
}