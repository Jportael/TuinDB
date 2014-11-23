<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <jsp:include page="head.jsp"/>
        <jsp:include page="fancybox.jsp"/>
        <jsp:include page="fileinput.jsp"/>
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
            <h1 class="text-center">Voeg een plant toe</h1>
            <form class="form-horizontal" role="form" id="addPlant" action="addplant" method="post" enctype="multipart/form-data">

                <input type="hidden" name="plant_id" value="${plant.getId()}"/>

                <div class="form-group">
                    <label for="plant_groep" class="col-sm-2 control-label">Naam</label>
                    <div class="col-sm-10">
                        <input id="plant_groep" class="form-control" name="plant_groep" value="${plant.getGroepNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_soort" class="col-sm-2 control-label">Soort</label>
                    <div class="col-sm-10">
                        <input id="plant_soort" class="form-control" name="plant_soort" value="${plant.getSoortNaam()}"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="plant_naam" class="col-sm-2 control-label">variëteit</label>
                    <div class="col-sm-10">
                        <input id="plant_naam" class="form-control" name="plant_naam" type="text" value="${plant.getNaam()}" required/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_nlds_naam" class="col-sm-2 control-label">Nederlandse naam</label>
                    <div class="col-sm-10">
                        <input id="plant_nlds_naam" class="form-control" name="plant_nlds_naam" type="text" value="${plant.getNldsNaam()}" required/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_familie" class="col-sm-2 control-label">familie</label>
                    <div class="col-sm-10">
                        <input id="plant_familie" class="form-control" name="plant_familie" value="${plant.getFamilieNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_soort" class="col-sm-2 control-label">Kleur</label>
                    <div class="col-sm-10">
                        <input id="plant_kleur" class="form-control" name="plant_kleur" type ="text" value="${plant.getKleur()}"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <label for="file">voeg foto toe</label>      
                        <input id="input-id" type="file" name="file" class="file" value='foto' multiple/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type ="submit" class="btn btn-default" value="voeg toe"/>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
