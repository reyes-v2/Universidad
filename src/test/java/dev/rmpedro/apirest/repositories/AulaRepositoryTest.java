package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.AulasDatosDummy;
import dev.rmpedro.apirest.datos.PabellonDatosDummy;
import dev.rmpedro.apirest.enums.Pizarron;
import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.models.entities.Pabellon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AulaRepositoryTest {

    @Autowired
    private AulaRepository aulaRepository;
    @Autowired
    private PabellonRepository pabellonRepository;

    @BeforeEach
    void setUp() {
        aulaRepository.save(AulasDatosDummy.aula01());
        aulaRepository.save(AulasDatosDummy.aula02());
        aulaRepository.save(AulasDatosDummy.aula03());
        aulaRepository.save(AulasDatosDummy.aula04());


    }

    @AfterEach
    void tearDown() {
        aulaRepository.deleteAll();

    }

    @Test
    void findAulaByPizarronEquals() {
        List<Aula> aulas = (List<Aula>) aulaRepository.findAulaByPizarronEquals(Pizarron.PIZARRA_BLANCA);
        if(aulas.isEmpty()){
                throw new NotFoundException("No hay aulas con esa pizarra");

        }
        assertThat(aulas.size()).isEqualTo(3);



    }

    @Test
    void findAulaByPabellonNombre() {
        Pabellon pabellon=pabellonRepository.save(PabellonDatosDummy.pabellon01());
        List<Aula> aulas = (List<Aula>) aulaRepository.findAulaByPabellonNombre(pabellon.getNombre());
        assertThat(aulas.size()).isEqualTo(1);
    }

    @Test
    void findAulaByNumeroAulaEquals() {
        List<Aula> aulas = (List<Aula>) aulaRepository.findAulaByNumeroAulaEquals(1);
        assertThat(aulas.size()).isEqualTo(1);


    }
}