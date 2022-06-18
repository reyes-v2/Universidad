package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Pabellon;
import dev.rmpedro.apirest.repositories.PabellonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO{


    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Pabellon> findPabellonsByDireccionLocalidad(String direccionLocalidad) {
        List<Pabellon> pabellonList = (List<Pabellon>) repository.findPabellonsByDireccionLocalidad(direccionLocalidad);
        if(pabellonList.isEmpty()){
            throw new NotFoundException("No hay pabellones que mostrar");
        }
        return pabellonList;
    }

    @Override
    public Iterable<Pabellon> findPabellonByNombreEquals(String nombrePabellon) {
        List<Pabellon>pabellons = (List<Pabellon>) repository.findPabellonByNombreEquals(nombrePabellon);
        if(pabellons.isEmpty()){
            throw new NotFoundException("No hay pabellones que mostrar con el nombre " + nombrePabellon);

        }
        return pabellons;
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
