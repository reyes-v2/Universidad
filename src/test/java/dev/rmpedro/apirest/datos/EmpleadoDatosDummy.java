package dev.rmpedro.apirest.datos;


import dev.rmpedro.apirest.entities.Direccion;
import dev.rmpedro.apirest.entities.Empleado;
import dev.rmpedro.apirest.enums.TipoEmpleado;

import java.math.BigDecimal;

public class EmpleadoDatosDummy {

    public static Empleado empleado01() {
        Direccion direccion = new Direccion(
                "Calle Siempre viva",
                "1204",
                "75719",
                "",
                "",
                "Tehuacán"
        );

        Empleado empleado = new Empleado(null,"Braulio","Juarez","123123123",direccion,new BigDecimal(4000),TipoEmpleado.ADMINISTRATIVO);
        return empleado;
    }

    public static Empleado empleado02() {
        Direccion direccion = new Direccion(
                "Boulevard Héroes de Nacozari",
                "801",
                "75715",
                "",
                "",
                "Tehuacán"
        );

        Empleado empleado = new Empleado(null,"Luis","Marcial","097857495478",direccion,new BigDecimal(4000),TipoEmpleado.ADMINISTRATIVO);
        return empleado;
    }

    public static Empleado empleado03() {
        Direccion direccion = new Direccion(
                "Avenida José Garcicrespo",
                "729",
                "75721",
                "",
                "",
                "Tehuacán"
        );

        Empleado empleado = new Empleado(null,"Andrea","Reyes","7689475947",direccion,new BigDecimal(4000),TipoEmpleado.ADMINISTRATIVO);
        return empleado;
    }
}
