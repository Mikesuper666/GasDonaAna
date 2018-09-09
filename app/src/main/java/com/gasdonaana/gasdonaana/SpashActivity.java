package com.gasdonaana.gasdonaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gasdonaana.gasdonaana.BancoDados.BancoSelect;

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
            crud.conectarAobanco(1,"rua","ruas","");
            }
        });
    }

    public void RecebendoEnderecos(String resultados){
        Intent it = new Intent(SpashActivity.this, LoginActivity.class);
        startActivity(it);
    }
}