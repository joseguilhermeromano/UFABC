<%-- 
    Document   : nova-falta
    Created on : 17/10/2016, 21:49:16
    Author     : Luiz Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Justificativa</title>
        <c:import url="layout/importes.jsp"></c:import>
    </head>
    <body>
        <div id="wrapper"><!-- Corpo da Página --> 
            <c:import url="layout/menu.jsp"></c:import>
            
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                        <a href="#menu-toggle" id="menu-toggle"><!-- Botão de exibir/ocultar menu lateral -->
                            <span class="glyphicon glyphicon-remove hidden-xs"></span>
                            <span class="glyphicon glyphicon-menu-hamburger hidden-lg hidden-md hidden-sm"></span>
                        </a><!-- /#Botão de exibir/ocultar menu lateral -->
                    <div class="col-md-12">

                        <!-- AQUI COMEÇA O CONTEÚDO DA PÁGINA -->
                        <h3><span class="glyphicon glyphicon-pencil"></span>Justificativa</h3><hr>
                        
                    <c:if test="${empty justificativa}">
                        <p class="alert alert-info">Ainda não há justificativa cadastrada para essa falta</p>
                    </c:if>
                    <c:if test="${not empty justificativa}">    
                        <div class="my_font_size">
                            <div class="row">
                                <div class="col-md-6">
                                    <b>Colaborador:</b> ${justificativa.falta.alocacao.usuario.nome} 
                                </div>
                                <div class="col-md-6">
                                    <b>Treinamento:</b> ${justificativa.falta.alocacao.treinamento.nome} 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <b>Data da falta:</b> <fmt:formatDate pattern="dd/MM/yyyy" value="${justificativa.falta.data}"/> 
                                </div>
                                <div class="col-md-6">
                                    <b>Data da Justificativa:</b> <fmt:formatDate pattern="dd/MM/yyyy" value="${justificativa.data}"/> 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <b>Status da justificativa:
                                        <c:if test="${justificativa.status == -1}"> <span class="text-warning">Pendente</span> </c:if>
                                        <c:if test="${justificativa.status == 0}"> <span class="text-danger">Recusada</span></c:if>
                                        <c:if test="${justificativa.status == 1}"> <span class="text-success">Aceita</span></c:if>
                                    </b>
                                </div>
                                <div class="col-md-6">
                                    
                                        <span class='my_plyphicon glyphicon glyphicon-save-file exemplo'></span><b>Comprovante:</b>  
                                        <a href="${baseURL}area-restrita/justificativa/showPdf/?codigo=${justificativa.codigo}">${justificativa.nome}</a>
                                </div>
                            </div>
                        </div>
                        <br>
                        <h3><span class="glyphicon glyphicon-list"></span> Descrição </h3><hr>
                        <div class="alert alert-info my_font_size col-md-12 text-justify">
                                ${justificativa.motivodesc}
                        </div>
                        <br>
                        
                        <c:if test="${usuario.administrador == 1}">
                            <form action="${baseURL}area-restrita/justificativa/alterar" method="post" ><!--FORM -->
                                <div class="col-md-6 col-md-offset-4 top-buffer">
                                    <span class="form-group col-md-4">
                                        <button type="submit" name="escolha" value="1" class="btn btn-success">Aceitar</button>
                                    </span>
                                    <span class="form-group ">
                                        <button type="submit" name="escolha" value="0" class="btn btn-danger">Recusar</button>
                                    </span>
                                </div>
                                <input type="hidden" name="codigo" value="${justificativa.codigo}">
                            </form><!-- /FORMULÁRIO -->
                        </c:if>        
                            <!-- BOTÃO DO FORMULÁRIO -->
                            
                        
                    </c:if>        
                        
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
            
        </div><!-- /Corpo da Página --> 
        <c:import url="layout/footer.jsp"></c:import>
    </body>
</html>

