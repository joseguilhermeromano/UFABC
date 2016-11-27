<%-- 
    Document   : faltas-colaborador
    Created on : 07/11/2016, 21:07:17
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faltas</title>
        <c:import url="../layout/importes.jsp"></c:import>
        </head>
        <body>
            <div id="wrapper"><!-- Corpo da Página --> 
            <c:import url="../layout/menu.jsp"></c:import>
                
                <!-- Page Content -->
                <div id="page-content-wrapper">
                    <div class="container-fluid">
                        <h1>Indicação de colaborador para reposição</h1>
                        <div class="col-md-4">
                            <c:out value="${options}" escapeXml="false"/>
                    </div>
                </div>
            </div>
            <!-- /#page-content-wrapper -->

        </div><!-- /Corpo da Página --> 
        <c:import url="../layout/footer.jsp"></c:import>
    </body>
</html>
