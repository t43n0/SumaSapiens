package com.example.evaluaciont1_jf_ag;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contacto {

    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "correo")
    private String correo;
    @ColumnInfo(name = "moneda")
    private String moneda;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
