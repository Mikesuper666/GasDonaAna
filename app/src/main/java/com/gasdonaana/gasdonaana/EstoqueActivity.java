package com.gasdonaana.gasdonaana;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gasdonaana.gasdonaana.Helper.Menssagens;
import com.gasdonaana.gasdonaana.Helper.Preferencias;

import java.util.ArrayList;
import java.util.List;

public class EstoqueActivity extends AppCompatActivity {
    private List<Integer> bairrosContagem = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        Spinner acEstoqueSpinner = findViewById(R.id.acEstoqueSpinner);

        Preferencias preferencias = new Preferencias(this);

        //corta a string a cada '__' e insere dentro do array
        //metodo de atualização offline de endereços
        final String dadosArray[] = preferencias.getEderecos().split("__");

        List<String> bairros = new ArrayList<>();
        bairrosContagem.add(0);
        bairros.add("Selecione o bairro");
        for (int i = 0; i < dadosArray.length; ) {

            if (dadosArray[i].contains("^")) {
                break;
            } else {
                bairros.add(dadosArray[i]+" "+dadosArray[(i+1)]);
                bairrosContagem.add(Integer.parseInt(dadosArray[i].trim()));
                i+=2;
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,bairros);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        acEstoqueSpinner.setAdapter(dataAdapter);

        acEstoqueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Menssagens.showToastSucesso(bairrosContagem.get(position)+"",EstoqueActivity.this);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}