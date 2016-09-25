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
                        
                        <form action="" method=""><!--FORM -->

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                    <b><label for="descricao">Descrição</label></b>
                                    <input type="text" name="" value="" placeholder="Descrição do Treinamento..." class="form-control estilo-input"  />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                    <b><label for="datainicio">Data de Início</label></b>
                                    <input type="text" name="" value="" placeholder="Data de Início" class="form-control estilo-input"  />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                    <b><label for="datatermino">Data de Término</label></b>
                                    <input type="text" name="" value="" placeholder="Data de Término" class="form-control estilo-input"  />
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                    <b><label for="datatermino">Horário</label></b>
                                    <input type="text" name="" value="" placeholder="ex: 8:00 h às 9:00 h" class="form-control estilo-input"  />
                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <b><label for="nome">Turma</label></b>
                                    <input type="text" name="" value="" placeholder="ex: Turma A ADS Noturno 2º Semestre" class="form-control estilo-input"  />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                    <b><label for="cpf">Laboratório</label></b>
                                    <input type="text" name="" value="" placeholder="ex: labF010" class="form-control estilo-input"  />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                    <b><label for="nome">Responsável</label></b>
                                    <input type="text" name="" value="" placeholder="Responsável" class="form-control estilo-input"  />
                                    </div>
                                </div>
                            </div>

                            <!-- BOTÃO DO FORMULÁRIO -->
                            <center><input type="submit" value="Atualizar"  class="btn btn-success button" /></center>
                            
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
