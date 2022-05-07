package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Persona;
import dev.rmpedro.apirest.enums.TipoEmpleado;
import dev.rmpedro.apirest.repositories.AlumnoRepository;
import dev.rmpedro.apirest.repositories.EmpleadoRepository;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import dev.rmpedro.apirest.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
}
