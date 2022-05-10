package com.example.demo.datos;

import dev.rmpedro.apirest.entities.Carrera;

public class DatosDummy {

    public static Carrera carrera01(){
        return new Carrera(null,"Ingenieria en Sistemas",50,5);

    }

    public static Carrera carrera02(){
        return new Carrera(null,"Licenciatura en Medicina",55,6);

    }

    public static Carrera carrera03(){
        return new Carrera(null,"Licenciatura en Agronegocios",45,4);

    }
    public static Carrera carrera04(){
        return new Carrera(null,"Ingenieria en Telematica",48,4);

    }
}
