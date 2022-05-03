package dev.rmpedro.apirest.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "carreras", schema = "universidad")
public class Carrera implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre",nullable = false, unique = true, length = 80)
	private String nombre;

	@Column(name = "cantidad_materias")
	private Integer cantidadMaterias;
	@Column(name = "cantidad_anios")
	private Integer cantidadAnios;
	@Column(name = "fecha_creacion",nullable = false)
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	  @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
	    private Set<Alumno> alumnos;

	    @ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY)
	    private Set<Profesor> profesores;
	
	@Serial
	private static final long serialVersionUID = -647494856265421437L;


	public Carrera(Integer id, String nombre, Integer cantidadMaterias, Integer cantidadAnios) {
		
		this.id = id;
		this.nombre = nombre;
		this.cantidadMaterias = cantidadMaterias;
		this.cantidadAnios = cantidadAnios;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion= new Date();
		
	}
	
	@PreUpdate
	private void antesModificar() {
		this.fechaCreacion= new Date();
		
	}
	   @Override
	    public String toString() {
	        final StringBuilder sb = new StringBuilder("Carrera{");
	        sb.append("id=").append(id);
	        sb.append(", nombre='").append(nombre).append('\'');
	        sb.append(", cantidadMaterias=").append(cantidadMaterias);
	        sb.append(", cantidadAnios=").append(cantidadAnios);
	        sb.append(", fechaCreacion=").append(fechaCreacion);
	        sb.append(", fechaModificacion=").append(fechaModificacion);
	        sb.append('}');
	        return sb.toString();
	    }
	
	
	
	
	
	
	

}
