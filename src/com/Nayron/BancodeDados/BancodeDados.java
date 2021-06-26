package com.Nayron.BancodeDados;
import com.Nayron.Main.Main;

import java.sql.*;

import com.Nayron.Main.Main;

public class BancodeDados {
    private Connection conexao;
    private Statement Statement;
    public void conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:.\\banco.db");
            Statement= conexao.createStatement();
        }catch (Exception e){e.printStackTrace();}
    }
    public void Resgatar(){
        String sql = "SELECT * FROM Tarefas;";
        try {
            ResultSet resultSet= Statement.executeQuery(sql);
            while (resultSet.next()){
                Main.criar.criaratividade(resultSet.getString("Titulo"),resultSet.getString("Descricao"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void EnviarDados(String titulo, String descricao){
        String sql = "INSERT INTO Tarefas (Titulo,Descricao)"+" values('"+titulo+"','"+descricao+"');";
        try{
            Statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }
    public void DeletarDados(String Titulo){
        String sql = "DELETE FROM Tarefas WHERE Titulo='"+Titulo+"';";
        try{
            Statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }
}
