package com.gasdonaana.gasdonaana.BancoDados;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.gasdonaana.gasdonaana.Helper.Menssagens;
import com.gasdonaana.gasdonaana.Models.RegistradoraModel;
import com.gasdonaana.gasdonaana.Models.TeleModel;
import com.gasdonaana.gasdonaana.VendaGasActivity;

public class BancoInsert {
    private Context context;
    private String url, parametros;
    private ProgressDialog dialogBaixando;
    private int tabela;
    public BancoInsert(Context context){
        this.context = context;
    }

    public void conectarAobanco(int tabela, RegistradoraModel registradoraModel, TeleModel teleModel){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            switch (tabela) {
                case 0:
                    //url = "http://www.onuse.com.br/db/ana_db/select/select.php?acao=0";
                    url = "http://192.168.0.102/da_gas/db/insert/insert.php?produto="+registradoraModel.getProduto()+"&vendedor=1"+"&datavenda="+registradoraModel.getData_venda()+"&horario_venda="+registradoraModel.getHorario_venda()+"&horario_entregue="+registradoraModel.getHorario_venda()+"&tele="+registradoraModel.getTele()+"&pagamento="+registradoraModel.getPagamento()+"&status="+registradoraModel.getStatus()+"&inteira="+registradoraModel.getInteira()+"&quantidade="+registradoraModel.getQuantidade()+"&total="+registradoraModel.getTotal()+"&endereco="+teleModel.getEndereco()+"&numero="+teleModel.getNumero()+"&bairro="+teleModel.getBairro()+"&entregador="+teleModel.getEntregador();
                    break;
            }
            this.tabela = tabela;
            parametros = "";

            new BancoInsert.RegistraDados().execute(url);
        }else{
            Menssagens.showToast("162 sem internet",context);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class RegistraDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogBaixando = new ProgressDialog(context);
            dialogBaixando.setMessage("Inserindo dados...");
            dialogBaixando.setCancelable(false);
            dialogBaixando.show();
        }

        @Override
        protected void onPostExecute(String resultado) {

            dialogBaixando.dismiss();
            if (resultado == null) {
                Menssagens.showToast("157",context);
            } else if (resultado.contains("nulo_produto")) {
                Menssagens.showToast("*********",context);
            } else {
                if(tabela == 0){
                    ((VendaGasActivity)context).FinalizarVenda();
                }else{
                    Menssagens.showToast("159",context);
                }
            }
        }
    }
}
