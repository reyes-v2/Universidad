package dev.rmpedro.apirest.entities;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable{

	
	private String calle;
	private String numero;
	private String codigoPostal;
	private String departamento;
	private String piso;
	private String localidad;
	@Serial
	private static final long serialVersionUID = -6409397347896412403L;
	
	

}
