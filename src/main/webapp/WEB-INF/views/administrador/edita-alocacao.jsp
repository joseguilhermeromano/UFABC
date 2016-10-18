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
        <title>Alteração de Alocacao</title>
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
                        <h3><span class="glyphicon glyphicon-pencil"></span> Alteração de Alocação</h3><hr>
                        
                        
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
                                        <b><label for="codigo_responsavel">Colaborador Responsavel:</label></b>
                                    <input type="text" name="codigo_colaborador" value="${alocacao.usuario.codigo}" placeholder="Código do Colaborador" class="form-control estilo-input"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="codigo_treinamento">Treinamento:</label></b>
                                    <input type="text" name="codigo_treinamento" value="${alocacao.treinamento.codigo}" placeholder="Código do Treinamento" class="form-control estilo-input"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="data_inicio">Data de Início:</label></b>
                                    <input type="text" name="nome" value="${alocacao.datainicio}" placeholder="Data Início" class="form-control estilo-input"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="data_final">Data Final:</label></b>
                                    <input type="text" name="nome" value="${alocacao.datafinal}" placeholder="Data Final" class="form-control estilo-input"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="hora_inicio">Hora de Início:</label></b>
                                    <input type="text" name="hora_inicio" value="${alocacao.horainicio}" placeholder="Horário de Início" class="form-control estilo-input"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="hora_final">Hora de Término:</label></b>
                                    <input type="text" name="hora_final" value="${alocacao.horafinal}" placeholder="Horário de Término" class="form-control estilo-input"/>
                                    </div>
                                </div>
                            </div>
                            <h5><b>Dias da Semana:</b></h5>
                            <div class="row">
                                <div class="col-md-2">
                                    <div class="form-group">
                                    <input type="checkbox" name="segunda" value="${alocacao.segunda}" class=""/>
                                    <b><label for="segunda">Segunda</label></b>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                    <input type="checkbox" name="terca" value="${alocacao.terca}" class=""/>
                                    <b><label for="terca">Terça</label></b>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                    <input type="checkbox" name="quarta" value="${alocacao.quarta}" class=""/>
                                    <b><label for="segunda">Quarta</label></b>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                    <input type="checkbox" name="quinta" value="${alocacao.quinta}" class=""/>
                                    <b><label for="quinta">Quinta</label></b>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                    <input type="checkbox" name="sexta" value="${alocacao.sexta}" class=""/>
                                    <b><label for="sexta">Sexta</label></b>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                    <input type="checkbox" name="sabado" value="${alocacao.sabado}" class=""/>
                                    <b><label for="sabado">Sabado</label></b>
                                    </div>
                                </div>
                            </div>
                            <br><br>
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
