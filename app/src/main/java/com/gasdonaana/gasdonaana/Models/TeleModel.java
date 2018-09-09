package com.example.maico.donaanaapp.Models;

public class TeleModel {
    private int id_venda;
    private String endereco;
    private int numero;
    private String bairro;
    private int entregador;
    private String entregadorDescicao;

    public TeleModel(){}

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
}
