package dev.rmpedro.apirest.datos;

import dev.rmpedro.apirest.models.entities.Direccion;
import dev.rmpedro.apirest.models.entities.Pabellon;

public class PabellonDatosDummy {
    public static Pabellon pabellon01() {

        Direccion direccion = new Direccion(
                "Random street",
                "53704",
                "75725",
                "",
                "1",
                "Tehuacán"
        );

        Pabellon pabellon = new Pabellon(null,200d,"Elon Musk",direccion);
        return pabellon;
    }

    public static Pabellon pabellon02() {
        Direccion direccion = new Direccion(
                "Random street",
                "53704",
                "75725",
                "",
                "2",
                "Tehuacán"
        );

        Pabellon pabellon = new Pabellon(null,400d,"Nikola Tesla",direccion);
        return pabellon;
    }

    public static Pabellon pabellon03() {
        Direccion direccion = new Direccion(
                "Random street",
                "53704",
                "75725",
                "",
                "3",
                "Tehuacán"
        );

        Pabellon pabellon = new Pabellon(null,600d,"Steve Jobs",direccion);
        return pabellon;
    }
}
