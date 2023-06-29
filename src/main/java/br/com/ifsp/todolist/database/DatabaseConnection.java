package br.com.ifsp.todolist.database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static final String serverName = "localhost";
    static final String dataBasePort = "3306";
    static final String myDataBase = "todo";
    static final String url = "jdbc:mysql://" + serverName + ":" + dataBasePort + "/" + myDataBase ;
    static final String username = "root";
    static final String password = "";

    private static Connection conexao = null;

    private DatabaseConnection(){}

    public static Connection getInstance() throws Exception {
        if(conexao == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(url, username, password);
                return conexao;
            } catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        } else {
            return conexao;
        }
    }
}
