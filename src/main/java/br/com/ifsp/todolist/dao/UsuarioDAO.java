package br.com.ifsp.todolist.dao;

import br.com.ifsp.todolist.database.DatabaseConnection;
import br.com.ifsp.todolist.model.Usuario;
import br.com.ifsp.todolist.utils.PasswordEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    static private Connection conexao;

    public static boolean insertUsuario(Usuario usuario){
        String senhaEncriptada = PasswordEncryptor.encryptPassword(usuario.getSenha());
        usuario.setSenha(senhaEncriptada);
        String INSERT_USUARIO = "INSERT INTO usuario (login, nome, email, senha) VALUES(?,?,?,?)";
        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(INSERT_USUARIO);
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getSenha());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Usuario getUsuarioLogin(String login, String senha){
        String senhaEncriptada = PasswordEncryptor.encryptPassword(senha);
        String SELECT_LOGIN = "SELECT * FROM usuario WHERE login = ? and senha = ?";
        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senhaEncriptada);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return wrapUsuario(resultSet);
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static List<Usuario> selectAllUsuario(){
        String SELECT_USUARIO = "SELECT * FROM usuario";
        try{
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_USUARIO);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Usuario> arrayListUsuario = new ArrayList<>();
            while (resultSet.next()){
                arrayListUsuario.add(wrapUsuario(resultSet));
            }
            return arrayListUsuario;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Usuario getUsuarioById(int userId) {
        String SELECT_USUARIO_FROM_ID = "SELECT * FROM usuario WHERE id = ?";
        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_USUARIO_FROM_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return wrapUsuario(resultSet);
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Usuario wrapUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setLogin(resultSet.getString("login"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setSenha(resultSet.getString("senha"));
        return usuario;
    }

    public static boolean existeLoginCadastrado(String login) {
        String SELECT_USUARIO_FROM_LOGIN = "SELECT * FROM usuario WHERE login = ?";
        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_USUARIO_FROM_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean existeEmailCadastrado(String email) {
        String SELECT_USUARIO_FROM_EMAIL = "SELECT * FROM usuario WHERE email = ?";
        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_USUARIO_FROM_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
