package com.example.evaluaciont1_jf_ag;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactoDao {
    @Insert
    void insertContacto(Contacto contacto);

    @Query("SELECT * FROM Contacto")
    List<Contacto> getAllContactos();

    @Query("SELECT * FROM Contacto WHERE nombre = :nombre")
    List<Contacto> getAllContactosPorNombre(String nombre);
}
