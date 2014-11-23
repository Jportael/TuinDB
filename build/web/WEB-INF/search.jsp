<%-- 
    Document   : search
    Created on : Nov 4, 2014, 2:51:37 AM
    Author     : Jx3
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
       <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

        <script>
            $(function () {
                var availableTags = [
            <c:forEach items="${zoektermen}" var="zoekterm">
                    "<c:out value="${zoekterm.toString()}"/>",
            </c:forEach>
                ];
                $("#search_bar").autocomplete({
                    source: availableTags
                });
            });


        </script>

    </head>
    <body>
        <jsp:include page="nav.jsp"/>
        <form id="search" action="search" method="post">
            <input id="search_bar" name="search_bar"/>
            <input type="submit" value="zoek"/>
        </form>
    </body>
</html>
