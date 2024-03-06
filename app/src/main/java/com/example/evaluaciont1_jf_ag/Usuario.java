package com.example.evaluaciont1_jf_ag;

import java.util.ArrayList;

public class Usuario {
    String email;
    String nombre;
    String tipoMoneda;
    String contrasena;

    public Usuario() {

    }

    public Usuario(String email, String nombre, String tipoMoneda) {
        this.email = email;
        this.nombre = nombre;
        this.tipoMoneda = tipoMoneda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
