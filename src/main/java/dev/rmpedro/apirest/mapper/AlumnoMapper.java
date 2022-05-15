package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.AlumnoDTO;
import dev.rmpedro.apirest.models.entities.Alumno;

public class AlumnoMapper {
    public static AlumnoDTO mapperAlumno(Alumno alumno){
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setId(alumno.getId());
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellido(alumno.getApellido());
        alumnoDTO.setCarrera(CarreraMapper.mapperCarrera(alumno.getCarrera()));

        return alumnoDTO;

    }



}
