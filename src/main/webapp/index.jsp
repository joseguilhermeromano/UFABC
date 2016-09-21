<%-- 
    Document   : index
    Created on : 21/09/2016, 13:50:58
    Author     : Luiz Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="executa" method="post">
            <label>Login: </label><input name="nome" type="text">
            <label>Senha: </label><input name="senha" type="password">
            <button>Entrar</button>
        </form>
    </body>
</html>
