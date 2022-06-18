package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.datos.ProfesorDatosDummy;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.models.entities.Profesor;
import dev.rmpedro.apirest.repositories.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class ProfesorDAOImplTest {

    ProfesorRepository profesorRepository;
    ProfesorDAO profesorDAO;



    @BeforeEach
    void setUp() {
        profesorRepository = mock(ProfesorRepository.class);
        profesorDAO = new ProfesorDAOImpl(profesorRepository);
    }

    @Test
    void findProfesoresByCarrera() {
        when(profesorRepository.findProfesoresByCarrera("Licenciatura en Agronegocios")).thenReturn(List.of(ProfesorDatosDummy.profesor02()));
        List<Profesor> profesores = (List<Profesor>) profesorDAO.findProfesoresByCarrera("Licenciatura en Agronegocios");
        assertThat(profesores.size()).isEqualTo(1);
        assertThat(profesores.get(0)).isEqualTo(ProfesorDatosDummy.profesor02());
        verify(profesorRepository).findProfesoresByCarrera("Licenciatura en Agronegocios");

    }
}