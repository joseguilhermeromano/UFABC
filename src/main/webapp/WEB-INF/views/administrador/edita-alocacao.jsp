<%-- 
    Document   : index
    Created on : 19/09/2016, 22:01:00
    Author     : José Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alteração de Alocaçao</title>
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
                        <h3><span class="glyphicon glyphicon-pencil"></span> Atualização da Alocação</h3><hr>
                        
                        <!-- Mensagens sucesso/erro -->
                        <c:if test="${not empty erros}">
                            <c:forEach var="erro" items="${erros}}">
                                <div class="alert alert-danger" role="alert">
                                  ${erro}
                                </div>
                            </c:forEach>
                        </c:if>
                        <!-- / Mensagens sucesso/erro -->
                        
                        <form action="${baseURL}area-restrita/alocacao/alterar" method="POST"><!--FORM -->

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="codCol">Colaborador Responsável</label></b>
                                    <br>
                                    <input type="hidden" name="codigo" value="${alocacao.codigo}" />
                                        <select class="select2" name="codCol" style="width: 100%"> 
                                          <option value="${alocacao.usuario.codigo}" selected>${alocacao.usuario.nome}</option>  
                                          <c:forEach items="${usuarios}" var="usuario" varStatus="loop">
                                              <c:if test="${alocacao.usuario.codigo!=usuario.codigo}">
                                                <option value="${usuario.codigo}">${usuario.nome}</option>
                                                </c:if>
                                          </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="codTreinamento">Treinamento</label></b>
                                    <br>
                                        <select class="select2" name="codTreina" style="width: 100%">
                                            <option value="${alocacao.treinamento.codigo}" selected>${alocacao.treinamento.nome}</option>
                                            <c:forEach items="${treinamentos}" var="treinamento" varStatus="loop">
                                                <c:if test="${alocacao.treinamento.codigo!=treinamento.codigo}">
                                                <option value="${treinamento.codigo}">${treinamento.nome}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="dataIni">Data Inicial</label></b>
                                    <input type="text" name="dataIni" placeholder="Data Inicial" class="form-control estilo-input datepicker" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${alocacao.datainicio}"/>" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="dataFin">Data Final</label></b>
                                    <input type="text" name="dataFin" placeholder="Data Final" class="form-control estilo-input datepicker" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${alocacao.datafinal}"/>" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="horaIni">Horário de Início</label></b>
                                    <input type="text" id="campoHora" name="horaIni" placeholder="Horário de Inicio" class="form-control estilo-input" value="<fmt:formatDate pattern="HH:mm" value="${alocacao.horainicio}"/>" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="horaFin">Horário de Término</label></b>
                                    <input type="text" id="campoHora" name="horaTerm" placeholder="Horário de Término" class="form-control estilo-input" value="<fmt:formatDate pattern='HH:mm' value='${alocacao.horafim}' />" />
                                    </div>
                                </div>
                            </div>

                            
                            <div class="row">
                                <div class="col-md-6">
                                   <div class="form-group">
                                       <b><label for="diaSemana">Dias da Semana</label></b><br>
                                       <div class="col-md-6">
                                           <input type="checkbox" name="seg" value="1" <c:if test="${alocacao.segunda==true}"> checked </c:if>> Segunda-Feira<br>
                                           <input type="checkbox" name="ter" value="2" <c:if test="${alocacao.terca==true}"> checked </c:if>> Terça-Feira<br>
                                           <input type="checkbox" name="qua" value="3" <c:if test="${alocacao.quarta==true}"> checked </c:if>> Quarta-Feira
                                       </div>
                                       <div class="col-md-6">
                                           <input type="checkbox" name="qui" value="4" <c:if test="${alocacao.quinta==true}"> checked </c:if>> Quinta-Feira<br>
                                           <input type="checkbox" name="sex" value="5" <c:if test="${alocacao.sexta==true}"> checked </c:if>> Sexta-Feira<br>
                                            <input type="checkbox" name="sab" value="6" <c:if test="${alocacao.sabado==true}"> checked </c:if>>Sábado
                                       </div>
                                       
                                   </div>
                               </div>
                            </div>
                            <br><br>
                            <!-- BOTÃO DO FORMULÁRIO -->
                            <center>
                                <a  class="btn btn-warning button" href="${baseURL}area-restrita/alocacao/listartudo">Voltar</a>
                                <input type="submit" value="Atualizar"  class="btn btn-success button" />
                            </center>
                            
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
