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
    private JPanel buttonspainel;
    public JPanel geralcontent;

    public MainInterface(String Texto){
        super.setTitle(Texto);
        this.setContentPane(MainPainel);
        this.pack();
        this.setVisible(true);

        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        geralcontent.setSize(new Dimension(400,900));
        paginaInicialButton.setFocusable(false);
        criarNovoButton.setFocusable(false);
        geralcontent.setFocusable(false);
        ImageIcon icone = new ImageIcon("./icone.png");
        this.setIconImage(icone.getImage());
    }
}