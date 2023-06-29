<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
    <title>Login</title>
</head>
<body class="bg-warning ">
<div class="container-sm col-5 mt-5 rounded-2">

    <div id="header" class="modal-header bg-secondary text-white justify-content-center">
        <h1>Login</h1>
    </div>
    <div class="container bg-white rounded-bottom" >
        <div class="flex-column justify-content-center text-center">
            <form action="<%= request.getContextPath()%>/login" method="post">
                <div class="form-group pt-5 px-5 pb-3 ">
                    <label class="col-2">Login</label>
                    <input type="text" name="login">
                </div>
                <div class="form-group pb-5 px-5">
                    <label class="col-2">Senha</label>
                    <input type="password" name="senha" value="">
                </div>
                <input class="btn btn-success m-1 col-4" type="submit" value="Logar">
            </form>
            <form action="${request.getContextPath()}/todo/usuario?action=pre_cadastrar" method="post">
                <input type="submit" class="btn btn-primary m-1 col-4" value="Cadastrar Usuário"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
