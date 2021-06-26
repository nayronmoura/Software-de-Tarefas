package com.Nayron.Main;

import javax.swing.*;
import java.awt.*;


public class Instanciar extends JPanel{
    public JCheckBox check = new JCheckBox();
    public JTextArea texto=new JTextArea();
    public Instanciar(String titulotext, String descricaotext){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        Color corContent= new Color(30, 33, 43);
        JPanel paineldetitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel paineldedescricao=new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextArea descricao=new JTextArea();

        this.setSize(400,100);
        this.setBackground(corContent);
        this.setBorder(BorderFactory.createBevelBorder(2));

        paineldetitulo.add(texto);
        paineldetitulo.add(check);
        paineldetitulo.setBackground(corContent);
        paineldetitulo.setSize(50,100);

        paineldedescricao.add(descricao);
        paineldedescricao.setBackground(corContent);
        paineldedescricao.setSize(100,100);

        check.setBackground(corContent);
        check.setBounds(0,0,30,30);

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
        this.setBackground(new Color(1,1,1,0.5f));

        this.add(paineldetitulo);
        this.add(paineldedescricao);
        this.setBorder(BorderFactory.createBevelBorder(1));
    }
}
