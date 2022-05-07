package dev.rmpedro.apirest.services;

import dev.rmpedro.apirest.entities.Aula;
import dev.rmpedro.apirest.entities.Carrera;
import dev.rmpedro.apirest.enums.Pizarron;

public interface AulaDAO extends GenericoDAO<Aula>{

    Iterable<Aula> findAulaByPizarronEquals(Pizarron pizarron);
    Iterable<Aula> findAulaByPabellonNombre(String nombrePabellon);
    Iterable<Aula> findAulaByNumeroAulaEquals(Integer numeroAula);



}
