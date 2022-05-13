package dev.rmpedro.apirest.models.dto;


import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarreraDTO {
    private Integer id;
    @NotNull(message = "No puede ser null")
    @NotEmpty(message = "No puede ser vacio")
    @Size(min = 8, max = 80)
    private String nombre;
    @Positive(message = "Debe ser mayor que 0")
    private Integer cantidadMaterias;
    @Positive(message = "Debe ser mayor que 0")
    private Integer cantidadAnios;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCantidadMaterias() {
		return cantidadMaterias;
	}
	public void setCantidadMaterias(Integer cantidadMaterias) {
		this.cantidadMaterias = cantidadMaterias;
	}
	public Integer getCantidadAnios() {
		return cantidadAnios;
	}
	public void setCantidadAnios(Integer cantidadAnios) {
		this.cantidadAnios = cantidadAnios;
	}
    
    



}
