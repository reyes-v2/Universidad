package com.example.demo.repositories;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.repositories.CarreraRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

import static com.example.demo.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class CarreraRepositoryTest {
    private final CarreraRepository carreraRepository;

    public CarreraRepositoryTest(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }


    @Test
    @Disabled
    void findByCantidadAnios(){


    }

    @Test
    @DisplayName("Buscar carreras por nombre")
    void findCarrerasByNombreContainsIgnoreCase(){
        //Given
        carreraRepository.save(carrera01());
        carreraRepository.save(carrera02());
        carreraRepository.save(carrera03());
        carreraRepository.save(carrera04());
        //When
        List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("Sistemas");


        //Then
        assertThat(expected.size()==1).isTrue();

    }
    @Test
    @Disabled
    void findCarrerasByCantidadAniosAfter(){

    }
    @Test
    @Disabled
   void buscarCarrerasPorProfesorNombreYApellido(){

    }
}
