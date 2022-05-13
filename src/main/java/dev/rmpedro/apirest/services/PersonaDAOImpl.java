package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Persona;
import dev.rmpedro.apirest.repositories.PersonaRepository;

import java.util.Optional;

public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO {

    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Persona> buscarPorNombreApeliido(String nombre, String apellido) {
        return repository.buscarPorNombreApeliido(nombre,apellido);
    }

    @Override
    public Optional<Persona> buscarPorDni(String dni) {
        return repository.buscarPorDni(dni);
    }

    @Override
    public Iterable<Persona> buscarPorApellido(String apellido) {
        return buscarPorApellido(apellido);
    }
}
