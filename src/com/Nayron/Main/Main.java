package com.Nayron.Main;
//depenências
//java imports

import com.Nayron.BancodeDados.BancodeDados;
import com.Nayron.Interfaces.CriarPerfis;
import com.Nayron.Interfaces.MainInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

//Classe main
public class Main {
    //Váriaveis Estaticas
    static GridBagConstraints constrains = new GridBagConstraints();//grid para instanciar as atividades
    static MainInterface MainFrame = new MainInterface("Tarefas Milgrau");//Frame Principal
    public static JPanel Paineldetarefas = new JPanel(new GridLayout(4, 5));//Painel onde é mostrado as Atividadaes
    static JPanel cards = new JPanel(new CardLayout());//Painel que troca entre criar e mostrar as Atividades
    static Color corbackground = new Color(255, 178, 21);//cor padrão para background
    static BancodeDados banco = new BancodeDados();
    public static PainelCriarTarefas criar = new PainelCriarTarefas();
    public static String NomedoPerfil ="";

    public static void main(String[] args) {
        banco.conectar();
        //variáveis
        criar.setBorder(null);
        Paineldetarefas.setBorder(BorderFactory.createEmptyBorder());
        Paineldetarefas.setFocusable(false);
        cards.setBorder(null);
        JScrollPane scrol = new JScrollPane(Paineldetarefas);

        actions();

        MainFrame.geralcontent.add(cards, BorderLayout.CENTER);
        Paineldetarefas.setBackground(corbackground);
        criar.setBackground(corbackground);

        cards.add(scrol, "tarefas");
        cards.add(criar, "criar");

        MudaPanel("tarefas");
        restaurarPerfis();

    }

    public static void actions()//actions listener dos botões do frame principal
    {
        MainFrame.criarNovoButton.addActionListener(e -> MudaPanel("criar"));
        MainFrame.paginaInicialButton.addActionListener(e -> MudaPanel("tarefas"));
        MainFrame.CriarPerfil.addActionListener(e->{
            CriarPerfis perfil = new CriarPerfis();
            perfil.setVisible(true);
        });
    }

    public static void restauraTarefas() {
        ResultSet result = banco.Resgatar("SELECT * FROM Tarefas " +
                "WHERE Perfil='"+ NomedoPerfil +"';");
        try {
            while (result.next()) {
                criar.criaratividade(result.getString("Titulo"), result.getString("Descricao"));
                atualizarpainel(Paineldetarefas);
            }
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
    }
    public static void restaurarPerfis(){
        ResultSet result = banco.Resgatar("SELECT Perfil FROM Tarefas");
        try {
            while (result.next()) {
                String perfil = result.getString("Perfil");
                CriarPerfil(perfil);
            }
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
    }

    public static void MudaPanel(String nome)//Método responsável por trocar os JPainel do cards
    {
        switch (nome) {
            case "criar":
                MainFrame.criarNovoButton.setBorderPainted(false);
                MainFrame.paginaInicialButton.setBorderPainted(true);
                break;
            case "tarefas":
                MainFrame.paginaInicialButton.setBorderPainted(false);
                MainFrame.criarNovoButton.setBorderPainted(true);
        }

        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, nome);
    }

    public static void CriarPerfil(String perfil){
        JButton botao = new JButton(perfil);
        botao.setBackground(corbackground);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NomedoPerfil=perfil;
                restauraTarefas();
            }
        });
        MainFrame.perfisContent.add(botao);
        atualizarpainel(MainFrame.perfisContent);
    }

    public static void atualizarpainel(Component c){
        c.repaint();
        c.validate();
    }
}