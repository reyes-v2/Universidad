package dev.rmpedro.apirest.models.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import dev.rmpedro.apirest.enums.Pizarron;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
//@Table(name="aulas", schema = "universidad")
@Table(name="aulas")
public class Aula implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "numero_aula",nullable = false)
	private Integer numeroAula;

	private String medidas;
	@Column(name = "cantidad_pupitres")
	private Integer cantidadPupitres;

	@Column(name = "tipo_pizarron")
	@Enumerated(EnumType.STRING)
	private Pizarron pizarron;
	@Column(name = "fecha_creacion",nullable = false)
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;

	@ManyToOne(optional = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "pabellon_id",foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
	private Pabellon pabellon;

	@Serial
	private static final long serialVersionUID = -2124486972432428615L;

	public Aula(Integer id, Integer numeroAula, String medidas, Integer cantidadPupitres, Pizarron pizarron) {
		
		this.id = id;
		this.numeroAula = numeroAula;
		this.medidas = medidas;
		this.cantidadPupitres = cantidadPupitres;
		this.pizarron = pizarron;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numeroAula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroAula, other.numeroAula);
	}
	
	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion= new Date();
		
	}
	
	@PreUpdate
	private void antesModificar() {
		this.fechaModificacion= new Date();
		
	}
	
	
	
	

}
