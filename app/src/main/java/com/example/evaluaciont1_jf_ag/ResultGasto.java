package com.example.evaluaciont1_jf_ag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultGasto extends AppCompatActivity {

    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_gasto);
        tvTotal = findViewById(R.id.tvTotal);

        int numPagadores = getIntent().getIntExtra("PAGADORES", 1);
        String importe = getIntent().getStringExtra("IMPORTE");

        // Verificar que importe no sea infinito
        assert importe != null;
        Double importeDouble = Double.parseDouble(importe);
        if (Double.isInfinite(importeDouble)) {
            tvTotal.setText("Error: El importe es infinito");
            return;
        }

        // Verificar que numPagadores no sea cero
        if (numPagadores == 0) {
            tvTotal.setText("Error: No se puede dividir por cero");
            return;
        }

        // Convertir numPagadores a Double
        Double numPagadoresDouble = (double) numPagadores;

        // Realizar la división de punto flotante
        Double total = importeDouble / numPagadoresDouble;

        tvTotal.setText(total.toString());
    }
}
