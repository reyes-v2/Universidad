package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.PabellonDTO;
import dev.rmpedro.apirest.models.entities.Pabellon;

public class PabellonMapper {
    public static PabellonDTO mapperPabellon(Pabellon pabellon){
        PabellonDTO pabellonDTO = new PabellonDTO();
        pabellonDTO.setId(pabellon.getId());
        pabellonDTO.setNombre(pabellon.getNombre());
        return pabellonDTO;

    }
}
