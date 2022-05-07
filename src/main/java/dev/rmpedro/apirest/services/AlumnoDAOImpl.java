package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Persona;
import dev.rmpedro.apirest.repositories.AlumnoRepository;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO{
    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }


    @Override
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String carrera) {

        return ((AlumnoRepository) repository).buscarAlumnoPorNombreCarrera(carrera);
    }
}
