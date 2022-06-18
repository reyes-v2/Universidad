package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{
    Iterable<Pabellon> findPabellonsByDireccionLocalidad(String direccionLocalidad);
    Iterable<Pabellon> findPabellonByNombreEquals(String nombrePabellon);
    Pabellon actualizarPabellon(Pabellon pabellonEncontrado, Pabellon pabellon);
    Pabellon findById(Integer id);



}
