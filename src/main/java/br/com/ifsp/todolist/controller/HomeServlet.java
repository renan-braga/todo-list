package br.com.ifsp.todolist.controller;

import br.com.ifsp.todolist.dao.TarefaDAO;
import br.com.ifsp.todolist.dao.UsuarioDAO;
import br.com.ifsp.todolist.model.Tarefa;
import br.com.ifsp.todolist.model.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = ((Usuario) request.getAttribute("usuario"));
        List<Tarefa> tarefas = TarefaDAO.getTarefasUsuario(usuario);
        request.setAttribute("tarefas", tarefas);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
