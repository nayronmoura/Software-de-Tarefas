/*
* Main Frame da aplicação
*/

package com.Nayron.Interfaces;

import javax.swing.*;
import java.awt.*;

public class MainInterface extends JFrame {
    public JPanel MainPainel;
    public JButton paginaInicialButton;
    public JButton criarNovoButton;
    public JPanel geralcontent;
    public JButton CriarPerfil;
    private JScrollPane Scroll;
    public JTextArea Error;
    private JPanel IconePeris;
    public JPanel perfisContent= new JPanel();

    public MainInterface(String Texto){
        super.setTitle(Texto);
        this.setContentPane(MainPainel);
        this.pack();
        this.setVisible(true);

        this.setMinimumSize(new Dimension(400,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new BoxLayout(perfisContent,BoxLayout.PAGE_AXIS);

        geralcontent.setSize(new Dimension(400,900));
        paginaInicialButton.setFocusable(false);
        criarNovoButton.setFocusable(false);
        geralcontent.setFocusable(false);

        CriarPerfil.setFocusable(false);
        CriarPerfil.setBorderPainted(false);
        criarNovoButton.setEnabled(false);

        paginaInicialButton.setEnabled(false);
        Scroll.setViewportView(perfisContent);
        Scroll.setBorder(BorderFactory.createEmptyBorder());

        perfisContent.setBackground(new Color(101,172,182));
        ImageIcon icone = new ImageIcon("src/com/Nayron/icone-botaomarl.png");

        this.setIconImage(icone.getImage());
    }
}
