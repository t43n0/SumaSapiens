package com.example.evaluaciont1_jf_ag;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddPersonActivity extends AppCompatActivity {

    EditText etEmail;
    TextView tvNombre;
    TextView tvMoneda;
    Button btnAgregar;
    Button btnBuscar;
    Button btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        etEmail = findViewById(R.id.etEmail);
        tvNombre = findViewById(R.id.tvNombre);
        tvMoneda = findViewById(R.id.tvMoneda);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnBorrar = findViewById(R.id.btnLimpiar);
        btnAgregar.setEnabled(false);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().isEmpty()) {
                    Toast.makeText(AddPersonActivity.this, "Debes introducir el correo de la persona que desees agregar", Toast.LENGTH_SHORT).show();
                } else {
                    buscarPorCorreo(etEmail.getText().toString());
                    etEmail.setEnabled(false);
                }
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = etEmail.getText().toString();
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("usuarios");
                dbRef.orderByChild("email").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Usuario usuario = snapshot.getValue(Usuario.class);
                                String contenido = etEmail.getText().toString();

                                try {
                                    FileInputStream fis = openFileInput("contactos.txt");

                                    // Lee el contenido del archivo
                                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                                    StringBuilder sb = new StringBuilder();
                                    String linea;

                                    while ((linea = br.readLine()) != null) {
                                        sb.append(linea).append("\n");
                                    }

                                    // Cierra el flujo de entrada
                                    fis.close();

                                    // Obtén el contenido como String
                                    String contenidoLeido = sb.toString();

                                    // Comprueba si el valor coincide
                                    if (contenidoLeido.equals(contenido)) {
                                        Toast.makeText(AddPersonActivity.this, "Ya tienes a este contacto agregado", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Abre o crea el archivo en modo privado
                                        FileOutputStream fos = openFileOutput("contactos.txt", Context.MODE_PRIVATE);

                                        // Escribe en el archivo
                                        fos.write(contenido.getBytes());

                                        // Cierra el flujo de salida
                                        fos.close();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    // Manejo de errores aquí
                                }

                                Toast.makeText(AddPersonActivity.this, "Contacto añadido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "No se encontró un usuario con el correo proporcionado");
                            Toast.makeText(AddPersonActivity.this, "No se encontró un usuario con el correo proporcionado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Manejar error de base de datos si es necesario
                        Log.e(TAG, "Error al obtener el usuario: " + databaseError.getMessage());
                    }
                });
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEmail.setEnabled(true);
                etEmail.setText("");
            }
        });
    }

    private void buscarPorCorreo(String correo) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("usuarios");

        dbRef.orderByChild("email").equalTo(correo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Usuario usuario = snapshot.getValue(Usuario.class);
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();

                        if (usuario.getEmail().equals(mAuth.getCurrentUser().getEmail())) {
                            Toast.makeText(AddPersonActivity.this, "No puedes agregarte a ti mismo", Toast.LENGTH_SHORT).show();
                        } else {
                            tvNombre.setText(usuario.getNombre());
                            tvMoneda.setText(usuario.getTipoMoneda());
                            btnAgregar.setEnabled(true);
                        }
                    }
                } else {
                    Log.d(TAG, "No se encontró un usuario con el correo proporcionado");
                    Toast.makeText(AddPersonActivity.this, "No se encontró un usuario con el correo proporcionado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar error de base de datos si es necesario
                Log.e(TAG, "Error al obtener el usuario: " + databaseError.getMessage());
            }
        });
    }
}