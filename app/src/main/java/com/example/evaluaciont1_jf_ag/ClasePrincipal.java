package com.example.evaluaciont1_jf_ag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClasePrincipal extends AppCompatActivity {

    Button addContacto;
    Button crearGastoConjunto;
    Button configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase_principal);
        addContacto = findViewById(R.id.anadirPersona);
        crearGastoConjunto = findViewById(R.id.crearGastoConjunto);
        configuracion = findViewById(R.id.configuracion);

        addContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClasePrincipal.this, AddPersonActivity.class);
                startActivity(i);
            }
        });

        crearGastoConjunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClasePrincipal.this, CrearGasto.class);
                startActivity(i);
            }
        });

        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClasePrincipal.this, Configuracion.class);
            }
        });
    }
}