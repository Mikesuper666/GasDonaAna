package com.gasdonaana.gasdonaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gasdonaana.gasdonaana.BancoDados.BancoSelect;
import com.gasdonaana.gasdonaana.Helper.Preferencias;

import java.util.ArrayList;
import java.util.List;

public class SpashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imagem = findViewById(R.id.imgSpashScreen);

        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            BancoSelect crud = new BancoSelect(SpashActivity.this);
            crud.conectarAobanco(2,"rua","ruas","");
            }
        });
    }

    public void RecebendoEnderecos(String resultados){
//corta a string a cada '__' e insere dentro do array
        String dadosArray[] = resultados.split("__");

        List<String> bairros = new ArrayList<>();

        for (int i = 0; i < dadosArray.length; ) {

            if (dadosArray[i].contains("^")) {
                break;
            } else {
                bairros.add(dadosArray[i]);
                i++;
            }
        }
        Preferencias preferencias = new Preferencias(this);

        preferencias.SetarLista(bairros);


        ProsseguirLogin();
    }

    private void ProsseguirLogin(){
        Intent it = new Intent(SpashActivity.this, LoginActivity.class);
        startActivity(it);
    }
}