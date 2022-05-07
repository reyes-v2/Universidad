package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Pabellon;
import dev.rmpedro.apirest.repositories.PabellonRepository;
import org.springframework.stereotype.Service;

import java.io.Serial;


@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO{


    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Pabellon> findPabellonsByDireccionLocalidad(String direccion_localidad) {
        return repository.findPabellonsByDireccionLocalidad(direccion_localidad);
    }

    @Override
    public Iterable<Pabellon> findPabellonByNombreEquals(String nombrePabellon) {
        return repository.findPabellonByNombreEquals(nombrePabellon);
    }


}
