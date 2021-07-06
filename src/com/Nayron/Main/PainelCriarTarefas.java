/*Desenvolvido por: Nayron Moura;
 * Github: https://github.com/noryaN1/;
 *
 * Código responsável por criar as atividades e mostralás no JPanel principal;
 */

package com.Nayron.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCriarTarefas extends JPanel {
    JTextArea AreaErro = new JTextArea();
    JButton criarButtom = new JButton("Criar");
    JTextField titulo = new JTextField();
    JTextArea descricao = new JTextArea();

    public PainelCriarTarefas()//Configuração do JPanel
    {
        this.setLayout(new GridLayout(3, 1));
        this.setBorder(null);

        actions();

        JScrollPane scroll = new JScrollPane(descricao);
        JPanel painel1 = new JPanel(new GridLayout(2, 0));
        JPanel painel2 = new JPanel(new GridLayout(2, 0));
        JPanel painel3 = new JPanel(new FlowLayout());

        titulo.setFont(Font.getFont("Arial"));
        titulo.setDocument(new Limitador(50));
        titulo.setSize(400, 200);

        descricao.setFont(Font.getFont("Arial"));
        descricao.setSize(200, 100);
        descricao.setDocument(new Limitador(200));
        descricao.setWrapStyleWord(true);
        criarButtom.setFocusable(false);
        criarButtom.setSize(40, 60);

        AreaErro.setBackground(new Color(1, 1, 1, 0));
        AreaErro.enableInputMethods(false);
        AreaErro.setLineWrap(true);

        painel1.add(new JLabel("Titulo da Atividade:"));
        painel1.add(titulo);
        painel1.setBackground(new Color(1, 1, 1, 0));
        painel1.setBorder(BorderFactory.createBevelBorder(1));

        painel2.add(new JLabel("Descrição da Atividade: "));
        painel2.add(scroll);
        painel2.setBackground(new Color(1, 1, 1, 0));
        painel2.setBorder(BorderFactory.createBevelBorder(1));

        painel3.add(criarButtom, JPanel.CENTER_ALIGNMENT);
        //painel3.add(AreaErro);
        painel3.setBackground(new Color(1, 1, 1, 0));

        this.add(painel1);
        this.add(painel2);
        this.add(painel3);
    }

    public void criaratividade(String titulo, String descricao)//Responsável por criar o JPanel da tarefa
    {
        Main.constrains.fill = GridBagConstraints.BOTH;
        Main.constrains.gridwidth = 7;
        Main.constrains.gridy += 3;
        Main.constrains.anchor = GridBagConstraints.PAGE_START;
        Instanciar painel = new Instanciar(titulo, descricao);
        painel.check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.Paineldetarefas.getComponentCount() <= 20) {
                    Main.Paineldetarefas.remove(painel);
                    Main.Paineldetarefas.repaint();
                    Main.Paineldetarefas.validate();
                    Main.banco.DeletarDados(painel.texto.getText());
                } else {
                    Main.MainFrame.Error.setText("Conclua as tarefas antes de criar mais.");
                }
            }
        });
        Main.Paineldetarefas.add(painel, Main.constrains);
        Main.MudaPanel("tarefas");
    }

    public void Error(String ERRO)//Menssagem de erro
    {
        AreaErro.setText(ERRO);
    }

    public void actions()//Action dos botões
    {
        criarButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titulo.getText().length() >= 1 && descricao.getText().length() >= 1) {
                    Component[] comp = Main.Paineldetarefas.getComponents();
                    if (comp.length <= 19) {
                        if (titulo.getText() != "" && descricao.getText() != "") {
                            criaratividade(titulo.getText(), descricao.getText());
                            Main.banco.EnviarDados(titulo.getText(), descricao.getText());
                            titulo.setText("");
                            descricao.setText("");
                            Error("");
                        } else {
                            Error("você atingiu o número máximo de atividades,\n conclua antes de adicionar mais!!");
                        }
                    }
                } else if (titulo.getText() == "") {
                    Error("Insira o Titulo.");
                } else if (descricao.getText() == "") {
                    Error("Insira a Descrição.");
                } else {
                    Error("Insira as Informações.");
                }
            }
        });
    }
}
