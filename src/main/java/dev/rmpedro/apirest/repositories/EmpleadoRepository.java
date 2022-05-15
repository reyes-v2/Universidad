package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import dev.rmpedro.apirest.models.entities.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository{
    @Query("Select p from Empleado p where p.tipoEmpleado=?1")
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
    @Query("select e from Empleado e")
    Iterable<Empleado>  buscarTodosEmpleado();
    @Query("select e from Empleado e where e.id=?1")
    Optional<Empleado> buscarEmpleadoPorId(Integer id);
}
