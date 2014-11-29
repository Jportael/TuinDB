<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html>
    <head>
        <title>Plantendatabase v0.1</title>
        <jsp:include page="head.jsp"/>
        <script>
            $(function () {
                var availableTags = [
            <c:forEach items = "${zoektermen}" var = "zoekterm">
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
        <div class="container-fluid">
            <h1 class="text-center">Plantendatabase v0.1</h1>
            <div class="row">
                <form class="form-horizontal" id="search" action="search" method="post">
                    <input id="search_bar" class="form-control" name="search_bar" placeholder="Geef hier een zoekopdracht" required/>
                    <input type="submit" class="btn btn-primary btn-lg btn-block text-center" value="zoek plant"/>
                </form>
            </div>
            <br>
            <div class="row">
                <a class="btn btn-primary btn-lg btn-block" href="addplant">Voeg plant toe</a>
            </div>
            <br>
            <div class="row">
                <a class="btn btn-warning btn-lg btn-block" href="index">!! cache refresh !!</a> 
            </div>
        </div>
        <br>
        
        <footer class="footer">
            <table class="table table-responsive table-bordered">
                <tr> 
                    <td>statistieken</td>
                    <td>${plantStatistics} planten</td>
                    <td>${soortStatistic} Soorten</td>
                    <td>${groepStatistic} Variëteiten</td>
                    <td>${familieStatistic} families</td>
                </tr>
            </table>
        </footer>   
    </body>
</html>
