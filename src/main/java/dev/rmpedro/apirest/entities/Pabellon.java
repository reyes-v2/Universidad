package dev.rmpedro.apirest.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
//@Table(name = "pabellones", schema = "universidad")
@Table(name = "pabellones")
public class Pabellon implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "metros_cuadrados")
	private Double metrosCuadrados;
	private String nombre;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="codigoPostal",column = @Column(name="codigo_postal")),
		@AttributeOverride(name="departamento",column = @Column(name="departamento"))

		
	})
	private Direccion direccion;
	
	@OneToMany(mappedBy = "pabellon",fetch = FetchType.LAZY)
	private Set<Aula> aulas;

	
	
	private static final long serialVersionUID = 4863874196431183367L;


	public Pabellon(Integer id, Double tamanioMetros, String nombre, Direccion direccion) {
	
		this.id = id;
		this.metrosCuadrados = tamanioMetros;
		this.nombre = nombre;
		this.direccion = direccion;
	}


	public Pabellon(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pabellon pabellon = (Pabellon) o;

        return nombre.equals(pabellon.nombre);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pabellon{");
        sb.append("id=").append(id);
        sb.append(", metrosCuadrados=").append(metrosCuadrados);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", direccion=").append(direccion);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", fechaModificacion=").append(fechaModificacion);
        sb.append('}');
        return sb.toString();
    }
	
	
	
	
	
	

}
