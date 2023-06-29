package br.com.ifsp.todolist.controller;

import br.com.ifsp.todolist.dao.TarefaDAO;
import br.com.ifsp.todolist.dao.UsuarioDAO;
import br.com.ifsp.todolist.model.Usuario;
import br.com.ifsp.todolist.utils.PasswordEncryptor;
import br.com.ifsp.todolist.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/usuario/*")
public class UsuarioServlet extends HttpServlet {

    private static final String LOGOUT = "logout";
    private static final String LOGIN = "login";
    private static final String CADASTRAR = "cadastrar";
    private static final String PRE_CADASTRAR = "pre_cadastrar";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("action") == null ? "" : request.getParameter("action");

        switch (method) {
            case LOGOUT: {
                this.getServletContext().removeAttribute("usuario");
                response.sendRedirect(getServletContext().getContextPath() + "/login");
                break;
            }
            case LOGIN: {
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
                break;
            }
            case PRE_CADASTRAR: {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/usuario-cadastro.jsp");
                requestDispatcher.forward(request, response);
                break;
            }
            case CADASTRAR: {
                String nome = request.getParameter("nome");
                String login = request.getParameter("login");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");

                Usuario usuarioCadastro = new Usuario(login, senha, nome, email);
                String msgCadastro = "";
                boolean cadastroValido = false;

                if(!Validator.validarLogin(usuarioCadastro.getLogin())){
                    msgCadastro += "Login inválido. ";
                }
                if(!Validator.validarSenha(usuarioCadastro.getSenha())){
                    msgCadastro += "Senha inválida. ";
                }
                if(!Validator.validarEmail(usuarioCadastro.getEmail())){
                    msgCadastro += "Email inválido. ";
                }
                if(!Validator.validarNome(usuarioCadastro.getNome())){
                    msgCadastro += "Nome inválido. ";
                }
                if(UsuarioDAO.existeLoginCadastrado(usuarioCadastro.getLogin())){
                    msgCadastro += "Login já cadastrado. ";
                }
                if(UsuarioDAO.existeEmailCadastrado(usuarioCadastro.getEmail())){
                    msgCadastro += "Email já cadastrado. ";
                }
                if(msgCadastro.equals("")){
                    if(UsuarioDAO.insertUsuario(usuarioCadastro)){
                        cadastroValido = true;
                        msgCadastro = "Cadastro realizado com sucesso";
                    } else {
                        msgCadastro = "Erro na conexão com o banco de dados";
                    }
                }

                if (cadastroValido) {
                    request.setAttribute("mensagemCadastro", msgCadastro);
                    this.getServletContext().setAttribute("usuario", UsuarioDAO.getUsuarioLogin(login, senha));
                    response.sendRedirect(getServletContext().getContextPath() + "/tarefa");
                } else {
                    request.setAttribute("mensagemCadastro", "Erro ao cadastrar: " + msgCadastro);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/usuario-cadastro.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
            }
        }
    }
}
