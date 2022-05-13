package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.CarreraDatosDummy;
import dev.rmpedro.apirest.datos.ProfesorDatosDummy;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Profesor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class CarreraRepositoryTest {

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private ProfesorRepository profesorRepository;


    @BeforeEach
    void setUp() { //Given
        carreraRepository.save(CarreraDatosDummy.carrera01());
        carreraRepository.save(CarreraDatosDummy.carrera02());
        carreraRepository.save(CarreraDatosDummy.carrera03());
        carreraRepository.save(CarreraDatosDummy.carrera04());

    }

    @AfterEach
    void tearDown() {
        carreraRepository.deleteAll();
    }


    @Test
    @DisplayName("Buscar carrera por cantidad de anios")
    void findByCantidadAnios() {

        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosAfter(4);

        //Then
        assertThat(expected.size() == 2).isTrue();


    }

    @Test
    @DisplayName("Buscar carreras por nombre")
    void findCarrerasByNombreContainsIgnoreCase() {

        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("Sistemas");


        //Then
        assertThat(expected.size() == 1).isTrue();

    }

    @Test
    @DisplayName("Buscar carreras mayores a N cantidad de anios")
    void findCarrerasByCantidadAniosAfter() {


        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosAfter(3);


        //Then
        assertThat(expected.size() == 4).isTrue();


    }
    @Test
    void buscarCarrerasPorProfesorYApellido() {
        Optional<Carrera> carreraFound = carreraRepository.findById(1);

        Profesor profesorGuardado = (Profesor) profesorRepository.save(ProfesorDatosDummy.profesor01());

        if (carreraFound.isPresent()) {
            Carrera carrera = carreraFound.get();
            Set<Carrera> carreraSet = new HashSet<>();
            carreraSet.add(carrera);
            profesorGuardado.setCarreras(carreraSet);
            profesorRepository.save(profesorGuardado);
        }

        List<Carrera> carreras = (List<Carrera>) carreraRepository.buscarCarrerasPorProfesorNombreYApellido("Pedro", "Reyes");
        assertEquals(carreras.size(), 1);
    }

}
