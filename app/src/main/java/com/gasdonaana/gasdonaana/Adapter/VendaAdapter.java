package com.gasdonaana.gasdonaana.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gasdonaana.gasdonaana.Helper.BaseInfos;
import com.gasdonaana.gasdonaana.Helper.Preferencias;
import com.gasdonaana.gasdonaana.Models.RegistradoraModel;
import com.gasdonaana.gasdonaana.Models.TeleModel;
import com.gasdonaana.gasdonaana.R;

import java.util.ArrayList;

public class VendaAdapter extends ArrayAdapter<RegistradoraModel> {
    private Context context;
    private ArrayList<RegistradoraModel> registradoraModels;
    private ArrayList<TeleModel> teleModels;

    public VendaAdapter(@NonNull Context context, @NonNull ArrayList<RegistradoraModel> objects, @NonNull ArrayList<TeleModel> teleObjects) {
        super(context, 0, objects);
        this.context = context;
        this.registradoraModels = objects;
        this.teleModels = teleObjects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        RegistradoraModel registradoraModel;
        TeleModel teleModel;

        //verifica se a lista está preenchida
        if(registradoraModels != null){
            //inicializa os objetos para montagem da lista
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //monta a view apartir do xml
            assert layoutInflater != null;
            view = layoutInflater.inflate(R.layout.widget_venda, parent, false);

            //seta valores nos componetes de tela
            registradoraModel = registradoraModels.get(position);
            //Recuperar elementos da tela
            TextView txtVendaInteira = view.findViewById(R.id.widget_venda_inteira); //Armazena o valor
            TextView txtValor = view.findViewById(R.id.widget_venda_valor); //Armazena o valor
            TextView txtDescricao = view.findViewById(R.id.widget_venda_descricao); //Armazena o valor
            TextView txtQuantidade = view.findViewById(R.id.widget_venda_quantidade); //Armazena o valor
            TextView txtTele = view.findViewById(R.id.widget_venda_tele); //Armazena o valor
            ImageView imgPagamento = view.findViewById(R.id.widget_venda_pagamento); //Armazena o valor

            //Dados de entrega
            LinearLayout fragVendaLinearEntrega = view.findViewById(R.id.widget_venda_telentrega);

            //Setando dados da venda
            txtVendaInteira.setText((registradoraModel.getInteira() == 0)?"Valor":"Valor(Inteira)");
            txtDescricao.setText(registradoraModel.getProdutoDescricao());
            txtValor.setText(registradoraModel.getTotal());
            txtQuantidade.setText(String.format(context.getResources().getString(R.string.string_un), registradoraModel.getQuantidade()));

            //setando pagamento
            final TypedArray imgStatusTyped = context.getResources().obtainTypedArray(R.array.array_pagamento_ico);

            //Adiciona imagem no staus e icone de apresentação
            imgPagamento.setImageResource(imgStatusTyped.getResourceId(registradoraModel.getPagamento(), -1));

            //setando tele
            txtTele.setText((registradoraModel.getTele() == 0)?"Nao":"Sim");
            fragVendaLinearEntrega.setVisibility((registradoraModel.getTele() == 0) ? View.GONE : View.VISIBLE);
            //verifica se a lista está preenchida
            if(registradoraModel.getTele() != 0){
                //seta valores nos componetes de tela
                teleModel = teleModels.get(position);
                TextView widget_venda_rua = view.findViewById(R.id.widget_venda_rua);
                TextView widget_venda_numero = view.findViewById(R.id.widget_venda_numero_casa);
                TextView widget_venda_bairro = view.findViewById(R.id.widget_venda_bairro);
                TextView widget_venda_motoboy = view.findViewById(R.id.widget_venda_motoboy);

                //preferencias se tele exixtir
                Preferencias preferencias = new Preferencias(context);

                //setando o endereço
                widget_venda_rua.setText(teleModel.getEnds()[1]);////teleModel.getEndereco());

                widget_venda_numero.setText(String.valueOf(teleModel.getNumero()));
                widget_venda_bairro.setText("ss");//teleModel.getBairro());
                widget_venda_motoboy.setText(teleModel.getEntregadorDescicao());
            }
        }
        return view;
    }
}
