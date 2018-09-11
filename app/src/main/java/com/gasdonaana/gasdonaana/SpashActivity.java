package com.gasdonaana.gasdonaana;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.gasdonaana.gasdonaana.BancoDados.BancoSelect;
import com.gasdonaana.gasdonaana.Helper.Preferencias;

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
            crud.conectarAobanco(2,"id,rua","ruas","","_ORDER_by_rua");
            }
        });
    }

    public void RecebendoEnderecos(String resultados){
        String[] ArraysAseparar = resultados.split("#_#");

        Preferencias preferencias = new Preferencias(this);
        //endereços
        preferencias.setEnderecos(ArraysAseparar[0]);
        //bairros
        preferencias.setBairros(ArraysAseparar[1]);
        //funcionarios
        preferencias.setFuncionarios(ArraysAseparar[2]);

        ProsseguirLogin();
    }

    private void ProsseguirLogin(){
        Intent it = new Intent(SpashActivity.this, LoginActivity.class);
        startActivity(it);
    }
}