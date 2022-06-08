package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.entities.Carrera;
import lombok.Getter;
import lombok.Setter;

public class CarreraMapper {

    public static CarreraDTO mapperCarrera(Carrera carrera){
        CarreraDTO carreraDTO = new CarreraDTO();
        if(carrera==null){
            carreraDTO.setId(null);
            carreraDTO.setNombre(null);
            carreraDTO.setCantidadAnios(null);
            carreraDTO.setCantidadMaterias(null);
        }
        carreraDTO.setId(carrera.getId());
        carreraDTO.setNombre(carrera.getNombre());
        carreraDTO.setCantidadAnios(carrera.getCantidadAnios());
        carreraDTO.setCantidadMaterias(carrera.getCantidadMaterias());

        return carreraDTO;
    }
}
