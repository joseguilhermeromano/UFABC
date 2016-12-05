<%-- 
    Document   : justificativa
    Created on : Oct 31, 2016, 1:28:37 PM
    Author     : eddie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Justificativa</title>
        <c:import url="../layout/importes.jsp"></c:import>
    </head>
    <body>
        <div id="wrapper"><!-- Corpo da Página --> 
            <c:import url="../layout/menu.jsp"></c:import>
            
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                        <a href="#menu-toggle" id="menu-toggle"><!-- Botão de exibir/ocultar menu lateral -->
                            <span class="glyphicon glyphicon-remove hidden-xs"></span>
                            <span class="glyphicon glyphicon-menu-hamburger hidden-lg hidden-md hidden-sm"></span>
                        </a><!-- /#Botão de exibir/ocultar menu lateral -->
                    <div class="col-lg-12">

                        <!-- AQUI COMEÇA O CONTEÚDO DA PÁGINA -->
                        <h3><span class="glyphicon glyphicon-plus"></span> Justificativa</h3><hr>
                        
                        <!-- Mensagens sucesso/erro -->
                        <c:if test="${not empty erros}">
                            <c:forEach var="erro" items="${erros}}">
                                <div class="alert alert-danger" role="alert">
                                  ${erro}
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty sucesso}">
                            <div class="alert alert-success" role="alert">
                                  ${sucesso}
                            </div>
                        </c:if>
                        <c:if test="${not empty erro}">
                            <div class="alert alert-danger" role="alert">
                                  ${erro}
                            </div>
                        </c:if>
                        <!-- / Mensagens sucesso/erro -->
                        
                        <form action="${baseURL}area-restrita/justificativa/alterar" method="POST" ENCTYPE="multipart/form-data"><!--FORM -->
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">="Comprovante" class="form-control estilo-input" value="
                                    <%= 
                                        if(request.getParameter("tempInfoFile") == null){    
                                                request.getParameter("tempInfoFile") 
                                        }        
                                    %>       
                                    "
                                    <b><label for="data">Comprovante</label></b>
                                    <input type="file" name="userfile" placeholder="Comprovante" class="form-control estilo-input" value="
                                    <%= 
                                        if(request.getParameter("tempInfoFile") == null){    
                                                request.getParameter("tempInfoFile") 
                                        }        
                                    %>       
                                    " />
                                    </div>
                                </div>                                
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="nome">Nome</label></b>
                                    <input type="text" name="nome" placeholder="Nome" class="form-control estilo-input" value="
                                    <%= 
                                        if(request.getParameter("nomeBanco") == null){    
                                                request.getParameter("nomeBanco") 
                                        }        
                                    %>       
                                    " />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="data">Data</label></b>
                                    <input type="text" name="data" placeholder="Data" class="form-control estilo-input datepicker" value="
                                    <%= 
                                        if(request.getParameter("dataBanco") == null){    
                                                request.getParameter("dataBanco") 
                                        }        
                                    %>       
                                    " />
                                    </div>
                                </div>                                
                            </div>
                             <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="motivo">Motivo:</label>
                                    <textarea class="form-control" id="editor" name="motivo" rows="10" cols='10'>
                                     <%= 
                                        if(request.getParameter("motivoBanco") == null){    
                                                request.getParameter("motivoBanco") 
                                        }        
                                    %>

                                    </textarea>
                                </div>
                            </div>
                            </div>      
                            <input type="hidden" value="<%= request.getParameter("codigo") %>" name="codigo" id="codigo">
                            <!-- BOTÃO DO FORMULÁRIO -->
                            <center><input type="submit" value="Cadastrar"  class="btn btn-success button" /></center>
                            
                        </form><!-- /FORMULÁRIO -->
                            
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
            
        </div><!-- /Corpo da Página --> 
        <c:import url="../layout/footer.jsp"></c:import>
    </body>
</html>
