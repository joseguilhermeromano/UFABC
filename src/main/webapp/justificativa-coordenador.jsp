<%-- 
    Document   : nova-falta
    Created on : 17/10/2016, 21:49:16
    Author     : Luiz Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Justificativa</title>
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
                    <div class="col-md-12">

                        <!-- AQUI COMEÇA O CONTEÚDO DA PÁGINA -->
                        <h3><span class="glyphicon glyphicon-pencil"></span>Justificativa</h3><hr>
                        
                        <div class="my_font_size">
                            <div class="col-md-4"><b>Colaborador:</b> usuario 1 </div>
                            <div class="col-md-5"><b>Treinamento:</b> Treinamento 1 </div>
                            <div class="col-md-4"><b>Data da falta:</b> 01/04/2016  </b></div>
                        </div>
                        
                        <h3 class="col-md-12">Conteudo da Justificativa: </h3>
                        <div>
                        <p class="my_font_size col-md-8 text-justify row">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque tincidunt mollis dolor quis efficitur. Pellentesque sed justo ullamcorper, hendrerit massa sit amet, fermentum neque. Aenean eu pulvinar lorem, sed sagittis est. Fusce pulvinar mollis neque, sit amet pharetra mi vehicula eget. In lobortis orci in turpis interdum
                        </p></div>
                        
                        <div class="row col-md-12">
                            <a class="btn btn-default my_font_size top-buffer" href='#'>
                                <i class='my_plyphicon glyphicon glyphicon-file exemplo'>    
                                </i>Arquivo em anexo
                            </a>
                        </div>
                        <br>
                        <form action="" method="" ><!--FORM -->
                            <div class="col-md-12 col-md-offset-2 top-buffer">
                                <span class="form-group col-md-2"><button class="btn btn-success">Aceito</button></span>
                                <span class="form-group "><button class="btn btn-danger">Recuso</button></span>
                            </div>

                            <!-- BOTÃO DO FORMULÁRIO -->
                            
                        </form><!-- /FORMULÁRIO -->
                            
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
            
        </div><!-- /Corpo da Página --> 
        <c:import url="WEB-INF/views/layout/footer.jsp"></c:import>
    </body>
</html>

