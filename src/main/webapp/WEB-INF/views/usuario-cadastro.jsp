<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    <title>Login</title>
</head>
<body class="bg-warning ">

<div class="container-sm col-5 mt-5 rounded-2">
    <div id="header" class="modal-header bg-secondary text-white justify-content-center">
        <h1>Cadastrar Usuário</h1>
    </div>
    <div class="container bg-white rounded-bottom" >
        <div class="flex-column justify-content-center text-center">
            <form action="<%= request.getContextPath()%>/usuario?action=cadastrar" method="post" >
                <c:if test="${not empty mensagemCadastro}">
                    <div class="alert alert-danger" role="alert">
                        <span>${mensagemCadastro}</span>
                    </div>
                </c:if>
                <div class="form-group pt-5 px-5 pb-3 ">
                    <label class="col-2">Login</label>
                    <input type="text" name="login">
                </div>
                <div class="form-group pb-3 px-5">
                    <label class="col-2">Nome</label>
                    <input type="text" name="nome" value="">
                </div>
                <div class="form-group pb-3 px-5">
                    <label class="col-2">Email</label>
                    <input type="text" name="email" value="">
                </div>
                <div class="form-group pb-5 px-5">
                    <label class="col-2">Senha</label>
                    <input type="password" name="senha" value="">
                </div>
                <input class="btn btn-success m-1 col-4" type="submit" value="Cadastrar">
            </form>
            <form action="${request.getContextPath()}/todo/usuario/login" method="get">
                <input type="submit" class="btn btn-primary m-1 col-4" value="Cancelar"/>
            </form>

        </div>
    </div>
</div>
</body>
</html>
