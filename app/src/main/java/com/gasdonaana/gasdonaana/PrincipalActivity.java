package com.gasdonaana.gasdonaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gasdonaana.gasdonaana.R;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageView btnRegistradora = findViewById(R.id.imgRegistradora);
        ImageView btnVendaGas = findViewById(R.id.imgVendaGas);
        ImageView btnEstoque = findViewById(R.id.imgEstoque);
        ImageView btnCompra = findViewById(R.id.imgConfig);

        btnRegistradora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PrincipalActivity.this, RegistradoraActivity.class);
                startActivity(it);
            }
        });

        btnVendaGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PrincipalActivity.this, VendaGasActivity.class);
                startActivity(it);
            }
        });

        btnEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PrincipalActivity.this, EstoqueActivity.class);
                startActivity(it);
            }
        });

        btnCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PrincipalActivity.this, EstoqueActivity.class);
                startActivity(it);
            }
        });
    }
}
