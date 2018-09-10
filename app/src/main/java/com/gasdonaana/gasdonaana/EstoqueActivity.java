package com.gasdonaana.gasdonaana;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gasdonaana.gasdonaana.Helper.Preferencias;

public class EstoqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        Spinner acEstoqueSpinner = findViewById(R.id.acEstoqueSpinner);

        Preferencias preferencias = new Preferencias(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, preferencias.getLista());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        acEstoqueSpinner.setAdapter(dataAdapter);
    }
}