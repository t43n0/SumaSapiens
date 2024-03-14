package com.example.evaluaciont1_jf_ag;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CrearGasto extends AppCompatActivity implements ContactoAdapter.OnItemClickListener{

    private RecyclerView rv;
    private ContactoAdapter ca;

    EditText etImporte;
    Button btnSiguiente;

    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gasto);

        etImporte = findViewById(R.id.etImporte);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ContactoDB db = Room.databaseBuilder(getApplicationContext(),
                ContactoDB.class, "contactos_db").allowMainThreadQueries().build();
        ContactoDao contactoDao = db.contactoDao();

        ca = new ContactoAdapter(contactoDao.getAllContactos(), this);
        rv.setAdapter(ca);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String importe = etImporte.getText().toString();
                int numPagadores = contador;

                Intent i = new Intent(CrearGasto.this, ResultGasto.class);
                i.putExtra("IMPORTE", importe);
                i.putExtra("PAGADORES", numPagadores);
                startActivity(i);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        contador = ca.getContadorSeleccionado();
    }
}
