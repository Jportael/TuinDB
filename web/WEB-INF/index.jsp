<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Plantendatabase v0.1</title>
        <jsp:include page="head.jsp"/>
        <script>
            //soorten
            $(function () {
                var availableTags = [
            <c:forEach items = "${soorten}" var = "soortBean" >
                    "${soortBean.getNaam()}",
            </c:forEach>
                    ""
                ];
                $("#plant_soort").autocomplete({
                    source: availableTags
                });
            });

            //groepen
            $(function () {
                var availableTags = [
            <c:forEach items = "${groepen}" var = "groepBean" >
                    "${groepBean.getNaam()}",
            </c:forEach>
                    ""
                ];
                $("#plant_groep").autocomplete({
                    source: availableTags
                });
            });

            //families
            $(function () {
                var availableTags = [
            <c:forEach items = "${families}" var = "familieBean" >
                    "${familieBean.getNaam()}",
            </c:forEach>
                    ""
                ];
                $("#plant_familie").autocomplete({
                    source: availableTags
                });
            });

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
        <div class="container">
            <h1 class="text-center">Plantendatabase v0.1</h1>
            <div class="row">
                <form class="form-horizontal" id="search" action="search" method="post">
                    <input id="search_bar" class="form-control" name="search_bar" placeholder="Geef hier een zoekopdracht" required="geef zoekterm in"/>
                    <input type="submit" class="btn btn-primary btn-lg btn-block text-center" href="search" value="zoek plant"/>
                </form>
            </div>
            <div class="row">
                <a class="btn btn-primary btn-lg btn-block" href="addplant">Voeg plant toe</a>
            </div>
            <br>
            <div class="row">
                <a class="btn btn-warning btn-lg btn-block" href="index">!! cache refresh !!</a> 
            </div>
        </div>
    </body>
</html>
