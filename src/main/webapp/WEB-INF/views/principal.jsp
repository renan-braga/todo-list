<%@ page import="br.com.ifsp.todolist.model.Usuario" %>
<%@ page import="br.com.ifsp.todolist.model.Tarefa" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Home</title>
    <style>
        .form-mbe-0 {
            margin-block-end: 0;
        }
    </style>
</head>

<body class="bg-warning">
    <div class="container-sm col-7 mt-5 rounded-2">
        <% Usuario usuario = (Usuario) request.getServletContext().getAttribute("usuario"); %>
        <% List<Tarefa> tarefas = (List<Tarefa>) request.getAttribute("tarefas"); %>


        <form action="${request.getContextPath()}/todo/usuario?action=logout" method="post"
              class="text-end justify-content-end form-mbe-0">
            <button class="btn btn-danger col-2" type="submit">
                <span class="me-2">Logout</span>
                <svg xmlns="http://www.w3.org/2000/svg" width="1rem" height="1rem" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">
                    <path d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z"></path>
                </svg>
            </button>
        </form>

        <c:if test="${not empty mensagemCadastro}">
            <div class="alert alert-success" role="alert">
                <span>${mensagemCadastro}</span>
            </div>
        </c:if>
        <div id="header" class="modal-header bg-secondary text-white">
            <h1>Minhas Tarefas</h1>
            <h2>Usuário: <%= usuario.getNome() %></h2>
        </div>
        <div class="container">
            <c:forEach var="tarefa" items="${tarefas}">
                <div class="row">
                    <div class="card input-group">
                        <label class="card-header"><strong>Título: ${tarefa.titulo}</strong></label>
                        <label class="card-text"><strong>Descrição: </strong> ${tarefa.descricao}</label>
                        <label class="card-text"><strong>Data de Criação: </strong> ${tarefa.dataCriacao}</label>
                        <label class="card-text"><strong>Data de Conclusão: </strong> ${tarefa.dataConclusao}</label>
                        <label class="card-text"><strong>Status: </strong> ${tarefa.statusEnum.descricao}</label>

                        <div class="d-flex">
                            <td>
                                <form action="${request.getContextPath()}/todo/tarefa?action=pre_editar&id=${tarefa.id}"
                                      method="post">
                                    <input class="btn btn-success m-1" type="submit" value="Editar"/>
                                </form>
                                <form action="${request.getContextPath()}/todo/tarefa?action=excluir&id=${tarefa.id}"
                                      method="post">
                                    <input class="btn btn-danger m-1" type="submit" value="Excluir"/>
                                </form>
                            </td>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <form action="${request.getContextPath()}/todo/tarefa?action=pre_adicionar" method="post">
                <input type="submit" class="btn btn-success" value="Adicionar Tarefa"/>
            </form>
        </div>
    </div>
</body>
</html>
