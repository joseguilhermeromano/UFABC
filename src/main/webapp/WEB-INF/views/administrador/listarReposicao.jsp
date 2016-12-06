<%-- 
    Document   : teste
    Created on : Nov 28, 2016, 9:31:44 PM
    Author     : eddie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reposição</title>
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
                        <h3><span class="glyphicon glyphicon-list"></span> Reposição</h3><hr>
                        
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
<div class="table-responsive"><!-- TABELA-->
                            <table class="table ls-table" id="example">
                                <thead>
                                    <tr>
                                            <th class="text-center col-md-1">Código</th>
                                            <th class="col-md-1">Data</th>
                                            <th class="col-md-1">Início</th>
                                            <th class="col-md-1">Término</th>
                                            <th class="col-md-1">Status</th>
                                            <th class="col-md-2">Código da falta</th>
                                            <th class="col-md-1">Responsável</th>
                                            <th class="text-center col-md-1">Detalhar/Alterar</th>
                                            <th class="text-center col-md-1">Excluir</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${repo}">
                                    <tr>
                                            <td class="text-center">${repo.codigo}</td>
                                            <td>${repo.data}</td>
                                            <td>${repo.horaInicio}</td>
                                            <td>${repo.horaFim}</td>
                                            <td>${repo.horaInicio}</td>
                                            <td>${repo.status}</td>
                                            <td class="text-center">
                                                <a href="<c:url value="${baseURL}area-restrita/treinamento/alterar">
                                                <c:param name="codigo" value="${item.codigo}"></c:param></c:url>"><span class="glyphicon glyphicon-edit estilo-botao-edicao"></span></a>
                                            </td>
                                            <td class="text-center"><a href="#" data-toggle="modal" data-target="#modalExcluir" 
                                                onclick="setCodigo('${item.codigo}'); setLink('${baseURL}area-restrita/treinamento/excluir?codigo=');">
                                                    <span class="glyphicon glyphicon-trash estilo-botao-exclusao"></span></a>
                                            </td>
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
            
        </div><!-- /Corpo da Página --> 
        <c:import url="../layout/footer.jsp"></c:import>
    </body>
</html>
