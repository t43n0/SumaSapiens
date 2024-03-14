package com.example.evaluaciont1_jf_ag;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {

    private List<Contacto> contactos;
    private OnItemClickListener listener;
    private int selectedItem = RecyclerView.NO_POSITION; // Variable para almacenar el índice del elemento seleccionado
    private int contadorTotal = 0;
    private int contadorSeleccionado = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ContactoAdapter(List<Contacto> contactos, OnItemClickListener listener) {
        this.contactos = contactos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto_item, parent, false);
        return new ContactoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);
        holder.bind(contacto);

        // Establecer el color de fondo del elemento seleccionado
        if (selectedItem == position) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.selected_color));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedItem = selectedItem;
                // Si el elemento actual ya está seleccionado, deselecciónalo
                if (selectedItem == position) {
                    selectedItem = RecyclerView.NO_POSITION; // Deseleccionar el elemento
                    notifyItemChanged(previousSelectedItem);
                    contadorSeleccionado--; // Decrementar el contador de elementos seleccionados
                } else {
                    listener.onItemClick(position);
                    selectedItem = position; // Seleccionar el nuevo elemento
                    notifyItemChanged(previousSelectedItem);
                    notifyItemChanged(selectedItem);
                    contadorSeleccionado++; // Incrementar el contador de elementos seleccionados
                }

                // Actualizar el contador total solo si se ha modificado el contador de elementos seleccionados
                if (contadorSeleccionado > 0 || previousSelectedItem != RecyclerView.NO_POSITION) {
                    contadorTotal = getItemCount();
                }

                // Actualizar el contador
                notifyDataSetChanged(); // Notificar al adaptador sobre los cambios en los datos
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public int getContadorSeleccionado() {
        return contadorSeleccionado;
    }

    // Método para obtener el contador total de elementos
    public int getContadorTotal() {
        return contadorTotal;
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvMoneda;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvMoneda = itemView.findViewById(R.id.tvMoneda);
        }

        public void bind(Contacto contacto) {
            tvNombre.setText(contacto.getNombre());
            tvMoneda.setText(contacto.getMoneda());
        }
    }
}
