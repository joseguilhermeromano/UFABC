<%-- 
    Document   : erro404
    Created on : 26/11/2016, 21:13:04
    Author     : José Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">

            <!-- Bootstrap -->
            <link href="http://localhost:8080/UFABC/bootstrap/css/bootstrap.min.css" rel="stylesheet">

            <!-- ESTILO CSS PERSONALIZADO -->
            <link href="http://localhost:8080/UFABC/bootstrap/css/estilo.css" rel="stylesheet"/>
        <title>Erro de Permissão</title>
    </head>
    <body>
        <div class="container-fluid"> 
            <div id="loginbox" style="margin-top: 25px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-success" >
                    <div class="panel-heading">
                        <div class="panel-title">
                            <h1> Ops... Você não tem permissão para acessar esta página!</h1>
                        </div>
                        
                    </div>     
 
                    <div style="padding-top:30px" class="panel-body" >
                        <p><b>A Página solicitada depende de permissão de administrador para ser exibida!</b></p>
                        <br>
                        <a  class="btn btn-warning button" href="javascript: window.history.back();">Voltar</a>
                    </div>
		</div>
            </div>
	</div>	
    </body>
</html>
