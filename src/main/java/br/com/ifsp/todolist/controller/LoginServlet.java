package br.com.ifsp.todolist.controller;

import br.com.ifsp.todolist.dao.TarefaDAO;
import br.com.ifsp.todolist.dao.UsuarioDAO;
import br.com.ifsp.todolist.model.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        requestDispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        Usuario usuario = UsuarioDAO.getUsuarioLogin(login, senha);
        if(usuario != null){
            this.getServletContext().setAttribute("usuario", usuario);
            request.setAttribute("tarefas", TarefaDAO.getTarefasUsuario(usuario));
            response.sendRedirect(getServletContext().getContextPath() + "/tarefa");
        } else {
            doGet(request, response);
        }
    }
}
