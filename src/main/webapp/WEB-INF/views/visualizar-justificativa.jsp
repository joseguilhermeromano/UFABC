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
                        <c:if test="${!empty justificativa.motivorecusa}">
                        <h3><span class="glyphicon glyphicon-warning-sign"></span> Motivo da Recusa </h3><hr>
                        <div class="alert alert-info my_font_size col-md-12 text-justify">
                                ${justificativa.motivorecusa}
                        </div>
                        <br>
                        </c:if>
                        <c:if test="${(usuario.administrador == 1) && (justificativa.status < 0)}">
                                <div class="col-md-6 col-md-offset-4 top-buffer">
                                    <span class="form-group col-md-4">
                                        <button type="button" data-toggle="modal" data-target="#modalAceitar" class="btn btn-success">Aceitar</button>
                                    </span>
                                    <span class="form-group ">
                                        
                                        <button type="button" data-toggle="modal" data-target="#modalRecusar" class="btn btn-danger">Recusar</button>
                                    </span>
                                </div>
                        </c:if>        
                            <!-- BOTÃO DO FORMULÁRIO -->
                            
                        <c:if test="${usuario.administrador != 1}">
                            <form method="post" action="${baseURL}area-restrita/justificativa/getInfoToUpdate">
                                <button class="col-md-offset-4 col-md-3 btn btn-default" type="submit" name="alterar" value="">Alterar</button>
                                <input type="hidden" name="codigo" value="${justificativa.codigo}">
                            </form>
                        </c:if>
                    </c:if>        
                        
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
        
        <!-- Modal de Recusa -->
        <div id="modalRecusar" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${baseURL}area-restrita/justificativa/aceitarecusa" method="POST">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">Recusa de Justificativa</h4>
                    </div>
                    <div class="modal-body">

                        <p>Deseja realmente recusar esta justificativa? </p>
                        <p class="text-warning"><small>Por favor, escreva o motivo da recusa desta justificativa!</small></p>
                        <textarea class="form-control" id="recusa" name="recusa" rows="5" cols='10'>${justificativa.motivorecusa}</textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancelar</button>
                        <button class="btn btn-danger" type="submit" name="escolha" value="0">Recusar a Justificativa</button>
                    </div>
                    <input type="hidden" name="codigo" value="${justificativa.codigo}">
                    </form>
                </div>
            </div>
        </div> 
        <!-- Modal de Aceite -->
        <div id="modalAceitar" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="${baseURL}area-restrita/justificativa/aceitarecusa" method="POST">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">Aceite de Justificativa</h4>
                    </div>
                    <div class="modal-body">

                        <p>Deseja realmente aceitar esta justificativa? </p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancelar</button>
                        <button class="btn btn-success" type="submit" name="escolha" value="1">Aceitar</button>
                    </div>
                    <input type="hidden" name="codigo" value="${justificativa.codigo}">
                    </form>
                    
                </div>
            </div>
        </div> 
        
        
        </div><!-- /Corpo da Página --> 
        <c:import url="layout/footer.jsp"></c:import>
    </body>
</html>

