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
        <title>Alocaçao</title>
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
                        <h3><span class="glyphicon glyphicon-plus"></span> Alocação</h3><hr>
                        
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
                        
                        <form action="${baseURL}area-restrita/alocacao/cadastrar" method="POST"><!--FORM -->

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                    <b><label for="dataIni">Data Inicial</label></b>
                                    <input type="text" name="dataIni" placeholder="Data Inicial" class="form-control estilo-input"  />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="dataFin">Data Final</label></b>
                                    <input type="text" name="dataFin" placeholder="Data Final" class="form-control estilo-input"  />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="horaIni">Horário de Início</label></b>
                                    <input type="text" name="horaIni" placeholder="Horário de Inicio" class="form-control estilo-input"  />
                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="horaFin">Horário de Término</label></b>
                                    <input type="text" name="horaTerm" placeholder="Horário de Término" class="form-control estilo-input"  />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="codCol">Código Colaborador</label></b>
                                    <input type="text" name="codCol" value="href=${baseURL}area-restrita/usuario/consultartudo" placeholder="Código do Colaborador" class="form-control estilo-input"  />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                    <b><label for="codTreinamento">Código Treinamento</label></b>
                                    <input type="text" name="codTreina" value="href=${baseURL}area-restrita/treinamento/consultartudo" placeholder="Código do Treinamento" class="form-control estilo-input"  />
                                    </div>
                                </div>
                            </div>  
                             <div class="col-md-6">
                                    "${baseURL}area-restrita/treinamento/consultartudo" 
                                    <div class="form-group">
                                        <b><label for="diaSemana">Dias da Semana</label></b><br>
                                    <div class="col-md-6">
                                        <input type="radio" name="seg" value="1">Segunda-Feira
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="ter" value="2">Terça-Feira<br>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="qua" value="3">Quarta-Feira<br>
                                    </div>   
                                    <div class="col-md-6">
                                        <input type="radio" name="quin" value="4">Quinta-Feira<br>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="sex" value="5">Sexta-Feira<br>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="sab" value="6">Sábado<br>
                                    </div>    
                                    </div>
                                </div>        
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <b><label for="diaSemana">Dias da Semana</label></b><br>
                                    <div class="col-md-6">
                                        <input type="radio" name="seg" value="1">Segunda-Feira
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="ter" value="2">Terça-Feira<br>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="qua" value="3">Quarta-Feira<br>
                                    </div>   
                                    <div class="col-md-6">
                                        <input type="radio" name="quin" value="4">Quinta-Feira<br>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="sex" value="5">Sexta-Feira<br>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="radio" name="sab" value="6">Sábado<br>
                                    </div>    
                                    </div>
                                </div>
                            </div>                                                        
                            <!-- BOTÃO DO FORMULÁRIO -->
                            <center><input type="submit" value="Cadastrar"  class="btn btn-success button" /></center>
                            
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
