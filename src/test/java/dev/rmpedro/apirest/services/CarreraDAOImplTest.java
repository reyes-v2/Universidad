package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.datos.CarreraDatosDummy;
import dev.rmpedro.apirest.datos.ProfesorDatosDummy;
import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.entities.Profesor;
import dev.rmpedro.apirest.repositories.CarreraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static dev.rmpedro.apirest.datos.CarreraDatosDummy.*;
import static dev.rmpedro.apirest.datos.ProfesorDatosDummy.profesor01;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarreraDAOImplTest {

    CarreraDAO carreraDAO;
    CarreraRepository carreraRepository;
    @BeforeEach
    void setUp(){
        carreraRepository = mock(CarreraRepository.class);
        carreraDAO = new CarreraDAOImpl(carreraRepository);
    }
    @Test
    void findByCantidadAnios() {
        //Given
        Integer anio = 4;
        when(carreraRepository.findByCantidadAnios(anio)).thenReturn(Arrays.asList(carrera03(),carrera04()));
        //when
        List<Carrera> expected = (List<Carrera>) carreraDAO.findByCantidadAnios(anio);
        //then
        assertThat(expected.get(0)).isEqualTo(carrera03());
        assertThat(expected.get(1)).isEqualTo(carrera04());
        verify(carreraRepository).findByCantidadAnios(anio);


    }

    @Test
    void findCarrerasByNombreContainsIgnoreCase() {
        //Given
        String nombre = "Ingenieria";
        when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre)).thenReturn(Arrays.asList(carrera01(), carrera04()));
        //When
        List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase(nombre);
        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera04());

        verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);



    }

    @Test
    void findCarrerasByCantidadAniosAfter() {
        //Given
        Integer anio = 3;
        when(carreraRepository.findCarrerasByCantidadAniosAfter(anio)).thenReturn(Arrays.asList(carrera01(),carrera02(),carrera03(),carrera04()));
        //When
        List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(anio);
        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera02());
        assertThat(expected.get(2)).isEqualTo(carrera03());
        assertThat(expected.get(3)).isEqualTo(carrera04());
        verify(carreraRepository).findCarrerasByCantidadAniosAfter(anio);

    }

    @Test
    void buscarCarrerasPorProfesorNombreYApellido() {
        String nombreProfesor = "Pedro";
        String apellidoProfesor = "Reyes";
        when(carreraRepository.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor))
                .thenReturn(List.of(CarreraDatosDummy.carrera04()));

        List<Carrera> carreraList = (List<Carrera>) carreraRepository.buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);

        assertEquals(carreraList.get(0), CarreraDatosDummy.carrera04());

        verify(carreraRepository).buscarCarrerasPorProfesorNombreYApellido(nombreProfesor, apellidoProfesor);

    }
}