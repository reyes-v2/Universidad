package dev.rmpedro.apirest.models.entities;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
//@NoArgsConstructor
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

	public Direccion(String calle, String numero, String codigoPostal, String departamento, String piso, String localidad) {
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.departamento = departamento;
		this.piso = piso;
		this.localidad = localidad;
	}

	public Direccion() {
	}
}
