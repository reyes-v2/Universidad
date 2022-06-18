package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.datos.AulasDatosDummy;
import dev.rmpedro.apirest.enums.Pizarron;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.repositories.AulaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AulaDAOImplTest {

    AulaDAO aulaDAO;
    AulaRepository aulaRepository;

    @BeforeEach
    void setUp() {
        aulaRepository = mock(AulaRepository.class);
        aulaDAO = new AulaDAOImpl(aulaRepository);
    }

    @Test
    void findAulasByTipoPizarron() {
        when(aulaRepository.findAulaByPizarronEquals(Pizarron.PIZARRA_TIZA))
                .thenReturn(List.of(AulasDatosDummy.aula03()));

        List<Aula> aulasTiza = (List<Aula>) aulaDAO.findAulaByPizarronEquals("tiza");

        assertThat(aulasTiza.size()).isGreaterThan(0);
        assertThat(aulasTiza.get(0)).isEqualTo(AulasDatosDummy.aula03());

        verify(aulaRepository).findAulaByPizarronEquals(Pizarron.PIZARRA_TIZA);
    }

    @Test
    void findAulasByPabellonNombre() {
        when(aulaRepository.findAulaByPabellonNombre("Elon Musk"))
                .thenReturn(List.of(AulasDatosDummy.aula04()));

        List<Aula> aulaPabellon = (List<Aula>) aulaDAO.findAulaByPabellonNombre("Elon Musk");
        assertThat(aulaPabellon.get(0)).isEqualTo(AulasDatosDummy.aula04());

        verify(aulaRepository).findAulaByPabellonNombre("Elon Musk");
    }

    @Test
    void findAulaByNumeroAula() {
        when(aulaRepository.findAulaByNumeroAulaEquals(1))
                .thenReturn(List.of(AulasDatosDummy.aula01()));

        List<Aula> aulaFound = (List<Aula>) aulaDAO.findAulaByNumeroAulaEquals(1);

        assertThat(aulaFound.get(0)).isEqualTo(AulasDatosDummy.aula01());
        verify(aulaRepository).findAulaByNumeroAulaEquals(1);
    }
}