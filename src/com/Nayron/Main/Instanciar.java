package com.Nayron.Main;

import javax.swing.*;
import java.awt.*;


public class Instanciar extends JPanel{
    public JButton check = new JButton();
    public JTextArea texto=new JTextArea();
    public Instanciar(String titulotext, String descricaotext){
        this.setLayout(new GridLayout(2,2));

        Color corContent= new Color(39,68,114);
        JPanel paineldetitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel paineldedescricao=new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextArea descricao=new JTextArea();

        this.setSize(400,100);
        this.setBackground(corContent);
        this.setBorder(BorderFactory.createBevelBorder(2));

        paineldetitulo.add(texto);
        JPanel p = new JPanel(new GridLayout(1,1));
        p.add(check,JPanel.LEFT_ALIGNMENT);
        p.setBackground(corContent);
        p.setFocusable(false);
        paineldetitulo.add(p);
        paineldetitulo.setBackground(corContent);
        paineldetitulo.setSize(50,100);

        paineldedescricao.add(descricao);
        paineldedescricao.setBackground(corContent);
        paineldedescricao.setSize(100,100);

        check.setBackground(corContent);
        check.setSize(10,10);
        ImageIcon icone = new ImageIcon("src/com/Nayron/icone-botaoexcluir.png");
        check.setIcon(icone);
        check.setFocusable(false);
        check.setBorderPainted(false);

        texto.setForeground(new Color(97, 252, 39));
        texto.setBackground(new Color(1,1,1,0));
        texto.setSize(100,50);
        texto.setWrapStyleWord(true);
        texto.setEditable(false);
        texto.setText(titulotext);

        descricao.setForeground(new Color(226, 220, 220));
        descricao.setBackground(new Color(1,1,1,0));
        descricao.setSize(100,200);
        descricao.setWrapStyleWord(true);
        descricao.setEditable(false);
        descricao.setLineWrap(true);
        descricao.setText("Descrição:\n"+descricaotext);
        descricao.setEnabled(false);

        this.add(paineldetitulo);
        this.add(p,JPanel.LEFT_ALIGNMENT);
        this.add(paineldedescricao);
        this.setBorder(BorderFactory.createBevelBorder(1));
    }
}
