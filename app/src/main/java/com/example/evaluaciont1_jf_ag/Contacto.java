package com.example.evaluaciont1_jf_ag;

import java.io.Serializable;

public class Contacto {
    private String nombre;
    private String correo;
    private String moneda;

    public Contacto() {

    }

    public Contacto(String nombre, String correo, String moneda) {
        this.nombre = nombre;
        this.correo = correo;
        this.moneda = moneda;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getMoneda() {
        return moneda;
    }
}
