package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaGUI {

    public SistemaGUI() {

        Prateleira prateleira = new Prateleira();

        // Cria o JFrame principal
        JFrame frame = new JFrame("Sistema de Cadastro, Busca e Exclusão");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Layout manual



        // ------------------- Bloco de Cadastro ------------------- //
        JPanel cadastroPanel = new JPanel();
        cadastroPanel.setBounds(10, 10, 360, 120);  // Posição e tamanho do painel
        cadastroPanel.setBorder(BorderFactory.createTitledBorder("Cadastro"));
        cadastroPanel.setLayout(null);  // Layout manual para o painel

        // Input de Preço (Cadastro)
        JLabel precoLabel = new JLabel("Preço:");
        precoLabel.setBounds(10, 20, 100, 25);
        cadastroPanel.add(precoLabel);

        JTextField precoField = new JTextField();
        precoField.setBounds(120, 20, 200, 25);
        cadastroPanel.add(precoField);

        // Input de Descrição (Cadastro)
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(10, 50, 100, 25);
        cadastroPanel.add(descricaoLabel);

        JTextField descricaoField = new JTextField();
        descricaoField.setBounds(120, 50, 200, 25);
        cadastroPanel.add(descricaoField);

        // Botão de Cadastrar
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(120, 110, 100, 25);
        cadastroPanel.add(cadastrarButton);

        frame.add(cadastroPanel); // Adiciona o painel de cadastro ao JFrame

        // ------------------- Bloco de Busca ------------------- //
        JPanel buscaPanel = new JPanel();
        buscaPanel.setBounds(10, 140, 360, 80);  // Posição e tamanho do painel
        buscaPanel.setBorder(BorderFactory.createTitledBorder("Busca"));
        buscaPanel.setLayout(null);  // Layout manual para o painel

        // Input de Descrição (Busca)
        JLabel buscaDescricaoLabel = new JLabel("Descrição:");
        buscaDescricaoLabel.setBounds(10, 20, 100, 25);
        buscaPanel.add(buscaDescricaoLabel);

        JTextField buscaDescricaoField = new JTextField();
        buscaDescricaoField.setBounds(120, 20, 200, 25);
        buscaPanel.add(buscaDescricaoField);

        // Botão de Buscar
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(120, 50, 100, 25);
        buscaPanel.add(buscarButton);

        frame.add(buscaPanel); // Adiciona o painel de busca ao JFrame

        // ------------------- Bloco de Exclusão ------------------- //
        JPanel exclusaoPanel = new JPanel();
        exclusaoPanel.setBounds(10, 230, 360, 120);  // Posição e tamanho do painel
        exclusaoPanel.setBorder(BorderFactory.createTitledBorder("Exclusão"));
        exclusaoPanel.setLayout(null);  // Layout manual para o painel

        // Input de Descrição (Exclusão)
        JLabel exclusaoDescricaoLabel = new JLabel("Descrição:");
        exclusaoDescricaoLabel.setBounds(10, 50, 100, 25);
        exclusaoPanel.add(exclusaoDescricaoLabel);

        JTextField exclusaoDescricaoField = new JTextField();
        exclusaoDescricaoField.setBounds(120, 50, 200, 25);
        exclusaoPanel.add(exclusaoDescricaoField);


        // Botão de Excluir
        JButton excluirButton = new JButton("Excluir");
        excluirButton.setBounds(120, 110, 100, 25);
        exclusaoPanel.add(excluirButton);

        frame.add(exclusaoPanel); // Adiciona o painel de exclusão ao JFrame

        // Torna o JFrame visível
        frame.setVisible(true);

        // Ações dos botões
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ação para cadastrar os valores
                double preco = Double.parseDouble(precoField.getText());
                String descricao = descricaoField.getText();


                Produto produto = new Produto(preco, descricao);
                try {
                    prateleira.cadastraProduto(produto);
                } catch (produtoJaExisteException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                JOptionPane.showMessageDialog(frame, "Cadastrado: \nPreço: " + preco + "\nDescrição: " + descricao);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ação para buscar pelo valor de descrição
                String descricaoBusca = buscaDescricaoField.getText();


                JOptionPane.showMessageDialog(frame, "Buscando por: \nDescrição: " + descricaoBusca +"\n"+ "Existe o produto: "+prateleira.existeProduto(descricaoBusca));

            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String descricao = exclusaoDescricaoField.getText();


                try {
                    prateleira.removeProduto(descricao);
                } catch (produtoNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }

                JOptionPane.showMessageDialog(frame, "Excluído: "+ "\nDescrição: " + descricao);
            }
        });
    }
}