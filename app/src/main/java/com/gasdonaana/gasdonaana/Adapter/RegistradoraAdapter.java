package com.gasdonaana.gasdonaana.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gasdonaana.gasdonaana.Models.RegistradoraModel;
import com.gasdonaana.gasdonaana.R;

import java.util.ArrayList;

public class RegistradoraAdapter extends ArrayAdapter<RegistradoraModel> {
    private Context context;
    private ArrayList<RegistradoraModel> registradoraModels;

    public RegistradoraAdapter(@NonNull Context context, @NonNull ArrayList<RegistradoraModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.registradoraModels = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inicializa os objetos para montagem da lista
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.widget_registradora, parent, false);

        //verifica se a lista está preenchida
        if(registradoraModels != null){
            //seta valores nos componetes de tela
            RegistradoraModel registradoraModel = registradoraModels.get(position);
            //Recuperar elementos da tela
            TextView txtValor = view.findViewById(R.id.widget_registradora_txtValor); //Armazena o valor
            TextView txtDescricao = view.findViewById(R.id.widget_registradora_txtDescricao); //Armazena o valor
            TextView txtQuantidade = view.findViewById(R.id.widget_registradora_txtQuantidade); //Armazena o valor
            TextView txtTele = view.findViewById(R.id.widget_registradora_txtTele); //Armazena o valor
            ImageView img = view.findViewById(R.id.widget_registradora_imgProduto);
            ImageView imgStatus = view.findViewById(R.id.widget_registradora_imgStatus);


            txtDescricao.setText(registradoraModel.getProdutoDescricao());
            txtValor.setText(registradoraModel.getTotal());
            txtQuantidade.setText(String.format(context.getResources().getString(R.string.string_un), registradoraModel.getQuantidade()));

            //setando tele
            txtTele.setText((registradoraModel.getTele() == 0)?"Nao":"Sim");


            @SuppressLint("Recycle") final TypedArray imgStatusTyped = context.getResources().obtainTypedArray(R.array.array_produtos_icones_status);
            @SuppressLint("Recycle") final TypedArray imgTyped = context.getResources().obtainTypedArray(R.array.array_produtos_icones);

            //Adiciona imagem no staus e icone de apresentação
            imgStatus.setImageResource(imgStatusTyped.getResourceId(registradoraModel.getStatus(), -1));
            img.setImageResource(imgTyped.getResourceId(registradoraModel.getProduto(), -1));


        }
        return view;
    }
}
