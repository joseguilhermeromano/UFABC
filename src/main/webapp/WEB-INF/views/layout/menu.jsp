<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Captura base da url da requisição -->
<c:set var="baseURL" value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/" />
 <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <div class="borda"><!-- borda -->
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        <img width="150px" height="60px" src="${baseURL}img/logo-UFABC.png">
                    </a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}administrador/index"><span class="glyphicon glyphicon-home"></span> Início</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}administrador/perfil"><span class="glyphicon glyphicon-cog"></span> Perfil</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}administrador/colaboradores"><span class="glyphicon glyphicon-list"></span> Colaboradores</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}administrador/treinamentos"><span class="glyphicon glyphicon-list"></span> Treinamentos</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}administrador/faltas"><span class="glyphicon glyphicon-copy"></span> Faltas</a>
                </li>
                <hr>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-log-out"></span> Sair</a>
                    
                </li>
                <hr>
            </ul>
            </div><!-- /borda -->
        </div>
<!-- /#sidebar-wrapper -->
