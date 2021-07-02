/*Programa de Criação de Tarefas
* Desenvolvido por: Nayron Moura
* Github: https://github.com/noryaN1/
*/
package com.Nayron.Main;
//dependencias
import com.Nayron.BancodeDados.BancodeDados;
import com.Nayron.Interfaces.CriarPerfis;
import com.Nayron.Interfaces.MainInterface;
//imports
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
    static Color corbackground = new Color(102, 153, 204);//cor padrão para background
    static BancodeDados banco = new BancodeDados();//Banco de dados SQlite
    public static PainelCriarTarefas criar = new PainelCriarTarefas();//Painel de criação.
    public static String NomedoPerfil ="";//nome do Perfil atual

    public static void main(String[] args)//Método Main
    {
        banco.conectar();
        //variáveis
        criar.setBorder(null);
        Paineldetarefas.setBorder(BorderFactory.createEmptyBorder());
        Paineldetarefas.setFocusable(false);
        Paineldetarefas.setBorder(BorderFactory.createEmptyBorder());
        cards.setBorder(BorderFactory.createEmptyBorder());
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
            int width = MainFrame.getSize().width/2;
            int height = MainFrame.getSize().height/2;
            perfil.setLocation(width,height);
            perfil.setVisible(true);
        });
    }

    public static void restauraTarefas()//Método responsável por restaurar as tarefas do banco de dados
    {
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

    public static void restaurarPerfis()//Método responsável por restaurar os perfis do banco de dados
    {
        ResultSet result = banco.Resgatar("SELECT DISTINCT Perfil FROM Tarefas");
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
        botao.setBackground(new Color(137, 207, 240));
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paineldetarefas.removeAll();
                if(perfil==NomedoPerfil) {
                MainFrame.criarNovoButton.setEnabled(false);
                MainFrame.paginaInicialButton.setEnabled(false);
                NomedoPerfil="";
                atualizarpainel(Paineldetarefas);
                }else{
                NomedoPerfil=perfil;
                    MainFrame.criarNovoButton.setEnabled(true);
                    MainFrame.paginaInicialButton.setEnabled(true);
                    restauraTarefas();
                }
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