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
        <title>Início</title>
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
                        <h3><span class="glyphicon glyphicon-home"></span> Início</h3><hr>
                        <div class="row">
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div class="treinoscumpridos">
                                    <center><span>6</span></center>
                                    <center><p style="font-family: Arial Black; font-size: 11pt; color:#ffffff;">Treinamentos concluídos!</p></center>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div class="treinospendentes">
                                    <center><span style="font-family: Arial Black; font-size: 45pt; color:#ffffff;">4</span></center>
                                    <center><p>Treinamentos pendentes!</p></center>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-4 col-xs-4">
                                <div class="totaltreinosmes">
                                    <center><span style="font-family: Arial Black; font-size: 45pt; color:#ffffff;">10</span></center>
                                    <center><p style="font-family: Arial Black; font-size: 11pt; color:#ffffff;">Total de treinamentos no mês.</p></center>
                                </div>
                            </div>
                        </div>
                        <br>
                        <h4><span class="glyphicon glyphicon-alert"></span><b> Notificações</b></h4>
                        <br>
                        <div class="alert alert-info" role="alert">
                            <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
                        </div>

                        <div class="alert alert-danger" role="alert">
                            <strong>Oh snap!</strong> Change a few things up and try submitting again.
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
            
        </div><!-- /Corpo da Página --> 
        <c:import url="../layout/footer.jsp"></c:import>
    </body>
</html>
