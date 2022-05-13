package dev.rmpedro.apirest.datos;


import dev.rmpedro.apirest.models.entities.Direccion;
import dev.rmpedro.apirest.models.entities.Profesor;

import java.math.BigDecimal;

public class ProfesorDatosDummy {

    public static Profesor profesor01() {
        Direccion direccion = new Direccion(
                "José María Morelos y Pavón",
                "2434",
                "75720",
                "",
                "",
                "Tehuacán"
        );

        return new Profesor(null,"Pedro","Reyes","23323232",direccion,new BigDecimal(1231));
    }

    public static Profesor profesor02() {
        Direccion direccion = new Direccion(
                "3 Oriente",
                "2220",
                "75724",
                "",
                "",
                "Tehuacán"
        );

        return new Profesor(null,"Marissa","Reyes","32131231",direccion,new BigDecimal(1600));
    }

    public static Profesor profesor03() {
        Direccion direccion = new Direccion(
                "Heroes de la Independencia",
                "1502",
                "75730",
                "",
                "",
                "Tehuacán"
        );

        return new Profesor(null,"Arely","Zuniga","12344532",direccion,new BigDecimal(1900));
    }

}
