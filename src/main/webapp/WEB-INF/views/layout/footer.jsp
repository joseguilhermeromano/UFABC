    
    
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="baseURL" value="${pageContext.request.requestURL.substring(0, pageContext.request.requestURL.length() - pageContext.request.requestURI.length())}${pageContext.request.contextPath}/" />
    
    <!-- jQuery -->
    <script src="${baseURL}bootstrap/js/jquery.min.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${baseURL}bootstrap/js/bootstrap.min.js"></script>
    
    
    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
        $("span", this).toggleClass("glyphicon glyphicon-remove glyphicon glyphicon-menu-hamburger");
    });
    </script>




