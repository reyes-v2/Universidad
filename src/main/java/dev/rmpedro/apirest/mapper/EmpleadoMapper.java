package dev.rmpedro.apirest.mapper;

import dev.rmpedro.apirest.models.dto.EmpleadoDTO;
import dev.rmpedro.apirest.models.entities.Empleado;

public class EmpleadoMapper {
    public static EmpleadoDTO mapperEmpleado(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setTipoEmpleado(empleado.getTipoEmpleado());

        return empleadoDTO;

    }
}
