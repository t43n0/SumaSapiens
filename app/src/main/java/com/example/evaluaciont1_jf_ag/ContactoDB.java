package com.example.evaluaciont1_jf_ag;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contacto.class}, version = 1)
public abstract class ContactoDB extends RoomDatabase {
    public abstract ContactoDao contactoDao();
}
