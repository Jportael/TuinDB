<%-- 
    Document   : edit_plant
    Created on : Nov 15, 2014, 1:35:01 AM
    Author     : Jx3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edit plant: ${plant.getNaam()}</title>
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
            <c:forEach items = "${soort_bomen}" var = "soortBoomBean">
                    "<c:out value="${soortBoomBean.getNaam()}"/>",
            </c:forEach>
                ];
                $("#plant_soort_boom").autocomplete({
                    source: availableTags
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="nav.jsp"/>
        <div class="container-fluid">
            <form id="editPlant" class="form-horizontal" role="form" action="editplant" method="post">
                <h1 class="text-center">wijzig de plant</h1>
                <input type="hidden" name="plant_id" value="${plant.getId()}"/>

                <div class="form-group">
                    <label for="plant_naam" class="col-sm-2 control-label">Naam</label>
                    <div class="col-sm-10">
                        <input id="plant_naam" class="form-control" name="plant_naam" type="text" value="${plant.getNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_nlds_naam" class="col-sm-2 control-label">Nederlandse naam</label>
                    <div class="col-sm-10">
                        <input id="plant_nlds_naam" class="form-control" name="plant_nlds_naam" type="text" value="${plant.getNldsNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_soort" class="col-sm-2 control-label">Kleur</label>
                    <div class="col-sm-10">
                        <input id="plant_kleur" class="form-control" name="plant_kleur" type ="text" value="${plant.getKleur()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_soort" class="col-sm-2 control-label">Soort</label>
                    <div class="col-sm-10">
                        <input id="plant_soort" class="form-control" name="plant_soort" value="${plant.getSoortNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_groep" class="col-sm-2 control-label">Groep</label>
                    <div class="col-sm-10">
                        <input id="plant_groep" class="form-control" name="plant_groep" value="${plant.getGroepNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_familie" class="col-sm-2 control-label">familie</label>
                    <div class="col-sm-10">
                        <input id="plant_familie" class="form-control" name="plant_familie" value="${plant.getFamilieNaam()}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="plant_soort_boom" class="col-sm-2 control-label">Soort Boom</label>
                    <div class="col-sm-10">
                        <input id="plant_soort_boom" class="form-control" name="plant_soort_boom" value="${plant.getSoortBoomNaam()}"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input id="is_active"  type="checkbox" name="is_active" ${plant.isActive()?'checked':''}/>
                                is actief
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type ="submit" class="btn btn-default" value="wijzig"/>
                    </div>
                </div>

            </form >

            <form  action="addpicture" method="post" enctype="multipart/form-data">    
                <input type="hidden" name="plant_id" value="${plant.getId()}"/>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <label for="file">voeg foto toe</label>      
                        <input id="input-id" type="file" name="file" class="file" value='foto' multiple/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" class="btn btn-default" value="toevoegen"/>
                    </div>
                </div>

            </form>
            <c:forEach items="${plant.getFotos()}" var="fotos">
                <form action="verwijderfoto" method="post">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <a class="fancybox" rel="group" href="uploadFiles/${fotos.toString()}"><img src=<c:url value="uploadFiles/${fotos.toString()}"/> width="200"></a>
                        </div>
                    </div>

                    <input type="hidden" name="foto_loc" value="${fotos.toString()}"/>
                    <input type="hidden" name="plant_id" value="${plant.getId()}"/>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" class="btn btn-default" value="verwijder"/>
                        </div>
                    </div>
                </form>
            </c:forEach> 
        </div>
    </body>
</html>
