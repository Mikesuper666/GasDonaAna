package com.gasdonaana.gasdonaana.Helper;

import android.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BaseInfos {
    /**
     * @param infoBase string total para processar
     * @param separador separa os arrays conforme o conteudo
     * @param item Seleciona o item do array na posição 1,2 ou 3
     * @return retorna o array
     */
    public static ArrayList<String>InfoDescricao(String infoBase, int separador, int item) {

        String infoBaseRecebida[] = infoBase.split("__");

        final ArrayList<String> infoProcessada = new ArrayList<>();
        infoProcessada.add("nulo");
        for (int i = 0; i < infoBaseRecebida.length; ) {

            if (infoBaseRecebida[i].contains("^")) {
                break;
            } else {
                infoProcessada.add(infoBaseRecebida[i] + " " + infoBaseRecebida[i + item]);

                i += separador;
            }
        }

        return infoProcessada;
    }
}
