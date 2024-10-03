package org.example;

import org.Exceptions.produtoJaExisteException;
import org.Exceptions.produtoNaoEncontradoException;
import org.Exceptions.produtoNaoExisteException;

import java.util.HashMap;

public class Prateleira {
    private HashMap<String, Produto> produtosPrateleiras = new HashMap<String, Produto>();

    public Prateleira() {
    }

    public void cadastraProduto(Produto produto) throws produtoJaExisteException {
        for (Produto p : produtosPrateleiras.values()) {
            if (p.equals(produto)) {
                throw new produtoJaExisteException("Produto já cadastrado!");
            }
        }
        this.produtosPrateleiras.put(produto.getDescricao(), produto);
    }

    public void removeProduto(String descricao) throws produtoNaoEncontradoException {
    if(existeProduto(descricao)) {
        this.produtosPrateleiras.remove(descricao);
    }else{
        throw new produtoNaoEncontradoException("Não foi encontrado o produto com o descricao: " + descricao);
    }

    }

    public boolean existeProduto(String descricao) {
        if (this.produtosPrateleiras.containsKey(descricao)) {
            return true;
        }
        return false;
    }

    public String getProdutoEspcifico(String descricao)throws produtoNaoExisteException {
        if (existeProduto(descricao)) {
            for (Produto p : this.produtosPrateleiras.values()) {
                if (p.getDescricao().equals(descricao)) {
                    return "descrição: " + p.getDescricao()+"\n"+"preço: " + p.getPreco();
                }
            }
        }
           throw new produtoNaoExisteException("Esse descrição de produto não existe");
    }

}
