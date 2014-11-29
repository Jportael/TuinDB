<%-- 
    Document   : display_plant
    Created on : Nov 14, 2014, 12:00:55 AM
    Author     : Jx3
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gevonden Planten</title>
        <jsp:include page="head.jsp"/>
        <jsp:include page="fancybox.jsp"/>
        <script>
            jQuery(document).ready(function ($) {
                $(".clickableRow").click(function () {
                    window.document.location = $(this).attr("href");
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="nav.jsp"/>
        <table class="table table-hover">
            <tr>
                <th>ID</th>
                <th>naam</th>
                <th>Soort</th>
                <th>Variëteit</th>
                <th>Nederlandse naam</th>
                <th>familie</th>
                <th>Soort Boom</th>
                <th>Kleur</th>
                <th>foto's</th>
                <th>edit</th>
                <th>details</th>
            </tr>


            <c:forEach items="${gevondenPlanten}" var="plant">
                <tr>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getId()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getGroepNaam()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getSoortNaam()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getNaam()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getNldsNaam()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getFamilieNaam()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getSoortBoomNaam()}</td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">${plant.getKleur()}</td>



                    <td>
                        <c:forEach items="${plant.getFotos()}" var="fotos" end="0">
                            <a class="fancybox" rel="group" href="uploadFiles/${fotos.toString()}"><img class="img-responsive" src=<c:url value="uploadFiles/${fotos.toString()}"/> width="100"></a>
                            </c:forEach> 
                        <div class="hidden">
                            <c:forEach items="${plant.getFotos()}" var="fotos" begin="1">
                                <a class="fancybox" rel="group" href="uploadFiles/${fotos.toString()}"><img class="img-responsive" src=<c:url value="uploadFiles/${fotos.toString()}"/> width="100"></a>
                                </c:forEach> 
                        </div>
                    </td>
                    <td class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        EDIT
                    </td>
                    <td class='clickableRow' href="plantdetails?plant_id=${plant.getId()}">
                        EDIT
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>

</html>
