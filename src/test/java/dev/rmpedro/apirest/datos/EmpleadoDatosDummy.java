package dev.rmpedro.apirest.datos;


import dev.rmpedro.apirest.models.entities.Direccion;
import dev.rmpedro.apirest.models.entities.Empleado;
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

        Empleado empleado = new Empleado(1,"Braulio","Juarez","5465465",direccion,new BigDecimal(4000),TipoEmpleado.ADMINISTRATIVO);
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

        Empleado empleado = new Empleado(2,"Luis","Marcial","54565",direccion,new BigDecimal(4000),TipoEmpleado.ADMINISTRATIVO);
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

        Empleado empleado = new Empleado(3,"Andrea","Reyes","655645",direccion,new BigDecimal(4000),TipoEmpleado.ADMINISTRATIVO);
        return empleado;
    }
}
