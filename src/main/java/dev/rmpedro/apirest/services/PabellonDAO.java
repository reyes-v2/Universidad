package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{
    Iterable<Pabellon> findPabellonsByDireccionLocalidad(String direccion_localidad);
    Iterable<Pabellon> findPabellonByNombreEquals(String nombrePabellon);



}
