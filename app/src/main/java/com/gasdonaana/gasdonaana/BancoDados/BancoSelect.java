package com.gasdonaana.gasdonaana.BancoDados;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.gasdonaana.gasdonaana.Helper.Menssagens;
import com.gasdonaana.gasdonaana.Helper.Preferencias;
import com.gasdonaana.gasdonaana.RegistradoraActivity;
import com.gasdonaana.gasdonaana.SpashActivity;
import com.gasdonaana.gasdonaana.VendaGasActivity;

import java.util.ArrayList;

public class BancoSelect {
    private Context context;
    private ProgressDialog dialogBaixando;
    private int acao;
    private Preferencias preferencias;

    public BancoSelect(Context context){
        this.context = context;
        preferencias = new Preferencias(context);
    }

    public void conectarAobanco(int acao, String select, String from, String where) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            //URL da api que recebará as infos requisitadas
            String url = "http://192.168.0.102/da_gas/db/select/select.php?acao="+acao+"&select="+select+"&FROM="+from+"&WHERE="+where;
            //acao pos API processada
            this.acao = acao;

            new RegistraDados().execute(url);
        }else{
            Menssagens.showToast("162 sem internet",context);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class RegistraDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String parametros = "";
            return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogBaixando = new ProgressDialog(context);
            dialogBaixando.setMessage("Atualizando dados...");
            dialogBaixando.setCancelable(false);
            dialogBaixando.show();
        }

        @Override
        protected void onPostExecute(String resultado) {

                dialogBaixando.dismiss();
            if (resultado == null) {
                Menssagens.showToast("157",context);
            } else if (resultado.contains("Conexao_falhou")) {
                Menssagens.showToast("158",context);
            } else {
                if(acao == 0)
                    ((VendaGasActivity)context).ProdutosDisponiveis(resultado);
                else if(acao == 1)
                    ((RegistradoraActivity)context).RecebendoResultados(resultado);
                else if(acao == 2)
                    ((SpashActivity)context).RecebendoEnderecos(resultado);
                else
                    Menssagens.showToast("159",context);
            }
        }
    }

}
