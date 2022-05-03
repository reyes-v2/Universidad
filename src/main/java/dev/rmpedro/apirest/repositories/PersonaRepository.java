package dev.rmpedro.apirest.repositories;

import dev.rmpedro.apirest.entities.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository <Persona,Integer>{

}
