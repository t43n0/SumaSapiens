package com.example.evaluaciont1_jf_ag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Configuracion extends AppCompatActivity {

    Button btnBorrarBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        btnBorrarBBDD = findViewById(R.id.borrarBBDD);

        btnBorrarBBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new bgthreat4().start();
                Toast.makeText(Configuracion.this, "Se borro la BBDD", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class bgthreat4 extends Thread {
        public void run() {
            super.run();

            ContactoDB db = Room.databaseBuilder(getApplicationContext(),
                    ContactoDB.class, "contactos_db").allowMainThreadQueries().build();
            ContactoDao contactoDao = db.contactoDao();

            contactoDao.deleteAll();
            contactoDao.restartSqnc();
        }
    }
}