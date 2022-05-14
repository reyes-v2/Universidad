package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.datos.EmpleadoDatosDummy;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmpleadoDAOImplTest {

    EmpleadoDAO empleadoDAO;
    EmpleadoRepository empleadoRepository;

    @BeforeEach
    void setUp() {
        empleadoRepository=mock(EmpleadoRepository.class);
        empleadoDAO = new EmpleadoDAOImpl(empleadoRepository);
    }

    @Test
    void findEmpleadoByTipoEmpleado() {
        when(empleadoRepository.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO))
                .thenReturn(List.of(EmpleadoDatosDummy.empleado01(),EmpleadoDatosDummy.empleado02(),EmpleadoDatosDummy.empleado03()));
        List<Persona> empleados = (List<Persona>) empleadoDAO.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);

        assertThat(empleados.size()).isEqualTo(3);
        verify(empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
    }
}