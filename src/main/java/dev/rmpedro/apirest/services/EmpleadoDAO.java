package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.enums.TipoEmpleado;

import java.util.Optional;


public interface EmpleadoDAO extends PersonaDAO{
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
    Iterable <Empleado> buscarTodosEmpleado();
    Optional<Empleado> buscarEmpleadoPorId(Integer id);
    Persona actualizarEmpleado(Persona empleadoEncontrado,Empleado empleado);
}
