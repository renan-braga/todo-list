<%@ page import="br.com.ifsp.todolist.model.Usuario" %>
<%@ page import="br.com.ifsp.todolist.model.Tarefa" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

    <title>Home</title>
    <style>
        .card {
            margin-bottom: 20px;
        }
    </style>
</head>
<body class="container">

<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
<% List<Tarefa> tarefas = (List<Tarefa>) request.getAttribute("tarefas"); %>
<h1>Home</h1>
<h2>Bem-vindo(a), <%= usuario.getNome() %></h2>
<div class="row">
    <div class="col-md-6 offset-md-3">
        <div class="card">
            <div class="card-header">
                <strong>Tarefas</strong>
            </div>
            <ul class="list-group list-group-flush">
                <c:forEach var="tarefa" items="${tarefas}">
                    <li class="list-group-item">
                        <h5 class="card-title">${tarefa.titulo}</h5>
                        <p class="card-text"><strong>Descrição:</strong> ${tarefa.descricao}</p>
                        <p class="card-text"><strong>Data de Criação:</strong> ${tarefa.dataCriacao}</p>
                        <p class="card-text"><strong>Data de Conclusão:</strong> ${tarefa.dataConclusao}</p>
                        <p class="card-text"><strong>Status:</strong> ${tarefa.status}</p>
                        <form action="tarefa-edicao.jsp" method="post">
                            <input type="hidden" name="tarefa" value="${tarefa}">
                            <button class="btn btn-success" type="submit">Editar</button>
                        </form>
                        <form action="tarefa-excluir.jsp" method="post">
                            <input type="hidden" name="tarefa" value="${tarefa}">
                            <button class="btn btn-success" type="submit">Excluir</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
