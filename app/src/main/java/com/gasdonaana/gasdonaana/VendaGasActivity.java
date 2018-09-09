package com.example.maico.donaanaapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maico.donaanaapp.Adapter.VendaAdapter;
import com.example.maico.donaanaapp.BancoDados.BancoInsert;
import com.example.maico.donaanaapp.BancoDados.BancoSelect;
import com.example.maico.donaanaapp.Fragment.CadastroVendaFragment;
import com.example.maico.donaanaapp.Helper.Data;
import com.example.maico.donaanaapp.Helper.Menssagens;
import com.example.maico.donaanaapp.Models.RegistradoraModel;
import com.example.maico.donaanaapp.Models.TeleModel;

import java.util.ArrayList;


public class VendaGasActivity extends AppCompatActivity {
    private ArrayAdapter<RegistradoraModel> arrayAdapter;
    private ArrayList<RegistradoraModel> vendaArray;
    private ArrayList<TeleModel> teleArray;
    ImageView btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda_gas);
        /*====================================================
         * DECLARACÃO DE COMPONENTES DO XML
         **************************************************/
        TextView dataAtual = findViewById(R.id.txtVendaData);
        ImageView btn_add = findViewById(R.id.imgVendaAdd);
        btn_save = findViewById(R.id.imgVendaSave);
        /*====================================================
         * MONTA O LIST VIEW E ADAPTER
         **************************************************/
        vendaArray = new ArrayList<>();
        teleArray = new ArrayList<>();
        ListView listaVenda = findViewById(R.id.lstVenda);
        arrayAdapter = new VendaAdapter(this, vendaArray, teleArray);
        listaVenda.setAdapter(arrayAdapter);
        if(Build.VERSION.SDK_INT >= 21){
            listaVenda.setNestedScrollingEnabled(true);
        }

        /*====================================================
         * Inicia o campo de data do dia atual no qual está sendo acessado
         **************************************************/
        dataAtual.setText(Data.Data_Hora("1",this));
        btn_save.setVisibility(View.GONE);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InserirInfos();
            }
        });

        /*====================================================
         * Inicia o processo de adicionar venda
         **************************************************/
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoSelect bancoSelect = new BancoSelect(VendaGasActivity.this);
                bancoSelect.conectarAobanco(0,"id,_descricao,_validade","produtos","ativo*1");
            }
        });
    }

    public void ProdutosDisponiveis(String informacoes){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragVenda = new CadastroVendaFragment();
        transaction.add(R.id.fragmentVenda, fragVenda, "CadastroVendaFragment");
        transaction.addToBackStack(null); //Linha super importante para  o retorno do fragment
        if(fragmentManager.findFragmentByTag("CadastroVendaFragment") == null) {

            Bundle bundle = new Bundle();
            bundle.putString("INFORMACOES", informacoes);
            fragVenda.setArguments(bundle);

            transaction.commit();
        }
    }

    private void InserirInfos(){
        BancoInsert bancoInsert = new BancoInsert(VendaGasActivity.this);

        //setar data e hora
        vendaArray.get(0).setData_venda(Data.Data_Hora("1",this));
        vendaArray.get(0).setHorario_venda(Data.Data_Hora("2", this));
        vendaArray.get(0).setHorario_entregue(Data.Data_Hora("2", this));
        //setar status
        if (vendaArray.get(0).getTele() == 0)  vendaArray.get(0).setStatus(1); else vendaArray.get(0).setStatus(0);

        bancoInsert.conectarAobanco(0,vendaArray.get(0), teleArray.get(0));
    }

    public void AtualizarLista(ArrayList<RegistradoraModel> arrayList) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); ) {
                vendaArray.add(arrayList.get(i));
                i++;
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public void Atualizarlista(RegistradoraModel registradoraModel, TeleModel teleModel){
        btn_save.setVisibility(View.VISIBLE);
        vendaArray.add(registradoraModel);
        teleArray.add(teleModel);
        arrayAdapter.notifyDataSetChanged();
        RemoverFragment();
    }

    private void RemoverFragment(){onBackPressed();}

    public void FinalizarVenda(){ Menssagens.showToastSucesso("Venda efetuada", this);finish(); }
}