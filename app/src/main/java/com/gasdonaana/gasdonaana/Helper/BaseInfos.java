package com.gasdonaana.gasdonaana.Helper;

import java.util.ArrayList;

public class BaseInfos {
    /**
     * @param infoBase string total para processar
     * @param separador separa os arrays conforme o conteudo
     * @param item Seleciona o item do array na posição 1,2 ou 3
     * @param dois verdadeiro retorna 2 posicao falso retorna somente uma
     * @param endereco Parâmetro titulo, oque será mostrado no topo do spinner
     * @return retorna o array
     */
    public static ArrayList<String>InfoDescricao(String infoBase, int separador, int item, Boolean dois, String endereco) {

        String infoBaseRecebida[] = infoBase.split("__");

        final ArrayList<String> infoProcessada = new ArrayList<>();
        infoProcessada.add(endereco);
        for (int i = 0; i < infoBaseRecebida.length; ) {

            if (infoBaseRecebida[i].contains("^")) {
                break;
            } else {
                infoProcessada.add((dois)? infoBaseRecebida[i + item] + " " + infoBaseRecebida[(i + item + 1)] : infoBaseRecebida[i+item]);
                i += separador;
            }
        }
        return infoProcessada;
    }
}
