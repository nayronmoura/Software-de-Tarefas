/*Programa de Gerenciamento de Tarefas
 * Desenvolvido por: Nayron Moura;
 * Github: https://github.com/noryaN1/;
 *
 * Descrição dos codigos:
 * Main- Codigo principal;
 * PainelCriarTarefas- Cria o JPanel de criar atividades;
 * Limitador- Documento que limita a quantidade de palavras de um JTextArea ou JTextfield
 * Instanciar- Código responsável por criar um JPanel das tarefas que são espostas no JPanel Paineldetarefas;
 *
 * Criar Perfis- Código de um JFrame que cria os Perfis;
 * Criarperfis.form- Formulário do InteliJ para interface;
 * MainInterface - JFrame com todo o design do MainFrame;
 * MainInterface.form - Formulário do InteliJ para interface;
 *
 * BancodeDados - Código responsável por todo o manejamento do banco de dados;
 * banco.db - banco de dados SQLITE
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

//Class main
public class Main {
    //Váriaveis Estaticas
    static GridBagConstraints constrains = new GridBagConstraints();//grid para instanciar as atividades
    static MainInterface MainFrame = new MainInterface("Controle suas Tarefas");//Frame Principal
    public static JPanel Paineldetarefas = new JPanel(new GridLayout(4, 5));//Painel onde é mostrado as Atividadaes
    static JPanel cards = new JPanel(new CardLayout());//Painel que troca entre criar e mostrar as Atividades
    static Color corbackground = new Color(102, 153, 204);//cor padrão para background
    static BancodeDados banco = new BancodeDados();//Banco de dados SQlite
    public static PainelCriarTarefas criar = new PainelCriarTarefas();//Painel de criação.
    public static String NomedoPerfil = "";//nome do Perfil atual

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

    public static void actions()//actions listener dos botões do frame principal.
    {
        MainFrame.criarNovoButton.addActionListener(e -> MudaPanel("criar"));
        MainFrame.paginaInicialButton.addActionListener(e -> MudaPanel("tarefas"));
        MainFrame.CriarPerfil.addActionListener(e -> {
            if (MainFrame.perfisContent.getComponentCount() < 3) {
                CriarPerfis perfil = new CriarPerfis();
                int width = MainFrame.getSize().width / 2;
                int height = MainFrame.getSize().height / 2;
                perfil.setLocation(width, height);
                perfil.setVisible(true);
            } else {
                MainFrame.Error.setText("Você completou a quantidade Máxima de Perfis" +
                        " Conclua antes de criar mais.");

            }
        });
    }

    public static void restauraTarefas()//Método responsável por restaurar as tarefas do banco de dados.
    {
        ResultSet result = banco.Resgatar("SELECT * FROM Tarefas " +
                "WHERE Perfil='" + NomedoPerfil + "';");
        try {
            while (result.next()) {
                criar.criaratividade(result.getString("Titulo"), result.getString("Descricao"));
                atualizarpainel(Paineldetarefas);
            }
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
    }

    public static void restaurarPerfis()//Método responsável por restaurar os perfis do banco de dados.
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

    public static void MudaPanel(String nome)//Método responsável por trocar os JPainel do cards.
    {
        switch (nome) {
            case "criar":
                MainFrame.criarNovoButton.setBorderPainted(false);
                MainFrame.paginaInicialButton.setBorderPainted(true);
                break;
            case "tarefas":
                MainFrame.paginaInicialButton.setBorderPainted(false);
                MainFrame.criarNovoButton.setBorderPainted(true);
                break;
            case "tarefa-zoom":
                MainFrame.paginaInicialButton.setBorderPainted(false);
                MainFrame.criarNovoButton.setBorderPainted(false);
                break;
        }

        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, nome);
    }

    public static void CriarPerfil(String perfil)//Método responsável por criar os perfis e adicionalos ao JPanel.
    {
        JPanel painel = new JPanel(new FlowLayout());
        JButton botao = new JButton(perfil);
        JButton botaoExcluir = new JButton();

        botao.setBackground(new Color(137, 207, 240));
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (perfil == NomedoPerfil) {
                    Paineldetarefas.removeAll();
                    MainFrame.criarNovoButton.setEnabled(false);
                    MainFrame.paginaInicialButton.setEnabled(false);
                    NomedoPerfil = "";
                    MudaPanel("tarefas");
                    atualizarpainel(Paineldetarefas);
                } else {
                    Paineldetarefas.removeAll();
                    NomedoPerfil = perfil;
                    MainFrame.criarNovoButton.setEnabled(true);
                    MainFrame.paginaInicialButton.setEnabled(true);
                    MudaPanel("tarefas");
                    restauraTarefas();
                }
            }
        });
        botao.setFocusable(false);
        botao.setBorderPainted(false);
        botao.setBackground(new Color(255, 255, 255));
        botao.setFocusable(false);
        botao.setSize(new Dimension(Integer.MAX_VALUE, painel.getMinimumSize().height));
        botao.setBackground(new Color(255, 255, 255));

        botaoExcluir.setIcon(new ImageIcon("src/com/Nayron/icone-botaomarl.png"));
        botaoExcluir.setBorderPainted(false);
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.perfisContent.remove(painel);
                atualizarpainel(MainFrame.perfisContent);
            }
        });
        botaoExcluir.setBackground(new Color(255, 255, 255));
        botaoExcluir.setFocusable(false);
        botaoExcluir.setSize(new Dimension(Integer.MAX_VALUE, painel.getMinimumSize().height));

        painel.setBackground(new Color(255, 255, 255));
        painel.add(botao);
        painel.add(botaoExcluir);
        painel.setSize(new Dimension(Integer.MAX_VALUE, painel.getMinimumSize().height));

        MainFrame.perfisContent.add(painel);
        atualizarpainel(MainFrame.perfisContent);
    }

    public static void atualizarpainel(Component c)//Método responsável por atualizar o painel desejado a cada alteração.
    {
        c.repaint();
        c.validate();
    }
}