package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.CarreraDTO;
import dev.rmpedro.apirest.models.dto.ProfesorDTO;
import dev.rmpedro.apirest.models.entities.Carrera;
import dev.rmpedro.apirest.models.entities.Profesor;

import java.util.HashSet;
import java.util.Set;

public class ProfesorMapper {
    public static ProfesorDTO mapperProfesor(Profesor profesor){
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setId(profesor.getId());
        profesorDTO.setNombre(profesor.getNombre());
        profesorDTO.setApellido(profesor.getApellido());
        Set<CarreraDTO> carrerasDTO = new HashSet<>();
        for(Carrera carrera : profesor.getCarreras()){
            carrerasDTO.add(CarreraMapper.mapperCarrera(carrera));
        }
        profesorDTO.setCarreraDTO(carrerasDTO);
        return profesorDTO;
    }
}
