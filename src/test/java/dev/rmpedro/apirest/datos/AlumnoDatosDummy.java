package dev.rmpedro.apirest.datos;


import dev.rmpedro.apirest.models.entities.Alumno;
import dev.rmpedro.apirest.models.entities.Direccion;

public class AlumnoDatosDummy {

    public static Alumno alumno01() {
        Direccion direccion = new Direccion(
                "Calle José María Morelos y Pavón",
                "2434",
                "75720",
                "",
                "",
                "Tehuacán"
        );

        return new Alumno(1,"PEdro","Reyes","23323232",direccion);
    }

    public static Alumno alumno02() {
        Direccion direccion = new Direccion(
                "Calle Miguel Hidalgo",
                "1219",
                "75718",
                "",
                "",
                "Tehuacán"
        );

        return new Alumno(2,"Ximena","Cruz","232323123",direccion);
    }

    public static Alumno alumno03() {
        Direccion direccion = new Direccion(
                "Calle Lomas de la Soledad",
                "2320",
                "75728",
                "",
                "",
                "Tehuacán"
        );

        return new Alumno(3,"Alondra","Lopez","9098908098",direccion);
    }
}
