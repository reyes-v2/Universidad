package dev.rmpedro.apirest.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Getter
@Setter
//@Table(name="alumnos",schema="universidad")
@Table(name="alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona{

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "carrera_id", foreignKey = @ForeignKey(name = "FK_CARRERA_ID"))
	@JsonIgnoreProperties({"hibernateLazyInitializer","alumnos"})
	private Carrera carrera;


	

	public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
		super(id, nombre, apellido, dni, direccion);
	}
	@Serial
	private static final long serialVersionUID = 97655354333990378L;

	public Alumno() {

	}

	@Override
	    public String toString() {
	        final StringBuilder sb = new StringBuilder(super.toString());
	        sb.append("carrera=").append(carrera);
	        sb.append('}');
	        return sb.toString();
	    }
	
	
	
	

}
