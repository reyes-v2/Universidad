package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.entities.Aula;
import dev.rmpedro.apirest.enums.Pizarron;
import org.springframework.data.repository.CrudRepository;

public interface AulaRepository extends CrudRepository<Aula,Integer> {
    Iterable<Aula> findAulaByPizarronEquals(Pizarron pizarron);
    Iterable<Aula> findAulaByPabellonNombre(String nombrePabellon);
    Iterable<Aula> findAulaByNumeroAulaEquals(Integer numeroAula);

}
