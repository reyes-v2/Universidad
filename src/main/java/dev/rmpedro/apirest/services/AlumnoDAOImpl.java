package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Alumno;
import dev.rmpedro.apirest.entities.Persona;
import dev.rmpedro.apirest.repositories.AlummnoRepository;
import dev.rmpedro.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AlumnoDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements AlumnoDAO{
    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }





}
