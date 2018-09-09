package com.gasdonaana.gasdonaana.Models;

public class VendaModel {
    private int id;
    private int idProduto;
    private float preco;
    private String dataPedido;
    private String dataEntregue;
    private boolean cancelado;

    public VendaModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(String dataEntregue) {
        this.dataEntregue = dataEntregue;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
}
