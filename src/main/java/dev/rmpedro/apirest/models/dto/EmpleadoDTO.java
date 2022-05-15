package dev.rmpedro.apirest.models.dto;

import dev.rmpedro.apirest.enums.TipoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    private Integer id;
    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    @Size(min = 8, max = 80)
    private String nombre;
    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    @Size(min = 8, max = 80)
    private String apellido;
    private TipoEmpleado tipoEmpleado;
}
