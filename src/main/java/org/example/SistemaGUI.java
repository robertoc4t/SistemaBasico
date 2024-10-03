package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaGUI {

    public SistemaGUI() {

        // Cria o JFrame principal
        JFrame frame = new JFrame("Sistema básico");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bloco de Cadastro
        JPanel cadastroPanel = new JPanel();
        cadastroPanel.setPreferredSize(new Dimension(360, 140));
        cadastroPanel.setBorder(BorderFactory.createTitledBorder("Cadastro"));
        cadastroPanel.setLayout(null);  // Layout manual para o painel

        // preço
        JLabel precoLabel = new JLabel("Preço:");
        precoLabel.setBounds(10, 20, 100, 25);
        cadastroPanel.add(precoLabel);

        JTextField precoField = new JTextField();
        precoField.setBounds(120, 20, 200, 25);
        cadastroPanel.add(precoField);

        // descrição
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(10, 50, 100, 25);
        cadastroPanel.add(descricaoLabel);

        JTextField descricaoField = new JTextField();
        descricaoField.setBounds(120, 50, 200, 25);
        cadastroPanel.add(descricaoField);

        // Botão Cadastrar
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(120, 90, 100, 25);
        cadastroPanel.add(botaoCadastrar);

        // Bloco de Busca
        JPanel buscaPanel = new JPanel();
        buscaPanel.setPreferredSize(new Dimension(360, 100));
        buscaPanel.setBorder(BorderFactory.createTitledBorder("Busca"));
        buscaPanel.setLayout(null);

        JLabel buscaDescricaoLabel = new JLabel("Descrição:");
        buscaDescricaoLabel.setBounds(10, 20, 100, 25);
        buscaPanel.add(buscaDescricaoLabel);

        JTextField buscaDescricaoField = new JTextField();
        buscaDescricaoField.setBounds(120, 20, 200, 25);
        buscaPanel.add(buscaDescricaoField);

        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.setBounds(120, 60, 100, 25);
        buscaPanel.add(botaoBuscar);

        // Bloco de Exclusão
        JPanel exclusaoPanel = new JPanel();
        exclusaoPanel.setPreferredSize(new Dimension(360, 140));
        exclusaoPanel.setBorder(BorderFactory.createTitledBorder("Exclusão"));
        exclusaoPanel.setLayout(null);

        JLabel exclusaoDescricaoLabel = new JLabel("Descrição:");
        exclusaoDescricaoLabel.setBounds(10, 20, 100, 25);
        exclusaoPanel.add(exclusaoDescricaoLabel);

        JTextField exclusaoDescricaoField = new JTextField();
        exclusaoDescricaoField.setBounds(120, 20, 200, 25);
        exclusaoPanel.add(exclusaoDescricaoField);

        JButton BotaoExcluir = new JButton("Excluir");
        BotaoExcluir.setBounds(120, 60, 100, 25);
        exclusaoPanel.add(BotaoExcluir);

        // Painel centralizado que engloba os outros três painéis
        JPanel painelCentral = new JPanel();
        painelCentral.setBorder(BorderFactory.createTitledBorder("Painel Central"));
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS)); // Layout em coluna
        painelCentral.add(cadastroPanel);
        painelCentral.add(buscaPanel);
        painelCentral.add(exclusaoPanel);

        // Centralizar o painelCentral no JFrame
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        container.add(painelCentral); // Adiciona o painelCentral ao centro do container

        frame.add(container); // Adiciona o container ao frame
        frame.setVisible(true);


        ImageIcon icone = new ImageIcon("src/main/java/org/Img/Logo.png"); // Substitua pelo caminho correto
        frame.setIconImage(icone.getImage());


        Prateleira prateleira = new Prateleira();
        Produto p = new Produto();

        // Ações botões
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Ação cadastrar os valores
                double preco = Double.parseDouble(precoField.getText());
                String descricao = descricaoField.getText();

                p.setDescricao(descricao);
                p.setPreco(preco);

                try {
                    prateleira.cadastraProduto(p);
                } catch (produtoJaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                JOptionPane.showMessageDialog(frame, "Cadastrado: \nPreço: " + preco + "\nDescrição: " + descricao);
            }
        });


        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Ação buscar pelo valor de descrição
                String descricaoBusca = buscaDescricaoField.getText();

                try {
                    JOptionPane.showMessageDialog(frame, "Buscando por: \nDescrição: " + descricaoBusca + "\n" +
                            "O produto: " + prateleira.getProdutoEspcifico(descricaoBusca));
                } catch (produtoNaoExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        BotaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = exclusaoDescricaoField.getText();

                try {
                    prateleira.removeProduto(descricao);
                    JOptionPane.showMessageDialog(null, "Produto: "+descricao+" Excluído");
                } catch (produtoNaoEncontradoException ex) {
                    JOptionPane.showMessageDialog( frame, new RuntimeException(ex));
                }


            }
        });
    }
}
