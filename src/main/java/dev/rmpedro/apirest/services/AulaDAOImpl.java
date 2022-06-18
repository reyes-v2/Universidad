package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.exceptions.NotFoundException;
import dev.rmpedro.apirest.models.entities.Aula;
import dev.rmpedro.apirest.enums.Pizarron;
import dev.rmpedro.apirest.models.entities.Pabellon;
import dev.rmpedro.apirest.repositories.AulaRepository;
import org.springframework.stereotype.Service;

import java.nio.channels.NoConnectionPendingException;
import java.util.Date;
import java.util.List;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{


	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	public Iterable<Aula> findAulaByPizarronEquals(String pizarron) {
		List<Aula> aulas = (List<Aula>) repository.findAulaByPizarronEquals(Pizarron.valueOf(pizarron.toUpperCase()));
		if(aulas.isEmpty()){
			throw new NotFoundException("No existen aulas con el pizarron " + pizarron);
		}
		return aulas;
	}

	@Override
	public Iterable<Aula> findAulaByPabellonNombre(String nombrePabellon) {
		Iterable<Aula> pabellons = repository.findAulaByPabellonNombre(nombrePabellon);
		if(((List<Aula>)pabellons).isEmpty()){
			throw new NotFoundException("No existen aulas en el pabellon " + nombrePabellon);

		}
		return pabellons;

	}

	@Override
	public Iterable<Aula> findAulaByNumeroAulaEquals(Integer numeroAula) {
		List<Aula> aulas = (List<Aula>) repository.findAulaByNumeroAulaEquals(numeroAula);
		if(aulas.isEmpty()){
			throw new NotFoundException("No existen aulas con el numero " + numeroAula);
		}
		return aulas;
	}

	@Override
	public Aula actualizar(Aula aulaEncontrada, Aula aulaNueva) {
		Aula aulaActualizada = null;
		aulaEncontrada.setNumeroAula(aulaNueva.getNumeroAula());
		aulaEncontrada.setCantidadPupitres(aulaNueva.getCantidadPupitres());
		aulaActualizada = aulaEncontrada;
		aulaActualizada.setFechaModificacion(new Date());



		return aulaActualizada;


	}


}
