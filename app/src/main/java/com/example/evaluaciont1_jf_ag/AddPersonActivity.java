package com.example.evaluaciont1_jf_ag;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPersonActivity extends AppCompatActivity {

    private LinearLayout personLayout;
    private Button addButton;
    private Button createAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_anadir);

        personLayout = findViewById(R.id.layout_person);
        addButton = findViewById(R.id.button_add);
        createAllButton = findViewById(R.id.button_crear_todos); // Referencia al nuevo botón

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPersonView();
            }
        });

        createAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar el código para crear todas las personas
                // Luego, puedes iniciar la actividad ClasePrincipal
                Intent intent = new Intent(AddPersonActivity.this, ClasePrincipal.class);
                startActivity(intent);
            }
        });
    }

    private void addPersonView() {
        // Crear la vista de la persona
        View personView = getLayoutInflater().inflate(R.layout.item_person, null);

        // Referenciar elementos de la vista de la persona
        EditText nameEditText = personView.findViewById(R.id.edit_text_name);
        EditText currencyEditText = personView.findViewById(R.id.edit_text_currency);
        Button createButton = personView.findViewById(R.id.button_create);

        // Configurar clic en el botón de crear
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                // Hacer algo con el nombre
                Toast.makeText(AddPersonActivity.this, "Persona creada: " + name, Toast.LENGTH_SHORT).show();
            }
        });

        // Agregar la vista de la persona al diseño principal
        personLayout.addView(personView);
    }
}