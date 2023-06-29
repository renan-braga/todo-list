package br.com.ifsp.todolist.controller;

import br.com.ifsp.todolist.dao.TarefaDAO;
import br.com.ifsp.todolist.model.StatusEnum;
import br.com.ifsp.todolist.model.Tarefa;
import br.com.ifsp.todolist.model.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/tarefa/*")
public class TarefaServlet extends HttpServlet {


    private static final String ADICIONAR = "adicionar";
    private static final String EDITAR = "editar";
    private static final String PRE_EDITAR = "pre_editar";
    private static final String PRE_ADICIONAR = "pre_adicionar";
    private static final String EXCLUIR = "excluir";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) this.getServletContext().getAttribute("usuario");
        List<Tarefa> tarefasUsuario = TarefaDAO.getTarefasUsuario(usuario);
        request.setAttribute("tarefas", tarefasUsuario);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/principal.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) this.getServletContext().getAttribute("usuario");
        String method = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (method) {
            case PRE_EDITAR:
                request.setAttribute("status", StatusEnum.values());
                int tarefaId = Integer.parseInt(request.getParameter("id"));
                Tarefa tarefa = TarefaDAO.getTarefaUsuario(tarefaId, usuario.getId());
                request.setAttribute("tarefa", tarefa);
                request.getRequestDispatcher("WEB-INF/views/tarefa-edicao.jsp").forward(request, response);
                break;
            case EDITAR:
                tarefaId = Integer.parseInt(request.getParameter("id"));
                tarefa = TarefaDAO.getTarefaUsuario(tarefaId, usuario.getId());
                tarefa.setTitulo(request.getParameter("titulo"));
                tarefa.setDescricao(request.getParameter("descricao"));
                tarefa.setStatusEnum(StatusEnum.getEnumFromString(request.getParameter("statusEnum")));
                TarefaDAO.updateTarefa(tarefa);
                response.sendRedirect(getServletContext().getContextPath() + "/tarefa");
                break;
            case PRE_ADICIONAR:
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/WEB-INF/views/tarefa-adicao.jsp").forward(request, response);
                break;
            case ADICIONAR:
                doPut(request, response);
                break;
            case EXCLUIR:
                doDelete(request, response);
                break;
            default:
                response.sendRedirect(getServletContext().getContextPath() + "/tarefa");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = (Usuario) this.getServletContext().getAttribute("usuario");
        String queryString = request.getParameter("id");
        int tarefaId = Integer.parseInt(queryString);
        TarefaDAO.deleteTarefaUsuario(tarefaId, usuario.getId());
        response.sendRedirect(getServletContext().getContextPath() + "/tarefa");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) this.getServletContext().getAttribute("usuario");
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String statusEnum = request.getParameter("statusEnum");
        Tarefa tarefa = new Tarefa(titulo, descricao, LocalDateTime.now(), null, StatusEnum.getEnumFromString(statusEnum), usuario);
        TarefaDAO.insertTarefa(tarefa);
        response.sendRedirect(getServletContext().getContextPath() + "/tarefa");
    }
}
