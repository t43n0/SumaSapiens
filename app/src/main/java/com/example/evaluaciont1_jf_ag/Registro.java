package com.example.evaluaciont1_jf_ag;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registro extends AppCompatActivity {

    EditText etEmail;
    EditText etNombre;
    EditText editTextContrasena;
    Button buttonGuardar;
    Button buttonLimpiar;
    Spinner spinnerTipoMoneda;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Vincular los elementos desde el layout
        spinnerTipoMoneda = findViewById(R.id.spinnerTipoMoneda);
        etEmail = findViewById(R.id.etEmail);
        etNombre = findViewById(R.id.etNombre);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonLimpiar = findViewById(R.id.buttonLimpiar);
        mAuth = FirebaseAuth.getInstance();

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
        String email = etEmail.getText().toString();
        String contrasena = editTextContrasena.getText().toString();
        String tipoMoneda = spinnerTipoMoneda.getSelectedItem().toString();
        String nombre = etNombre.getText().toString();

        if (email.isEmpty() || contrasena.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("usuarios");

                                Usuario usuario = new Usuario(email, nombre, tipoMoneda);

                                dbRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            Toast.makeText(Registro.this, "Ya existe un usuario con ese correo", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(Registro.this, InicioSesion.class);
                                            startActivity(i);
                                        } else {
                                            dbRef.push().setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(Registro.this, "El usuario se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                                                    Intent i = new Intent(Registro.this, InicioSesion.class);
                                                    startActivity(i);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Registro.this, "Ha habido un error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Manejar error de base de datos si es necesario
                                    }
                                });
                            } else {
                                // Si falla la autenticación, mostrar un mensaje al usuario.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Registro.this, "La autenticación ha fallado.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void limpiarCampos() {
        etEmail.setText("");
        etNombre.setText("");
        editTextContrasena.setText("");
    }
}
