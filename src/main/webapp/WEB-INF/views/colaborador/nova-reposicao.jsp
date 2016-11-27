<%-- 
    Document   : nova-reposicao
    Created on : 26/11/2016, 22:05:17
    Author     : Luiz Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nova Reposição</title>
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
                        <h3><span class="glyphicon glyphicon-plus"></span> Nova Reposição</h3><hr>
                        
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
                        <h4>Informações:</h4>
                        <p class="text-justify text-center">
                            <b>Código da alocação:</b> ${alocacao.codigo} <br>
                            <b>Treinamento: </b> ${alocacao.treinamento.nome} <br>
                            <b>Data de inicio: </b> <fmt:formatDate value="${alocacao.datainicio}" pattern="dd/MM/yyyy" /> <br>
                            <b>Data de Termino: </b> <fmt:formatDate value="${alocacao.datafinal}" pattern="dd/MM/yyyy" /> <br>
                            <b>Professor responsavel: </b> ${alocacao.usuario.nome}<br>
                            <b>Dias da Semana: </b>
                            <ul class="col-md-6">
                                    <c:if test="${alocacao.segunda}"> <li>Segunda</li> </c:if>
                                    <c:if test="${alocacao.terca}"> <li>Terça </li></c:if>
                                    <c:if test="${alocacao.quarta}"><li> Quarta</li> </c:if>
                                    <c:if test="${alocacao.quinta}"><li> Quinta</li> </c:if>
                                    <c:if test="${alocacao.sexta}"><li> Sexta</li> </c:if>
                                    <c:if test="${alocacao.sabado}"><li> Sábado</li> </c:if>
                            </ul>
                        
                                            
                        <form action="${baseURL}area-restrita/falta/cadastrar" method="post"><!--FORM -->
                            <div class="row">
                                <div class="col-md-6 col-md-offset-3">
                                    <div class="form-group">
                                    <b><label for="cpf">Defina a data da reposição:</label></b>
                                    <input type="text" name="data" value="" class="form-control estilo-input datepicker"  />
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <div class="form-group">
                                    <b><label for="horaIni">Horário de Início</label></b>
                                    <input type="text" id="campoHora" name="horaIni" placeholder="Horário de Inicio" class="form-control estilo-input" />
                                </div>
                            </div>
                            <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="horaFin">Horário de Término</label></b>
                                    <input type="text" id="campoHora" name="horaTerm" placeholder="Horário de Término" class="form-control estilo-input"  />
                                    </div>
                            </div>
                            
                            <!-- BOTÃO DO FORMULÁRIO -->
                            <center><input type="submit" value="Cadastrar" name="cadastrar"  class="btn btn-success button" /></center>
                            <input type="hidden" name="codigoAlocacao" value="${alocacao.codigo}">
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

