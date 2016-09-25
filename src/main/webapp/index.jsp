<%-- 
    Document   : index
    Created on : 21/09/2016, 13:50:58
    Author     : Luiz Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="WEB-INF/views/layout/importes.jsp"></c:import>
        <title>Login</title>
    </head>
    <body>
        <div class="container-fluid"> 
            <div id="loginbox" style="margin-top: 25px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-success" >
                    <div class="panel-heading">
                        <div class="panel-title"><img width="70px" height="30px" src="${baseURL}img/logo-UFABC.png"></div>
                        
                    </div>     
 
                    <div style="padding-top:30px" class="panel-body" >
 
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                          
                        <!-- FORM -->
                        <form id="loginform" class="form-horizontal" role="form" action="Executa" method="POST">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="login-username" type="text" class="form-control estilo-botao-busca" name="username" placeholder="Username">                                        
                            </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input id="login-password" type="password" class="form-control estilo-botao-busca" name="senha" placeholder="Senha">
                            </div>
 
                            <div style="margin-top:10px" class="form-group">
                                <!-- Button -->

                                <div class="col-sm-12 controls">
                                    <button type="submit" class="btn btn-default"><b>Login</b></button><br><br>
                                    <input type="hidden" name="tarefa" value="Login">
                                  <c:if test="${not empty msg}">
                                        <div class="alert alert-info" role="alert"><strong>Mensagem: </strong>${msg}</div>
                                   </c:if>

                                </div>
                            </div>
                            
			</form><!-- /FORM -->
                        
                    </div>
		</div>
            </div>
	</div>	
        <c:import url="WEB-INF/views/layout/footer.jsp"></c:import>
    </body>
</html>