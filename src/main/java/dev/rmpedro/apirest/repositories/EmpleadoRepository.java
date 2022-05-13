package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository{
    @Query("Select p from Empleado p where p.tipoEmpleado=?1")
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
