package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Empleado;
import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import dev.rmpedro.apirest.models.entities.Profesor;
import dev.rmpedro.apirest.repositories.EmpleadoRepository;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO{

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados")PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

    @Override
    public Iterable<Empleado> buscarTodosEmpleado() {
        return ((EmpleadoRepository)repository).buscarTodosEmpleado();
    }

    @Override
    public Optional<Empleado> buscarEmpleadoPorId(Integer id) {
        return((EmpleadoRepository)repository).buscarEmpleadoPorId(id);
    }

    @Override
    public Persona actualizarEmpleado(Persona empleadoEncontrado, Empleado empleado) {
        Persona empleadoActualizado = null;
        empleadoEncontrado.setNombre(empleado.getNombre());
        empleadoEncontrado.setApellido(empleado.getApellido());
        empleadoEncontrado.setDireccion(empleado.getDireccion());
        ((Empleado)empleadoEncontrado).setSueldo(empleado.getSueldo());
        empleadoActualizado=repository.save(empleadoEncontrado);
        return empleadoActualizado;
    }
}
