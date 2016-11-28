<%-- 
    Document   : index
    Created on : 19/09/2016, 22:01:00
    Author     : José Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Início</title>
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
                        <h3><span class="glyphicon glyphicon-home"></span> Início</h3><hr>
                        <div class="row">
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div class="treinoscumpridos">
                                    <center><span>6</span></center>
                                    <center><p style="font-family: Arial Black; font-size: 11pt; color:#ffffff;">Treinamentos concluídos!</p></center>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div class="treinospendentes">
                                    <center><span style="font-family: Arial Black; font-size: 45pt; color:#ffffff;">4</span></center>
                                    <center><p>Treinamentos pendentes!</p></center>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div class="totaltreinosmes">
                                    <center><span style="font-family: Arial Black; font-size: 45pt; color:#ffffff;">10</span></center>
                                    <center><p style="font-family: Arial Black; font-size: 11pt; color:#ffffff;">Total de treinamentos no mês.</p></center>
                                </div>
                            </div>
                        </div>
                        <br>
                        <h4><span class="glyphicon glyphicon-alert"></span><b> Notificações</b></h4>
                        <br>
                        <div class="col-md-5">
                            <h4 class="text text-center"><b>Últimas Justificativas</b></h4>
                        <c:if test="${empty ultimasJustificativas}"><p class="alert alert-info text-center">Não há justificativas cadastradas</p></c:if>
                        <c:if test="${not empty ultimasJustificativas}">
                            <table class="table ls-table" id="tabela1">
                                
                                <thead>
                                    <tr>
                                        <th class="text text-center">Usuario</th>
                                        <th class="text text-center">Treinameto</th>
                                        <th class="text text-center">Data Justificativa</th>
                                    </tr>
                                </thead>
                                <c:forEach var="j" items="${ultimasJustificativas}" >
                                    <tr>
                                        <td class="text text-center">${j.falta.alocacao.usuario.nome}</td>
                                        <td class="text text-center">${j.falta.alocacao.treinamento.nome}</td>
                                        <td class="text text-center">${j.data}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <button class="col-lg-12 col-sm-12 col-xs-12 col-md-12 btn btn-success">Visualisar todas as justificativas</button>
                            </c:if>
                        </div>
                        
                        <div class="col-md-5 col-md-offset-2">
                            <h4 class="text text-center"><b>Últimas Reposições</b></h4>
                            <c:if test="${empty ultimasReposicoes}"><p class="alert alert-info text-center">Não há reposições cadastradas</p></c:if>
                            <c:if test="${not empty ultimasReposicoes}">
                        
                            <table id="tabela1" class="table ls-table">
                                <thead>
                                    <tr>
                                        <th class="text text-center">Usuario</th>
                                        <th class="text text-center">Treinameto</th>
                                        <th class="text text-center">Data da Reposição</th>
                                    </tr>
                                </thead>
                                <c:forEach var="r" items="${ultimasReposicoes}" >
                                    <tr>
                                        <td class="text text-center">${r.falta.alocacao.usuario.nome}</td>
                                        <td class="text text-center">${r.falta.alocacao.treinamento.nome}</td>
                                        <td class="text text-center">${r.data}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            
                            <button class="col-lg-12 col-sm-12 col-xs-12 col-md-12 btn btn-success">Visualisar todas as reposições</button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
            
        </div><!-- /Corpo da Página --> 
        <c:import url="../layout/footer.jsp"></c:import>
    </body>
</html>
