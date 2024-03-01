package com.example.evaluaciont1_jf_ag;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroNotaActivity extends AppCompatActivity {

    EditText editTextNombreUsuario;
    EditText editTextContraseña;
    Button buttonGuardar;
    Button buttonLimpiar;

    Spinner spinnerTipoMoneda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nota);

        // Vincular los elementos desde el layout
        spinnerTipoMoneda = findViewById(R.id.spinnerTipoMoneda);
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonLimpiar = findViewById(R.id.buttonLimpiar);

        // Definir las opciones de moneda
        String[] opcionesMoneda = {"$", "€", "¥", "£", "₹"};
        int textColor = getResources().getColor(R.color.colorPrimary); // Cambia este color al que desees
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, opcionesMoneda, textColor);
        spinnerTipoMoneda.setAdapter(adapter);

        // Configurar los ClickListeners para los botones
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        buttonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });
    }

    private void guardarDatos() {
        String nombreUsuario = editTextNombreUsuario.getText().toString();
        String contraseña = editTextContraseña.getText().toString();
        String tipoMoneda = spinnerTipoMoneda.getSelectedItem().toString();

        // Aquí puedes implementar la lógica para guardar los datos, como enviarlos a una base de datos o almacenarlos en SharedPreferences
    }

    private void limpiarCampos() {
        editTextNombreUsuario.setText("");
        editTextContraseña.setText("");
        // No necesitas limpiar el texto del Spinner
    }
}
