package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.models.entities.Pabellon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon,Integer> {

    Iterable<Pabellon> findPabellonsByDireccionLocalidad(String direccionLocalidad);
    Iterable<Pabellon> findPabellonByNombreEquals(String nombrePabellon);
}
