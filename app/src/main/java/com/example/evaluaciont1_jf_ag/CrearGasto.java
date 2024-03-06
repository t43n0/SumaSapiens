package com.example.evaluaciont1_jf_ag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

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

        contactoList = leerContactosDesdeArchivo("contactos.txt");

        ca = new ContactoAdapter(contactoList);
        rv.setAdapter(ca);

    }

    private List<Contacto> leerContactosDesdeArchivo(String s) {
        List<Contacto> contactos = new ArrayList<>();

        try {
            FileInputStream fis = openFileInput(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;

            while ((line = br.readLine()) != null) {
                // Crea un nuevo EmailModel y añádelo a la lista
                Contacto contact = buscarContactoPorEmail(line);
                contactos.add(contact);
            }

            br.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactos;
    }

    private Contacto buscarContactoPorEmail(String email) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("usuarios");
        final Contacto[] contacto = new Contacto[1];
        dbRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        contacto[0] = new Contacto(usuario.getNombre(), usuario.email, usuario.getTipoMoneda());
                    }
                } else {
                    Toast.makeText(CrearGasto.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return contacto[0];
    }
}
