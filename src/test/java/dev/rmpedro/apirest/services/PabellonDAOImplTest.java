package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.datos.PabellonDatosDummy;
import dev.rmpedro.apirest.models.entities.Pabellon;
import dev.rmpedro.apirest.repositories.PabellonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PabellonDAOImplTest {

    PabellonDAO pabellonDAO;
    PabellonRepository pabellonRepository;

    @BeforeEach
    void setUp() {
            pabellonRepository = mock(PabellonRepository.class);
            pabellonDAO = new PabellonDAOImpl(pabellonRepository);
    }

    @Test
    void findPabellonsByDireccionLocalidad() {
        when(pabellonRepository.findPabellonsByDireccionLocalidad("Tehuacan"))
                .thenReturn(List.of(PabellonDatosDummy.pabellon01(),PabellonDatosDummy.pabellon02(),
                        PabellonDatosDummy.pabellon03()));
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonsByDireccionLocalidad("Tehuacan");
        assertThat(pabellones.size()).isEqualTo(3);
        verify(pabellonRepository).findPabellonsByDireccionLocalidad("Tehuacan");
    }

    @Test
    void findPabellonByNombreEquals() {
        when(pabellonRepository.findPabellonByNombreEquals("Elon Musk"))
                .thenReturn(List.of(PabellonDatosDummy.pabellon01()));
        List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonByNombreEquals("Elon Musk");
        assertThat(pabellones.size()).isGreaterThan(0);
        assertThat(pabellones.get(0)).isEqualTo(PabellonDatosDummy.pabellon01());
        verify(pabellonRepository).findPabellonByNombreEquals("Elon Musk");
    }
}