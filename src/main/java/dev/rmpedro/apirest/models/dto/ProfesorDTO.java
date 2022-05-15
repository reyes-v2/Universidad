package dev.rmpedro.apirest.models.dto;


import dev.rmpedro.apirest.models.entities.Carrera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO {
    private Integer id;
    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    @Size(min = 8, max = 80)
    private String nombre;
    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    @Size(min = 8, max = 80)
    private String apellido;
    private Set<CarreraDTO> carreraDTO;
}
