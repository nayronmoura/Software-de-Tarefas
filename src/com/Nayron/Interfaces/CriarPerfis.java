/*Desenvolvido por: Nayron Moura;
 * Github: https://github.com/noryaN1/;
 *
 * Código responsável criar um JFrame que cria os perfis;
 */

package com.Nayron.Interfaces;

import com.Nayron.Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarPerfis extends JFrame {
    private JPanel Content;
    private JTextField textField1;
    private JButton criar;
    public CriarPerfis(){
        super.setTitle("Criando Tarefas");
        this.setContentPane(Content);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        criar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.CriarPerfil(textField1.getText());
                dispose();
            }
        });
    }
}
