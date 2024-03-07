package com.example.evaluaciont1_jf_ag;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CrearGasto extends AppCompatActivity {

    private RecyclerView rv;
    private ContactoAdapter ca;
    private List<Contacto> contactoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gasto);

        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        leerContactosDesdeArchivo(new OnContactosLeidosListener() {
            @Override
            public void onContactosLeidos(List<Contacto> contactos) {
                Log.d(TAG, "onContactosLeidos: " + contactos.size() + " contactos leídos");
                contactoList = contactos;
                ca = new ContactoAdapter(contactoList);
                rv.setAdapter(ca);
            }
        });
    }

    private void leerContactosDesdeArchivo(OnContactosLeidosListener listener) {
        List<Contacto> contactos = new ArrayList<>();

        try {
            FileInputStream fis = getApplicationContext().openFileInput("contactos.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;

            while ((line = br.readLine()) != null) {
                // Crea un nuevo EmailModel y añádelo a la lista
                buscarContactoPorEmail(line, new OnContactoEncontradoListener() {
                    @Override
                    public void onContactoEncontrado(Contacto contacto) {
                        Log.d(TAG, "onContactoEncontrado: " + contacto.getNombre());
                        contactos.add(contacto);
                    }
                });
            }

            br.close();
            fis.close();
            listener.onContactosLeidos(contactos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void buscarContactoPorEmail(String email, OnContactoEncontradoListener listener) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("usuarios");
        dbRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        Contacto contacto = new Contacto(usuario.getNombre(), usuario.email, usuario.getTipoMoneda());
                        listener.onContactoEncontrado(contacto);
                    }
                } else {
                    Toast.makeText(CrearGasto.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar cancelación si es necesario
            }
        });
    }

}
