<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                    <a href="${baseURL}area-restrita/usuario/inicio"><span class="glyphicon glyphicon-home"></span> Início</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/usuario/perfil"><span class="glyphicon glyphicon-cog"></span> Perfil</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/usuario/listartudo"><span class="glyphicon glyphicon-list"></span> Usuários</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/treinamento/listartudo"><span class="glyphicon glyphicon-list"></span> Treinamentos</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/alocacao/listartudo"><span class="glyphicon glyphicon-list"></span> Alocação</a>
                </li>
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/falta/listartudo"><span class="glyphicon glyphicon-copy"></span> Faltas</a>
                </li>              
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/justificativa/cadastrar"><span class="glyphicon glyphicon-copy"></span> Justificativas</a>
                </li> 
                <hr>
                <li>
                    <a href="${baseURL}area-restrita/usuario/logoff"><span class="glyphicon glyphicon-log-out"></span> Sair</a>                    
                </li>
                <hr>
            </ul>
            </div><!-- /borda -->
        </div>
<!-- /#sidebar-wrapper -->
