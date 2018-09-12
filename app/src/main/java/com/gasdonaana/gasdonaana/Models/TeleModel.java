package com.gasdonaana.gasdonaana.Models;

public class TeleModel {
    private int id_venda;
    private int endereco;
    private int numero;
    private int bairro;
    private int entregador;
    private String entregadorDescicao;

    public TeleModel(){}

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEntregador() {
        return entregador;
    }

    public void setEntregador(int entregador) {
        this.entregador = entregador;
    }

    public String getEntregadorDescicao() {
        return entregadorDescicao;
    }

    public void setEntregadorDescicao(String entregadorDescicao) {
        this.entregadorDescicao = entregadorDescicao;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public int getBairro() {
        return bairro;
    }

    public void setBairro(int bairro) {
        this.bairro = bairro;
    }
}
