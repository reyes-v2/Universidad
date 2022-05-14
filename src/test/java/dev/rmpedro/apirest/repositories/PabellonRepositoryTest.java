package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.PabellonDatosDummy;
import dev.rmpedro.apirest.models.entities.Pabellon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PabellonRepositoryTest {

    @Autowired
    private PabellonRepository pabellonRepository;


    @BeforeEach
    void setUp() {
        pabellonRepository.save(PabellonDatosDummy.pabellon01());
        pabellonRepository.save(PabellonDatosDummy.pabellon02());
        pabellonRepository.save(PabellonDatosDummy.pabellon03());

    }

    @AfterEach
    void tearDown() {
        pabellonRepository.deleteAll();

    }

    @Test
    void findPabellonsByDireccionLocalidad() {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonRepository.findPabellonsByDireccionLocalidad("Tehuacán");
        assertThat(pabellones.size()).isEqualTo(3);
    }

    @Test
    void findPabellonByNombreEquals() {
        List<Pabellon> pabellones = (List<Pabellon>) pabellonRepository.findPabellonByNombreEquals("Elon Musk");
        assertThat(pabellones.size()).isEqualTo(1);
    }
}