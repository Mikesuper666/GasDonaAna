package com.gasdonaana.gasdonaana.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Preferencias {
    private Context contexto;
    private SharedPreferences sharedPreferences;
    private String NOME_ARQUIVO = "preferencia";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private String FUNCIONARIO_KEY = "funcionarios";
    private String BAIRROS = "bairros";

    public Preferencias(Context contextoParametro)
    {
        contexto = contextoParametro;
        sharedPreferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE);
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
        Set<String> set = new HashSet<>();
        set.addAll(lista);
        editor.putStringSet(BAIRROS, set);
        editor.apply();
        Log.d("storesharedPreferences",">>>>"+lista);
    }
}
