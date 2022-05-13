package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Persona;

import java.util.Optional;

public interface PersonaDAO extends GenericoDAO<Persona>{

    public Optional<Persona> buscarPorNombreApeliido(String nombre, String apellido);
    public Optional<Persona> buscarPorDni(String dni);
    public Iterable<Persona> buscarPorApellido(String apellido);


}
