package com.example.evaluaciont1_jf_ag;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioSesion extends AppCompatActivity {

    private EditText etEmail;
    private EditText etContrasena;
    private Button guardarButton;
    private Button limpiarButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        // Referencias a los elementos del layout
        etEmail = findViewById(R.id.etEmail);
        etContrasena = findViewById(R.id.editTextContrasena);
        guardarButton = findViewById(R.id.buttonGuardar);
        limpiarButton = findViewById(R.id.buttonLimpiar);
        mAuth = FirebaseAuth.getInstance();

        // Configuración de los clics en los botones
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        limpiarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
    }

    private void iniciarSesion() {
        String contrasena = etContrasena.getText().toString();
        String email = etEmail.getText().toString();

        mAuth.signInWithEmailAndPassword(email, contrasena)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(InicioSesion.this, AddPersonActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(InicioSesion.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void limpiar() {
        etContrasena.setText("");
        etEmail.setText("");

        Toast.makeText(this, "Campo de nombre de usuario limpiado", Toast.LENGTH_SHORT).show();
    }
}
