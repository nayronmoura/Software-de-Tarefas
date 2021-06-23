package com.Nayron.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCriarTarefas extends JPanel {
    public PainelCriarTarefas(){
        this.setLayout(new GridLayout(3,1));
        this.setBorder(null);
        JLabel label1 = new JLabel("Titulo da Atividade: ");
        JLabel label2 = new JLabel("Descrição da Atividade: ");

        JTextField titulo = new JTextField();
        JTextArea descricao = new JTextArea();
        JButton criarButtom = new JButton("Criar");

        JScrollPane scroll = new JScrollPane(descricao);
        JPanel painel1 = new JPanel(new GridLayout(2,0));
        JPanel painel2 = new JPanel(new GridLayout(2,0));
        JPanel painel3 = new JPanel(new FlowLayout());

        titulo.setFont(Font.getFont("Arial"));
        titulo.setDocument(new Limitador(20));
        titulo.setSize(400,200);

        descricao.setFont(Font.getFont("Arial"));
        descricao.setSize(200,100);
        descricao.setDocument(new Limitador(60));
        descricao.setWrapStyleWord(true);

        criarButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(titulo.getText().length()>=1 && descricao.getText().length()>=1){
                    criaratividade(titulo.getText(),descricao.getText());
                    titulo.setText("");
                    descricao.setText("");
                }
            }
        });
        criarButtom.setFocusable(false);
        criarButtom.setSize(40,60);

        painel1.add(label1);
        painel1.add(titulo);
        painel1.setBackground(new Color(1,1,1,0));
        painel1.setBorder(BorderFactory.createBevelBorder(1));

        painel2.add(label2);
        painel2.add(scroll);
        painel2.setBackground(new Color(1,1,1,0));
        painel2.setBorder(BorderFactory.createBevelBorder(1));

        painel3.add(criarButtom,JPanel.CENTER_ALIGNMENT);
        painel3.setBackground(new Color(1,1,1,0));

        this.add(painel1);
        this.add(painel2);
        this.add(painel3);
    }
    public void criaratividade(String titulo, String descricao){
        Main.constrains.fill= GridBagConstraints.BOTH;
        Main.constrains.gridwidth=7;
        Main.constrains.gridy+=3;
        Main.constrains.anchor=GridBagConstraints.PAGE_START;
        Instanciar painel = new Instanciar(titulo,descricao);
        painel.check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.Paineldetarefas.remove(painel);
                Main.Paineldetarefas.repaint();
                Main.Paineldetarefas.validate();
            }
        });
        Main.Paineldetarefas.add(painel,Main.constrains);
        Main.MudaPanel("tarefas");
    }
}
