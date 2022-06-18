package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.datos.EmpleadoDatosDummy;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpleadoRepositoryTest {
    @Autowired
    private EmpleadoRepository repository;


    @BeforeEach
    void setUp() {
        repository.save(EmpleadoDatosDummy.empleado01());
        repository.save(EmpleadoDatosDummy.empleado02());
        repository.save(EmpleadoDatosDummy.empleado03());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findEmpleadoByTipoEmpleado() {
        List<Empleado> empleadosMantenimiento = (List<Empleado>) repository.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
        assertThat(empleadosMantenimiento.size()).isEqualTo(3);
    }
}