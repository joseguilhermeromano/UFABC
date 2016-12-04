<%-- 
    Document   : nova-reposicao
    Created on : 04/12/2016, 00:35:00
    Author     : José Guilherme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alteração de Reposição</title>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            
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
                        <h3><span class="glyphicon glyphicon-pencil"></span> Alteração de Reposição</h3><hr>
                        
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
                            <b>Código da falta</b> ${falta.codigo} <br>
                            <b>Treinamento: </b> ${falta.alocacao.treinamento.nome} <br>
                            <b>Data da falta: </b> <fmt:formatDate value="${falta.data}" pattern="dd/MM/yyyy" /> <br>
                            <b>Professor responsavel: </b> ${falta.alocacao.usuario.nome}<br>
                            
                            <p class="">
                                <b>Dias em que o treinamento é realizado: </b>
                                    <c:if test="${falta.alocacao.segunda}"> Segunda </c:if>
                                    <c:if test="${falta.alocacao.terca}"> Terça </c:if>
                                    <c:if test="${falta.alocacao.quarta}"> Quarta </c:if>
                                    <c:if test="${falta.alocacao.quinta}"> Quinta </c:if>
                                    <c:if test="${falta.alocacao.sexta}"> Sexta </c:if>
                                    <c:if test="${falta.alocacao.sabado}"> Sábado </c:if>
                            </p>
                        
                                  
                        </div>
                        <form action="${baseURL}area-restrita/reposicao/cadastrar" method="post"><!--FORM -->
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <b><label for="cpf">Defina a data da reposição:</label></b>
                                        <input type="text" placeholder="Data da reposição" name="data" value="<fmt:formatDate value='${reposicao.data}' pattern="dd/MM/yyyy" />" class="form-control estilo-input datepicker"  />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <b><label for="horaIni">Horário de Início</label></b>
                                        <input type="text" id="campoHora" name="horaInicio" value="<fmt:formatDate value='${reposicao.horaInicio}' pattern="HH:mm" />" placeholder="Horário de Inicio" class="form-control estilo-input" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <b><label for="horaFin">Horário de Término</label></b>
                                        <input type="text" id="campoHora" name="horaFim" value="<fmt:formatDate value='${reposicao.horaFim}' pattern="HH:mm" />" placeholder="Horário de Término" class="form-control estilo-input"  />
                                    </div>
                                </div>
                                    <div class="form-group col-md-12">
                                        <div class="">
                                            <c:if test="${reposicao.falta.alocacao.usuario==usuarioLogado.codigo}">
                                                <input id="opcao1" type="checkbox" value="1" onclick="ativaSelect('colaboradorSelect','opcao1')"> 
                                            </c:if>
                                            <c:if test="${reposicao.falta.alocacao.usuario!=usuarioLogado.codigo}">
                                                <input id="opcao1" type="checkbox" value="1" onclick="ativaSelect('colaboradorSelect','opcao1')" checked>
                                            </c:if>
                                            <label for="opcao1"> Indicar colaborador para realizar a reposição</label>
                                        </div>

                                        <select class="select2 col-md-4 " name="codigoColaborador" disabled id="colaboradorSelect">
                                            <option value="-1">Selecione um colaborador</option>
                                            <c:if test="${reposicao.falta.alocacao.usuario!=usuarioLogado.codigo}">
                                                <option value="${reposicao.falta.alocacao.usuario.codigo}" selected>${reposicao.falta.alocacao.usuario.nome}</option>
                                            </c:if>
                                            <c:forEach items="${colaboradores}" var="c" varStatus="loop">
                                                <c:if test="${c.codigo!=usuarioLogado.codigo}">
                                                    <option value="${c.codigo}">${c.nome}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                            </div>    
                            <!-- BOTÃO DO FORMULÁRIO -->
                            
                            
                            <input type="hidden" name="codigo" value="${falta.codigo}">
                            <center><input type="submit" value="cadastrar" name="cadastrar"  class="btn btn-success button" /></center>
                            <input type="hidden" name="codigoAlocacao" value="${alocacao.codigo}">
                        </form><!-- /FORMULÁRIO -->
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
            
        </div><!-- /Corpo da Página --> 
        <c:import url="../layout/footer.jsp"></c:import>
        <script type="text/javascript">
            function ativaSelect(selectId, radioId) {
                var radio = document.getElementById(radioId);
                var select = document.getElementById(selectId);
                if(radio.checked) {
                    select.disabled = false;
                } else select.disabled = true;
                
            }
        </script>
    </body>
</html>

