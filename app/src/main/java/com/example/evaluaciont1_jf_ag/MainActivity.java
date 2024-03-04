package com.example.evaluaciont1_jf_ag;
import com.bumptech.glide.Glide;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnRegistrarNota;
    Button btnConsultarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageViewBackground = findViewById(R.id.imageViewBackground);
        Glide.with(this).load(R.drawable.fto_gif).into(imageViewBackground);

        Button btnRegistrarNota = findViewById(R.id.btnRegistrarNota);
        Button btnConsultarNota = findViewById(R.id.btnConsultarNota);

        btnRegistrarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });

        btnConsultarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InicioSesion.class);
                startActivity(intent);
            }
        });


    }
}
