package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.CarreraDatosDummy;
import dev.rmpedro.apirest.entities.Carrera;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class CarreraRepositoryTest {

    @Autowired
    private CarreraRepository carreraRepository;


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
    @DisplayName("Buscar nombre por cantidad de anios")
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

}
