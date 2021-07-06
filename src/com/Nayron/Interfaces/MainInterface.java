/*Desenvolvido por: Nayron Moura;
* Github: https://github.com/noryaN1/;
*
* Código responsável por criar o design do Frame Principal.
* obs: para ver o design real, utulizando o InteliJ abra o MainFrame.form.
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
    private JPanel Content;
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
        Error.setWrapStyleWord(true);
        Error.setLineWrap(true);

        perfisContent.setBackground(new Color(94,151,174));
        ImageIcon icone = new ImageIcon("src/com/Nayron/iconeapp.png");

        this.setIconImage(icone.getImage());
    }
}
