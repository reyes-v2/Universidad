package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.entities.Carrera;
import lombok.Getter;
import lombok.Setter;

public class CarreraMapper {

    public static CarreraDTO mapCarrera(Carrera carrera){
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setId(carrera.getId());
        carreraDTO.setNombre(carrera.getNombre());
        carreraDTO.setCantidadAnios(carrera.getCantidadAnios());
        carreraDTO.setCantidadMaterias(carrera.getCantidadMaterias());

        return carreraDTO;
    }
}
