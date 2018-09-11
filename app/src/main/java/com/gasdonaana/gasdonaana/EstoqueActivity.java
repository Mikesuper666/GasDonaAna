package com.gasdonaana.gasdonaana;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gasdonaana.gasdonaana.Helper.Preferencias;

import java.util.ArrayList;
import java.util.List;

public class EstoqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        Spinner acEstoqueSpinner = findViewById(R.id.acEstoqueSpinner);

        Preferencias preferencias = new Preferencias(this);

        //corta a string a cada '__' e insere dentro do array
        //metodo de atualização offline de endereços
        String dadosArray[] = preferencias.getBairros().split("__");

        List<String> bairros = new ArrayList<>();
        dadosArray[0] = "Selecione o bairro";
        for (int i = 0; i < dadosArray.length; ) {

            if (dadosArray[i].contains("^")) {
                break;
            } else {
                bairros.add(dadosArray[i]);
                i++;
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,bairros);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        acEstoqueSpinner.setAdapter(dataAdapter);
    }
}