package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Persona;
import dev.rmpedro.apirest.enums.TipoEmpleado;


public interface EmpleadoDAO extends PersonaDAO{
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
