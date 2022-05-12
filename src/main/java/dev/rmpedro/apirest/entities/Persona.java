package dev.rmpedro.apirest.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import lombok.*;
import org.hibernate.mapping.Join;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="personas")
//@Table(name="personas",schema = "universidad")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class  Persona implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", nullable = false, length = 60)
	private String nombre;
	@Column(name = "apellido", nullable = false, length = 60)
	private String apellido;
	@Column(name = "dni",unique = true, nullable = false, length = 10)
	private String dni;
	
	private Date fechaCreacion;
	private Date fechaModificacion;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name="codigoPostal",column = @Column(name="codigo_postal")),
			@AttributeOverride(name="departamento",column = @Column(name="departamento"))


	})
	private Direccion direccion;
	private static final long serialVersionUID = 1825359541597205358L;

	
	
	
	   public Persona(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
	        this.id = id;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.dni = dni;
	        this.direccion = direccion;
	    }
	

	@Override
	public int hashCode() {
		return Objects.hash(dni, id);
	}


	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion= new Date();

	}

	@PreUpdate
	private void antesModificar() {
		this.fechaModificacion= new Date();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(id, other.id);
	}
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Persona{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", dni='").append(dni).append('\'');
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", fechaModificacion=").append(fechaModificacion);
        sb.append(", direccion=").append(direccion);
        sb.append('}');
        return sb.toString();
    }








	

}
