package dev.rmpedro.apirest.datos;

import dev.rmpedro.apirest.enums.Pizarron;
import dev.rmpedro.apirest.models.entities.Aula;

public class AulasDatosDummy {
    public static Aula aula01() {

        return new Aula(null,1,"20x20",22,Pizarron.PIZARRA_BLANCA);
    }

    public static Aula aula02() {

        return new Aula(null,2,"20x20",40,Pizarron.PIZARRA_BLANCA);    }

    public static Aula aula03() {
        return new Aula(null,3,"20x20",30,Pizarron.PIZARRA_TIZA);

    }

    public static Aula aula04() {
        Aula aula = new Aula(null,4,"20x20",44,Pizarron.PIZARRA_BLANCA);
        aula.setPabellon(PabellonDatosDummy.pabellon01());
        return aula;
    }
}
