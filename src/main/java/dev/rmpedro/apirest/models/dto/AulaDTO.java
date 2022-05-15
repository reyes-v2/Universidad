package dev.rmpedro.apirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AulaDTO {
    private Integer id;
    @Positive
    private Integer numeroAula;
    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    private String medidas;

}
