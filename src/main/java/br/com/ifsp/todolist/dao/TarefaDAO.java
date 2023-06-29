package br.com.ifsp.todolist.dao;

import br.com.ifsp.todolist.database.DatabaseConnection;
import br.com.ifsp.todolist.model.StatusEnum;
import br.com.ifsp.todolist.model.Tarefa;
import br.com.ifsp.todolist.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public static Connection conexao;

    public static boolean insertTarefa(Tarefa tarefa){
        String INSERT_TAREFA = "INSERT INTO tarefa (titulo, descricao, data_criacao, data_conclusao, status, user_id)" +
                " VALUES (?,?,?,?,?,?)";

        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(INSERT_TAREFA);
            preparedStatement.setString(1, tarefa.getTitulo());
            preparedStatement.setString(2, tarefa.getDescricao());
            preparedStatement.setTimestamp(3, tarefa.getDataCriacaoTimestamp());
            preparedStatement.setTimestamp(4, tarefa.getDataConclusaoTimestamp());
            preparedStatement.setString(5, tarefa.getStatusEnum().getDescricao());
            preparedStatement.setInt(6, tarefa.getUsuario().getId());
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Tarefa> getTarefasUsuario(Usuario usuario) {
        String SELECT_TAREFA = "SELECT * FROM tarefa WHERE user_id = ?";

        try{
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_TAREFA);
            preparedStatement.setInt(1, usuario.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Tarefa> tarefas = new ArrayList<>();
            while (resultSet.next()){
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setTitulo(resultSet.getString("titulo"));
                tarefa.setDescricao(resultSet.getString("descricao"));
                tarefa.setDataCriacaoTimestamp(resultSet.getTimestamp("data_criacao"));
                tarefa.setDataConclusaoTimestamp(resultSet.getTimestamp("data_conclusao"));
                tarefa.setStatusEnum(StatusEnum.getEnumFromString(resultSet.getString("status")));
                tarefa.setUsuario(usuario);
                tarefas.add(tarefa);
            }
            return tarefas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void deleteTarefaUsuario(int tarefaId, int usuarioId) {
        String DELETE_TAREFA = "DELETE FROM tarefa WHERE id = ? AND user_id = ?";

        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(DELETE_TAREFA);
            preparedStatement.setInt(1, tarefaId);
            preparedStatement.setInt(2, usuarioId);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Tarefa getTarefaById(int tarefaId) {
        String SELECT_TAREFA = "SELECT * FROM tarefa WHERE id = ?";

        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_TAREFA);
            preparedStatement.setInt(1, tarefaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setTitulo(resultSet.getString("titulo"));
                tarefa.setDescricao(resultSet.getString("descricao"));
                tarefa.setDataCriacaoTimestamp(resultSet.getTimestamp("data_criacao"));
                tarefa.setDataConclusaoTimestamp(resultSet.getTimestamp("data_conclusao"));
                tarefa.setStatusEnum(StatusEnum.getEnumFromString(resultSet.getString("status")));
                tarefa.setUsuario(UsuarioDAO.getUsuarioById(resultSet.getInt("user_id")));
                return tarefa;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Tarefa getTarefaUsuario(int tarefaId, int usuarioId) {
        String SELECT_TAREFA_USUARIO = "SELECT * FROM tarefa WHERE id = ? AND user_id = ?";

        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(SELECT_TAREFA_USUARIO);
            preparedStatement.setInt(1, tarefaId);
            preparedStatement.setInt(2, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setTitulo(resultSet.getString("titulo"));
                tarefa.setDescricao(resultSet.getString("descricao"));
                tarefa.setDataCriacaoTimestamp(resultSet.getTimestamp("data_criacao"));
                tarefa.setDataConclusaoTimestamp(resultSet.getTimestamp("data_conclusao"));
                tarefa.setStatusEnum(StatusEnum.getEnumFromString(resultSet.getString("status")));
                tarefa.setUsuario(UsuarioDAO.getUsuarioById(resultSet.getInt("user_id")));
                return tarefa;
            }
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void updateTarefa(Tarefa tarefa) {
        String UPDATE_TAREFA = "UPDATE tarefa SET titulo = ?, descricao = ?, data_criacao = ?, data_conclusao = ?, status = ? WHERE id = ?";
        if(tarefa.getStatusEnum().equals(StatusEnum.CONCLUIDO)){
            tarefa.setDataConclusao(LocalDateTime.now());
        } else {
            tarefa.setDataConclusao(null);
        }
        try {
            conexao = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conexao.prepareStatement(UPDATE_TAREFA);
            preparedStatement.setString(1, tarefa.getTitulo());
            preparedStatement.setString(2, tarefa.getDescricao());
            preparedStatement.setTimestamp(3, tarefa.getDataCriacaoTimestamp());
            preparedStatement.setTimestamp(4, tarefa.getDataConclusaoTimestamp());
            preparedStatement.setString(5, tarefa.getStatusEnum().getDescricao());
            preparedStatement.setInt(6, tarefa.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
