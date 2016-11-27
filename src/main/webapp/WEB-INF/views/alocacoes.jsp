<%-- 
    Document   : alocacoes
    Created on : 17/10/2016, 20:01:23
    Author     : Luiz Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alocações</title>
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
                    <div class="col-lg-12">
                        <h3><span class="glyphicon glyphicon-list"></span> Alocações</h3><hr>
                        
                        <!-- Mensagens sucesso/erro -->
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
                        
                        <div class="row">
                            <div class="col-sm-6">
                                <a class="btn btn-default visible-xs" href="${baseURL}area-restrita/alocacao/cadastrar"><span class="glyphicon glyphicon-plus" ></span> Nova Alocação</a><br>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-11 col-sm-11">
                               <div class="input-group">
                                   <form method="POST" action="${baseURL}area-restrita/alocacao/buscar">
                                    <div class="row">
                                        <div class="col-md-4">
                                                <select class="select2" name="codCol" style="width: 100%"> 
                                                  <option  value="" selected>Selecione um Colaborador</option>  
                                                  <c:forEach items="${usuarios}" var="usuario" varStatus="loop">
                                                        <option value="${usuario.codigo}">${usuario.nome}</option>
                                                  </c:forEach>
                                                </select>
                                        </div>
                                        <div class="col-md-4">
                                            <br class="visible-xs visible-sm">
                                                <select class="select2" name="codTreina" style="width: 100%">
                                                    <option value="" selected>Selecione um Treinamento</option>
                                                    <c:forEach items="${treinamentos}" var="treinamento" varStatus="loop">
                                                        <option value="${treinamento.codigo}">${treinamento.nome}</option>
                                                    </c:forEach>
                                                </select>
                                        </div>
                                        <div class="col-md-4">
                                            <br class="visible-xs visible-sm">
                                            <button class="btn btn-default" type="submit" style="float:left"><span class="glyphicon glyphicon-search"></span> Buscar</button>
                                        </div>
                                    </div>
                                   </form>
                               </div><!-- /input-group -->
                             </div><!-- /.col-lg-3 -->
                            <div class="col-md-1 col-sm-1">
                                 <a class="btn btn-default hidden-xs" href="${baseURL}area-restrita/alocacao/cadastrar" style="float:right"><span class="glyphicon glyphicon-plus"></span> Nova Alocação</a>
                            </div>
                        </div><!-- /row -->
                        <br><br>
                        <div class="table-responsive"><!-- TABELA-->
                            <table class="table ls-table" id="tabela1">
                                <thead>
                                    <tr>
                                            <th >Treinamento</th>
                                            <th class="text-center">Colaborador</th>
                                            <th class="text-center">Data Início</th>
                                            <th class="text-center">Data Fim</th>
                                            <th class="text-center">Hora de Início</th>
                                            <th class="text-center">Hora de Término</th>
                                            <th class="text-center">Dias da Semana</th>
                                            <th class="text-center">Status</th>
                                            <c:if test="${usuarioLogado.administrador==1}">
                                                <th class="text-center">Detalhar/Alterar</th>
                                                <th class="text-center">Cadastrar Falta</th>
                                                <th class="text-center">Excluir</th>
                                            </c:if>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${alocacoes}" var="alocacao" varStatus="loop">
                                    <tr>    
                                            <td>${alocacao.treinamento.nome}</td>
                                            <td class="text-center">${alocacao.usuario.nome}</td>
                                            <td class="text-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${alocacao.datainicio}"/></td>
                                            <td class="text-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${alocacao.datafinal}"/></td>
                                            <td class="text-center"><fmt:formatDate pattern="HH:mm" value="${alocacao.horainicio}"/></td>
                                            <td class="text-center"><fmt:formatDate pattern="HH:mm" value="${alocacao.horafim}"/></td>
                                            <td class="text-center">
                                                <c:if test="${alocacao.segunda}"> Segunda </c:if>
                                                <c:if test="${alocacao.terca}"> Terça </c:if>
                                                <c:if test="${alocacao.quarta}"> Quarta </c:if>
                                                <c:if test="${alocacao.quinta}"> Quinta </c:if>
                                                <c:if test="${alocacao.sexta}"> Sexta </c:if>
                                                <c:if test="${alocacao.sabado}"> Sábado </c:if>
                                            </td>
                                            <td class="text-center">
                                            <c:if test="${(alocacao.status==0) && (usuarioLogado.administrador==1)}">
                                                <a  href="#" data-toggle="modal" data-target="#modalConcluir" 
                                                    onclick="setCodigo('${alocacao.codigo}'); setLink('${baseURL}area-restrita/alocacao/concluir?codigo=');">
                                                    <span class="estilo-botao-edicao" style="color:orange"><b>Ok?</b></span>
                                                </a>
                                            </c:if>
                                            <c:if test="${alocacao.status == 1}">
                                                <span class="glyphicon glyphicon-ok estilo-botao-edicao"></span>
                                            </c:if>
                                            </td>
                                            <c:if test="${usuarioLogado.administrador==1}">
                                            <td class="text-center">
                                                <a href="<c:url value="${baseURL}area-restrita/alocacao/alterar">
                                                <c:param name="codigo" value="${alocacao.codigo}"></c:param></c:url>"><span class="glyphicon glyphicon-edit estilo-botao-edicao"></span></a>
                                            </td>
                                            
                                            <td class="text-center">
                                                <a href="<c:url value="${baseURL}area-restrita/falta/cadastrar">
                                                <c:param name="codigoAlocacao" value="${alocacao.codigo}"></c:param></c:url>"><span class="glyphicon glyphicon-plus estilo-botao-edicao"></span></a>
                                            </td>
                                            
                                            <td class="text-center"><a href="#" data-toggle="modal" data-target="#modalExcluir" 
                                                onclick="setCodigo('${alocacao.codigo}'); setLink('${baseURL}area-restrita/alocacao/excluir?codigo=');">
                                                    <span class="glyphicon glyphicon-trash estilo-botao-exclusao"></span></a>
                                            </td>
                                            </c:if>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /TABELA-->
                        <nav><!-- Paginação -->
                            <ul class="pagination">
                              <li>
                                <a href="#" aria-label="Previous">
                                  <span aria-hidden="true">&laquo;</span>
                                </a>
                              </li>
                              <li><a href="#">1</a></li>
                              <li><a href="#">2</a></li>
                              <li><a href="#">3</a></li>
                              <li><a href="#">4</a></li>
                              <li><a href="#">5</a></li>
                              <li>
                                <a href="#" aria-label="Next">
                                  <span aria-hidden="true">&raquo;</span>
                                </a>
                              </li>
                            </ul>
                        </nav><!-- /Paginação -->
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
        
        <!-- Modal de Conclusão de Treinamento -->
        <div id="modalConcluir" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">Conclusão de Treinamento</h4>
                    </div>
                    <div class="modal-body">

                        <p>Deseja realmente concluir este treinamento?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancelar</button>
                        <button class="btn btn-success" onclick="Excluir();">Concluir Treinamento</button>
                    </div>
                </div>
            </div>
        </div>  
            
        </div><!-- /Corpo da Página --> 
        <c:import url="layout/footer.jsp"></c:import>
    </body>
</html>
<c:import url="../js/searchUser.jsp"></c:import>
