package org.example;

public class Produto {
 private double preco;
 private String descricao;
public Produto() {
    this.preco = 0;
    this.descricao = "";
}

 public Produto(double preco, String descricao) {
     this.preco = preco;
     this.descricao = descricao;
 }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
