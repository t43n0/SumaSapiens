package com.example.evaluaciont1_jf_ag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private final int textColor; // Color del texto

    public CustomSpinnerAdapter(Context context, String[] values, int textColor) {
        super(context, android.R.layout.simple_spinner_item, values);
        this.context = context;
        this.values = values;
        this.textColor = textColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setText(values[position]);
        view.setTextColor(textColor); // Establecer color del texto
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setText(values[position]);
        view.setTextColor(textColor); // Establecer color del texto
        return view;
    }
}
