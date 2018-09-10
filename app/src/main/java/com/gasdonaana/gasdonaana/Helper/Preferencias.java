package com.gasdonaana.gasdonaana.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Preferencias {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String FUNCIONARIO_KEY = "funcionarios";
    private String BAIRROS = "bairros";

    @SuppressLint("CommitPrefEdits")
    public Preferencias(Context contextoParametro)
    {
        String NOME_ARQUIVO = "preferencia";
        int MODE = 0;
        sharedPreferences = contextoParametro.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = sharedPreferences.edit();
    }

    public void setFuncionario(String funcionario){
        editor.putString(FUNCIONARIO_KEY, funcionario);
        editor.commit();
    }

    public String getFuncionario()
    {
        return sharedPreferences.getString(FUNCIONARIO_KEY, null);
    }


    public void SetarLista(List<String> lista){
        Set<String> set = new HashSet<>(lista);
        editor.putStringSet(BAIRROS, set);
        editor.apply();
    }

    public List<String> getLista() {
        Set<String> set = sharedPreferences.getStringSet(BAIRROS, null);
        ArrayList<String> arrPackage = new ArrayList<>(set);
        return arrPackage;
    }//Função teste para adquir dados passados por banco de dados
}
