package com.gasdonaana.gasdonaana.Models;

public class TeleModel {
    private int id_venda;
    private int numero;
    private String[] bairro;
    private String[] entregador;
    private String[] endereco;

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

    public String[] getEntregador() {
        return entregador;
    }

    public void setEntregador(String[] entregador) {
        this.entregador = entregador;
    }

    public String[] getEndereco() {
        return endereco;
    }

    public void setEndereco(String[] endereco) {
        this.endereco = endereco;
    }

    public String[] getBairro() {
        return bairro;
    }

    public void setBairro(String[] bairro) {
        this.bairro = bairro;
    }
}
