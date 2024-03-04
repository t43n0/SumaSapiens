package com.example.evaluaciont1_jf_ag;

public class Usuario {
    String email;
    String apellidos;
    String tipoMoneda;
    String contrasena;

    public Usuario() {

    }

    public Usuario(String email, String apellidos, String tipoMoneda) {
        this.email = email;
        this.apellidos = apellidos;
        this.tipoMoneda = tipoMoneda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
