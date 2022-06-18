package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.CarreraDatosDummy;
import dev.rmpedro.apirest.datos.ProfesorDatosDummy;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProfesorRepositoryTest {

    @Autowired
    private ProfesorRepository repository;
    @Autowired
    private CarreraRepository carreraRepository;

    @BeforeEach
    void setUp() {
        repository.save(ProfesorDatosDummy.profesor01());
        repository.save(ProfesorDatosDummy.profesor02());
        repository.save(ProfesorDatosDummy.profesor03());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }


    @Test
    void findProfesoresByCarrera() {
        Optional<Persona> profesor = repository.findById(1);

        if (profesor.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el profesor con id " + 1);
        }

        Profesor profesorEncontrado = (Profesor) profesor.get();
        Set<Carrera> carreraSet = new HashSet<>();

        Carrera carreraGuardada = carreraRepository.save(CarreraDatosDummy.carrera01());
        carreraSet.add(carreraGuardada);
        profesorEncontrado.setCarreras(carreraSet);
        repository.save(profesorEncontrado);

        List<Profesor> profesoresPorCarrera = (List<Profesor>) repository.findProfesoresByCarrera("Ingenieria en Sistemas");
        assertThat(profesoresPorCarrera.size()).isGreaterThan(0);


    }
}
