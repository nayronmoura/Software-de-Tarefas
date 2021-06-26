package com.Nayron.Main;
//depenências
//java imports
import com.Nayron.BancodeDados.BancodeDados;
import com.Nayron.Interfaces.MainInterface;

import javax.swing.*;
import java.awt.*;

//Classe main
public class Main {
    //Váriaveis Estaticas
    static GridBagConstraints constrains = new GridBagConstraints();//grid para instanciar as atividades
    static MainInterface MainFrame = new MainInterface("Tarefas Milgrau");//Frame Principal
    static JPanel Paineldetarefas = new JPanel(new GridLayout(4,5));//Painel onde é mostrado as Atividadaes
    static JPanel cards = new JPanel(new CardLayout());//Painel que troca entre criar e mostrar as Atividades
    static Color corbackground=new Color(255,178,21);//cor padrão para background
    static BancodeDados banco = new BancodeDados();
    public static PainelCriarTarefas criar = new PainelCriarTarefas();

    public static void main(String[] args)
    {
        banco.conectar();
        //variáveis
        criar.setBorder(null);
        JScrollPane scrol=new JScrollPane(Paineldetarefas);

        actions();

        MainFrame.geralcontent.add(cards,BorderLayout.CENTER);
        Paineldetarefas.setBackground(corbackground);
        criar.setBackground(corbackground);

        cards.add(scrol,"tarefas");
        cards.add(criar,"criar");

        MudaPanel("tarefas");

        banco.Resgatar();

    }
    public static void actions()//actions listener dos botões do frame principal
    {
        MainFrame.criarNovoButton.addActionListener(e -> MudaPanel("criar"));
        MainFrame.paginaInicialButton.addActionListener(e -> MudaPanel("tarefas"));
    }

    public static void MudaPanel(String nome)//Método responsável por trocar os JPainel do cards
    {
        switch (nome){
            case "criar":
                MainFrame.criarNovoButton.setBorderPainted(false);
                MainFrame.paginaInicialButton.setBorderPainted(true);
                break;
            case "tarefas":
                MainFrame.paginaInicialButton.setBorderPainted(false);
                MainFrame.criarNovoButton.setBorderPainted(true);
        }

        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards,nome);
    }
}