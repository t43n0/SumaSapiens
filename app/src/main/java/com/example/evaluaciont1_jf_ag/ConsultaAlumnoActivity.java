package com.example.evaluaciont1_jf_ag;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultaAlumnoActivity extends AppCompatActivity {

    private EditText nombreUsuarioEditText;
    private Button guardarButton;
    private Button limpiarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_alumno);

        // Referencias a los elementos del layout
        nombreUsuarioEditText = findViewById(R.id.editTextNombreUsuario);
        guardarButton = findViewById(R.id.buttonGuardar);
        limpiarButton = findViewById(R.id.buttonLimpiar);

        // Configuración de los clics en los botones
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    abrirAddPersonActivity();
                } else {
                    Toast.makeText(ConsultaAlumnoActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        limpiarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
    }

    private boolean validarCampos() {
        String nombreUsuario = nombreUsuarioEditText.getText().toString();
        return !nombreUsuario.isEmpty();
    }

    private void abrirAddPersonActivity() {
        Intent intent = new Intent(ConsultaAlumnoActivity.this, AddPersonActivity.class);
        startActivity(intent);
    }

    private void limpiar() {
        // Limpiar el campo de nombre de usuario
        nombreUsuarioEditText.setText("");
        Toast.makeText(this, "Campo de nombre de usuario limpiado", Toast.LENGTH_SHORT).show();
    }
}
