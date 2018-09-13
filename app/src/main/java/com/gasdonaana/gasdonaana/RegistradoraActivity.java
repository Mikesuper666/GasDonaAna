package com.gasdonaana.gasdonaana;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.gasdonaana.gasdonaana.Adapter.RegistradoraAdapter;
import com.gasdonaana.gasdonaana.BancoDados.BancoSelect;
import com.gasdonaana.gasdonaana.Fragment.AlterarRegistFragment;
import com.gasdonaana.gasdonaana.Helper.Data;
import com.gasdonaana.gasdonaana.Models.RegistradoraModel;

import java.util.ArrayList;

public class RegistradoraActivity extends AppCompatActivity {
    private ArrayAdapter<RegistradoraModel> arrayAdapter;
    private ArrayList<RegistradoraModel> registradoraArray;
    private TextView liquinhoTxt, totalTxt, gasTxt, aguaTxt;
    private int quantidadeLiquinho, quantidadeGas, quantidadeAgua, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registradora);
        /*====================================================
         * DECLARACÃO DE COMPONENTES DO XML
         **************************************************/
        TextView dataAtual = findViewById(R.id.txtRegistradoraDataAtual);
        liquinhoTxt = findViewById(R.id.txtRegistradoraLiquinho);
        totalTxt = findViewById(R.id.txtRegistradoraTotal);
        gasTxt = findViewById(R.id.txtRegistradoraQtdeGas);
        aguaTxt = findViewById(R.id.txtRegistradoraQtdeAgua);

        /*====================================================
         * MONTA O LIST VIEW E ADAPTER
         **************************************************/
        registradoraArray = new ArrayList<>();
        ListView listaRegistradora = findViewById(R.id.lstRegistradora);
        arrayAdapter = new RegistradoraAdapter(this, registradoraArray);
        listaRegistradora.setAdapter(arrayAdapter);
        if(Build.VERSION.SDK_INT >= 21){
            listaRegistradora.setNestedScrollingEnabled(true);
        }

        /*====================================================
         * Inicia o campo de data do dia atual no qual está sendo acessado
         **************************************************/
        dataAtual.setText(Data.Data_Hora("1",this));

        BuscarInfos(dataAtual.getText().toString());

        listaRegistradora.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment alterarRegistFragment = new AlterarRegistFragment();
            transaction.add(R.id.fragmentRegistradora, alterarRegistFragment, "AlterarRegistFragment");
            transaction.addToBackStack(null); //Linha super importante para  o retorno do fragment
            if(fragmentManager.findFragmentByTag("AlterarRegistFragment") == null) {

                RegistradoraModel registradoraModel = registradoraArray.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("CODIGO", registradoraModel.getId());
                bundle.putInt("PRODINT", registradoraModel.getProduto());
                bundle.putString("PRODDESC", registradoraModel.getProdutoDescricao());
                bundle.putString("VENDEDOR", registradoraModel.getVendedor());
                bundle.putString("DATAVENDA", registradoraModel.getData_venda());
                bundle.putString("HOARIOVENDA", registradoraModel.getHorario_venda());
                bundle.putString("HOARIOENTREGUE", registradoraModel.getHorario_entregue());
                bundle.putInt("TELE", registradoraModel.getTele());
                bundle.putInt("PAGAMENTO", registradoraModel.getPagamento());
                bundle.putInt("STATUS", registradoraModel.getStatus());
                bundle.putInt("INTEIRA", registradoraModel.getInteira());
                bundle.putInt("QUANTIDADE", registradoraModel.getQuantidade());
                bundle.putInt("TOTAL", Integer.parseInt(registradoraModel.getTotal()));
                alterarRegistFragment
                        .setArguments(bundle);
                transaction.commit();
            }
            }
        });
    }

    private void BuscarInfos(String data){
        BancoSelect crud = new BancoSelect(this);
        crud.conectarAobanco(1,"v.*,p.descricao","vendas_v,produtos_p","p.id=v.produto_AND_v.datavenda*'"+data+"'","_ORDER_by_datavenda");
    }

    private void AtualizarLista(ArrayList<RegistradoraModel> arrayList) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); ) {
                registradoraArray.add(arrayList.get(i));
                total += Integer.parseInt(registradoraArray.get(i).getTotal());
                if(registradoraArray.get(i).getProduto() == 1){quantidadeGas += registradoraArray.get(i).getQuantidade();}
                else if(registradoraArray.get(i).getProduto() == 2){quantidadeLiquinho += registradoraArray.get(i).getQuantidade();}
                else{quantidadeAgua += registradoraArray.get(i).getQuantidade();}
                i++;
            }
            arrayAdapter.notifyDataSetChanged();
        }

        //Novo metodo de concatenação de strings aplicado pela google
        liquinhoTxt.setText(String.format(getResources().getString(R.string.string_un), quantidadeLiquinho));
        gasTxt.setText(String.format(getResources().getString(R.string.string_un), quantidadeGas));
        aguaTxt.setText(String.format(getResources().getString(R.string.string_un), quantidadeAgua));
        totalTxt.setText(String.format(getResources().getString(R.string.string_rs), total));
    }


    public void RecebendoResultados(String resultado) {
        //corta a string a cada '__' e insere dentro do array
        String dadosArray[] = resultado.split("__");

        ArrayList<RegistradoraModel> registradoraModels = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < dadosArray.length; ) {
            RegistradoraModel e = new RegistradoraModel();

            if (dadosArray[i].contains("^")) {
                break;
            } else {
                e.setId(dadosArray[i]);//id
                i++;
                e.setProduto(Integer.parseInt(dadosArray[i]));//produto
                i++;
                e.setVendedor(dadosArray[i]);//vendedor
                i++;
                e.setData_venda(dadosArray[i]);//datavenda
                i++;
                e.setHorario_venda(dadosArray[i]);//horario_venda
                i++;
                e.setHorario_entregue(dadosArray[i]);//horario_entregue
                i++;
                e.setTele(Integer.parseInt(dadosArray[i]));//tele
                i++;
                e.setPagamento(Integer.parseInt(dadosArray[i]));//pagamento
                i++;
                e.setStatus(Integer.parseInt(dadosArray[i]));//status
                i++;
                e.setInteira(Integer.parseInt(dadosArray[i]));//inteira
                i++;
                e.setQuantidade(Integer.parseInt(dadosArray[i]));//quantidade
                i++;
                e.setTotal(dadosArray[i]);//total
                i++;
                e.setProdutoDescricao(dadosArray[i]);//total
                i++;


                registradoraModels.add(count, e);
                count++;
            }
        }
        AtualizarLista(registradoraModels);
    }
}
