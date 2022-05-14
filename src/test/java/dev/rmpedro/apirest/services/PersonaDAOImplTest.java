package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.datos.ProfesorDatosDummy;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class PersonaDAOImplTest {

    PersonaRepository personaRepository;
    PersonaDAO personaDAO;

    @BeforeEach
    void setUp() {
        personaRepository = mock(PersonaRepository.class);
        personaDAO = new PersonaDAOImpl(personaRepository);
    }

    @Test
    void buscarPorNombreApeliido() {
        when(personaRepository.buscarPorNombreApeliido("Pedro","Reyes"))
                .thenReturn(Optional.of(ProfesorDatosDummy.profesor01()));

        Optional<Persona> persona = personaDAO.buscarPorNombreApeliido("Pedro","Reyes");

        assertTrue(persona.isPresent());
        assertThat(persona.get()).isEqualTo(ProfesorDatosDummy.profesor01());
        verify(personaRepository).buscarPorNombreApeliido("Pedro","Reyes");



    }

    @Test
    void buscarPorDni() {
        when(personaRepository.buscarPorDni("23323232"))
                .thenReturn(Optional.of(ProfesorDatosDummy.profesor01()));

        Optional<Persona> persona = personaDAO.buscarPorDni("23323232");

        assertTrue(persona.isPresent());
        assertThat(persona.get()).isEqualTo(ProfesorDatosDummy.profesor01());
        verify(personaRepository).buscarPorDni("23323232");

    }

    @Test
    void buscarPorApellido() {
        when(personaRepository.buscarPorApellido("Reyes")).thenReturn(List.of(ProfesorDatosDummy.profesor01(),ProfesorDatosDummy.profesor02()));
        List<Persona> personas = (List<Persona>) personaDAO.buscarPorApellido("Reyes");
        assertThat(personas.size()).isEqualTo(2);
        verify(personaRepository).buscarPorApellido("Reyes");

    }
}