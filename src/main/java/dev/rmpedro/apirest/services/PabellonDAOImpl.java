package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Pabellon;
import dev.rmpedro.apirest.repositories.PabellonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    @Override
    public Pabellon actualizarPabellon(Pabellon pabellonEncontrado, Pabellon pabellon) {
        Pabellon pabellonActualizado = null;
        pabellonEncontrado.setNombre(pabellon.getNombre());
        pabellonEncontrado.setAulas(pabellon.getAulas());
        pabellonEncontrado.setDireccion(pabellon.getDireccion());
        pabellonActualizado=repository.save(pabellonEncontrado);

        return pabellonActualizado;
    }

    @Override
    public Pabellon findById(Integer id) {
        Optional<Pabellon> pabellonEncontrado = repository.findById(id);
        if(!pabellonEncontrado.isPresent()){
            throw new NotFoundException("No existe el pabellon con el id");
        }
        return pabellonEncontrado.get();
    }


}
