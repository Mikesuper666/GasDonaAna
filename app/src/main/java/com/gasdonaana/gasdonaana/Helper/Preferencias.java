package com.gasdonaana.gasdonaana.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String FUNCIONARIO_KEY = "funcionarios";
    private String ENDERECOS = "enderecos";
    private String BAIRROS = "bairros";

    private String NOME_ARQUIVO = "preferencia";
    private int MODE = 0;

    public Preferencias(Context contextoParametro)
    {

        sharedPreferences = contextoParametro.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = sharedPreferences.edit();
    }

    public void setFuncionarios(String funcionario){
        editor.putString(FUNCIONARIO_KEY, funcionario);
        editor.commit();
    }

    public void setEnderecos(String lista){
        editor.putString(ENDERECOS, lista);
        editor.commit();
    }

    public void setBairros(String lista){
        editor.putString(BAIRROS, lista);
        editor.commit();
    }

    public String getEderecos() { return sharedPreferences.getString(ENDERECOS, null); }
    public String getBairros() { return sharedPreferences.getString(BAIRROS, null); }
    public String getFuncionario()
    {
        return sharedPreferences.getString(FUNCIONARIO_KEY, null);
    }
}
