package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.AulaDTO;
import dev.rmpedro.apirest.models.entities.Aula;

public class AulaMapper {
    public static AulaDTO mapperAula(Aula aula){
        AulaDTO aulaDTO = new AulaDTO();
        aulaDTO.setId(aula.getId());
        aulaDTO.setNumeroAula(aula.getNumeroAula());
        aulaDTO.setMedidas(aula.getMedidas());

        return aulaDTO;


    }
}
