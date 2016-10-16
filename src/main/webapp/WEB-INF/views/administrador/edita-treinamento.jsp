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
        <title>Alteração de Treinamento</title>
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
                        <h3><span class="glyphicon glyphicon-pencil"></span> Alteração de Treinamento</h3><hr>
                        
                        
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
                         
                        <form action="${baseURL}area-restrita/treinamento/alterar" method="POST"><!--FORM -->

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="nome">Nome</label></b>
                                    <input type="hidden" name="codigo" value="${treinamento.codigo}" />
                                    <input type="text" name="nome" value="${treinamento.nome}" placeholder="Nome do Treinamento" class="form-control estilo-input"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                    <b><label for="descricao">Descrição</label></b>
                                    <textarea name="descricao"  rows="10" placeholder="Descrição do Treinamento..." class="form-control estilo-input"/>${treinamento.descricao}</textarea>
                                    </div>
                                </div>
                            </div>
                            <!-- BOTÃO DO FORMULÁRIO -->
                            <center>
                                <a  class="btn btn-warning button" href="${baseURL}area-restrita/treinamento/listartudo">Voltar</a>
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
