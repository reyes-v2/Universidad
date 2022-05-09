package dev.rmpedro.apirest.entities;

import java.math.BigDecimal;
import java.util.Date;

import dev.rmpedro.apirest.enums.TipoEmpleado;
import lombok.*;
import javax.persistence.*;
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "empleados",schema="universidad")
@PrimaryKeyJoinColumn(name="persona_id")
public class Empleado extends Persona{
	
	@Column(name="sueldo")
	private BigDecimal sueldo;
	@Column(name="tipo_empleado")
	@Enumerated(EnumType.STRING)
	private TipoEmpleado tipoEmpleado;
	
	@OneToOne(optional=true,cascade= CascadeType.ALL)
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    private Pabellon pabellon;


	public Empleado(Integer id, String nombre, String apellido, String dni, Direccion direccion,BigDecimal sueldo, TipoEmpleado tipoEmpleado) {
		super(id, nombre, apellido, dni, direccion);
		this.sueldo=sueldo;
		this.tipoEmpleado=tipoEmpleado;
	}
	
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("sueldo=").append(sueldo);
        sb.append(", tipo=").append(tipoEmpleado);
        sb.append(", pabellon=").append(pabellon);
        sb.append('}');
        return sb.toString();
    }

	
	private static final long serialVersionUID = 9208097717262734155L;
	

	

}
