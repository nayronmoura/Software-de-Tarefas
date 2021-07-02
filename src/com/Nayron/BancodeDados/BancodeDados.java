package com.Nayron.BancodeDados;
import com.Nayron.Main.Main;

import java.sql.*;

public class BancodeDados {
    private Connection conexao;
    private Statement Statement;

    public void conectar()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:src/com/Nayron/BancodeDados/banco.db");
            Statement= conexao.createStatement();
        }catch (Exception e){e.printStackTrace();}
    }

    public ResultSet Resgatar(String sql)
    {
        try {
            ResultSet resultSet= Statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void EnviarDados(String titulo, String descricao)
    {
        String sql = "INSERT INTO Tarefas (Perfil,Titulo,Descricao)"+" values('"+ Main.NomedoPerfil+"','"+titulo+"','"+descricao+"');";
        try{
            Statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }

    public void DeletarDados(String Titulo)
    {
        String sql = "DELETE FROM Tarefas WHERE Titulo='"+Titulo+"';";
        try{
            Statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }
}
