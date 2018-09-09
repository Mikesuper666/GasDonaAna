package com.example.maico.donaanaapp.Models;

public class RegistradoraModel {
    //referente a tabela "vendas" do banco de dados ds_gas
    private String id;
    private int produto;
    private String produtoDescricao;
    private String vendedor;
    private String data_venda;
    private String horario_venda;
    private String horario_entregue;
    private int tele;
    private int pagamento;
    private int status;
    private int inteira;
    private int quantidade;
    private String total;

    public RegistradoraModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public String getHorario_venda() {
        return horario_venda;
    }

    public void setHorario_venda(String horario_venda) {
        this.horario_venda = horario_venda;
    }

    public String getHorario_entregue() {
        return horario_entregue;
    }

    public void setHorario_entregue(String horario_entregue) {
        this.horario_entregue = horario_entregue;
    }

    public int getTele() {
        return tele;
    }

    public void setTele(int tele) {
        this.tele = tele;
    }

    public int getPagamento() {
        return pagamento;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getInteira() {
        return inteira;
    }

    public void setInteira(int inteira) {
        this.inteira = inteira;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
