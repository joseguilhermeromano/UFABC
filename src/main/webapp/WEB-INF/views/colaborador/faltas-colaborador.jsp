<%-- 
    Document   : faltas-colaborador
    Created on : 07/11/2016, 21:07:17
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faltas</title>
        <c:import url="WEB-INF/views/layout/importes.jsp"></c:import>
    </head>
    <body>
        <div id="wrapper"><!-- Corpo da Página --> 
            <c:import url="WEB-INF/views/layout/menu.jsp"></c:import>
            
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                        <a href="#menu-toggle" id="menu-toggle"><!-- Botão de exibir/ocultar menu lateral -->
                            <span class="glyphicon glyphicon-remove hidden-xs"></span>
                            <span class="glyphicon glyphicon-menu-hamburger hidden-lg hidden-md hidden-sm"></span>
                        </a><!-- /#Botão de exibir/ocultar menu lateral -->
                    <div class="col-lg-12">
                        <h3><span class="glyphicon glyphicon-list"></span> Minhas Faltas</h3><hr>
                    <div class="row">    
                         <div class="col-md-6 col-sm-6">
                               <form method="GET" action="${baseURL}area-restrita/treinamento/buscar"> 
                                    <div class="input-group">
                                      <input type="text" name="busca" class="form-control estilo-botao-busca" placeholder="Buscar por código...">
                                      <span class="input-group-btn">
                                          <button class="btn btn-default estilo-botao-busca" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                      </span>
                                    </div><!-- /input-group -->
                               </form>
                             </div><!-- /.col-lg-6 -->
                    </div>         
                             <br><br>
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
                        <!-- / Mensagens sucesso/erro --><br><br>
                        <div class="table-responsive"><!-- TABELA-->
                            <table class="table ls-table" id="tabela1">
                                <thead>
                                    <tr>
                                        <th class="text-center col-md-1">Código </th>
                                        <th class="text-center col-md-1">Treinamento</th>
                                        <th class="text-center col-md-3">Data</th>
                                        <th class="text-center col-md-6">Descrição</th>
                                        <th class="text-center col-md-6">Justificativa</th>
                                        <th class="text-center col-md-6">Status</th>
                                        <th class="text-center col-md-6">Visualizar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                    <c:forEach var="item" items="${listaFaltas}">
                                    <tr>
                                            <td class="text-center">${item.codigo}</td>
                                            <td>${item.treinamento.nome}</td>
                                            <td>${item.data}</td>
                                            <td>${item.descricao}</td>
                                            <td>${item.justifica}</td>
                                            <td>${item.status}</td>
                                            <td><a href='<c:url value="">
                                                       <c:param name="codigo" value="${item.codigo}"/>
                                                   </c:url>' >
                                                    <span class="col-md-6 glyphicon glyphicon-eye-open"></span></a></td>
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
        <c:import url="WEB-INF/views/layout/footer.jsp"></c:import>
    </body>
</html>