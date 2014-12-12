<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant details:</title>
        <jsp:include page="head.jsp"/>
        <jsp:include page="fancybox.jsp"/>
        <script>
            $(function () {
                var availableTags = [
            <c:forEach items = "${snoei}" var = "snoeiBean">
                    "<c:out value="${snoeiBean.getNaam()}"/>",
            </c:forEach>
                ];
                $("#plant_snoei_naam").autocomplete({
                    source: availableTags
                });
            });
        </script>

        <script>
            $(function () {
                var availableTags = [
            <c:forEach items = "${vermeerder}" var = "vermeerderBean">
                    "<c:out value="${vermeerderBean.getNaam()}"/>",
            </c:forEach>
                ];
                $("#plant_vermeerder_naam").autocomplete({
                    source: availableTags
                });
            });
        </script>
        <script>
            jQuery(document).ready(function ($) {
                $(".clickableRow").click(function () {
                    window.document.location = $(this).attr("href");
                });
            });
        </script>


        <script>
            function fillCategory() {
                // this function is used to fill the category list on load
            <c:forEach items="${pit}" var="pits">
                addOption(document.drop_list.Category, "${pits.getId()}", "${pits.getNaam()}");
            </c:forEach>
            }

            //PIT select
            function SelectSubCat() {
                // ON selection of category this function will work
                removeAllOptions(document.drop_list.SubCat);

            <c:forEach items="${pit}" var="pits">
                if (document.drop_list.Category.value === '${pits.getId()}') {
                    addOption(document.drop_list.SubCat, "1", "GEEN SUBPIT");
                <c:forEach items="${pits.getSubPit()}" var="subPit">
                    addOption(document.drop_list.SubCat, "${subPit.getId()}", "${subPit.getNaam()}");
                </c:forEach>
                }
            </c:forEach>
            }

            function removeAllOptions(selectbox)
            {
                var i;
                for (i = selectbox.options.length - 1; i >= 0; i--)
                {
                    //selectbox.options.remove(i);
                    selectbox.remove(i);
                }
            }


            function addOption(selectbox, value, text)
            {
                var optn = document.createElement("OPTION");
                optn.text = text;
                optn.value = value;
                selectbox.options.add(optn);
            }


        </script>

    </head>
    <body onload="fillCategory();">
        <div class="container-fluid">
            <h1 class="text-center">Details: ${plant.getNaam()}</h1>
            <div class="container">
                <table class="table table-condensed table-bordered table-hover" >
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th></th>
                        <th>Gegevens</th>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>#</th>
                        <td>${plant.getId()}</td>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Naam</th>
                        <td>${plant.getGroepNaam()}</td>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Soort</th>
                        <td>${plant.getSoortNaam()}</td>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Variëteit</th>
                        <td>${plant.getNaam()}</td>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Nederlandse Naam</th>
                        <td>${plant.getNldsNaam()}</td>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Familienaam</th>
                        <td>${plant.getFamilieNaam()}</td>
                    </tr>
                    <tr  class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Soort Boom</th>
                        <td>${plant.getSoortBoomNaam()}</td>
                    </tr>
                    <tr class='clickableRow' href="editplant?plant_id=${plant.getId()}">
                        <th>Kleur</th>
                        <td>${plant.getKleur()}</td>
                    </tr>
                </table>
            </div>
            <div class="container">

                <c:forEach items="${plant.getFotos()}" var="fotos" begin="0" end="2">
                    <a class="fancybox" rel="group" href="uploadFiles/${fotos.toString()}"><img src=<c:url value="uploadFiles/${fotos.toString()}"/> height="250"></a>
                    </c:forEach> 

            </div>
            <div class="container-fluid">

                <h2>Vermeerder</h2>
                <table class="table table-hover">
                    <tr>
                        <th>Maand</th>
                        <th>Methode</th>
                        <th>delete</th>
                    </tr>

                    <c:forEach items="${plantVermeerder}" var="plant_vermeerder">
                        <tr>
                            <td>
                                ${plant_vermeerder.getMaand()}
                            </td>
                            <td>
                                ${plant_vermeerder.getNaam()}
                            </td>
                            <td class='clickableRow' href="editvermeerder?plant_vermeerder_id=${plant_vermeerder.getId()}&plant_id=${plantId}"><a href="editvermeerder?plant_vermeerder_id=${plant_vermeerder.getId()}&plant_id=${plantId}"> DELETE </a></td>
                        </tr>
                    </c:forEach>

                    <tr>
                    <form method="post" action="editvermeerder">
                        <input type="hidden" name="plant_id" value="${plantId}">
                        <td>
                            <input id="plant_vermeerder_maand" type="number" name="plant_vermeerder_maand" max="12" min="0" placeholder="0"/>
                        </td>
                        <td>
                            <input id="plant_vermeerder_naam" type="text" name="plant_vermeerder_naam" placeholder="stek"/>
                        </td>
                        <td>
                            <input type="submit" class="btn btn-default" value="vermeerder toevoegen"/>
                        </td>
                    </form>
                    </tr>
                </table>
            </div>

            <div class="container-fluid">
                <h2>Snoei</h2>
                <table class="table table-hover">
                    <tr>
                        <th>Maand</th>
                        <th>Methode</th>
                        <th>delete</th>
                    </tr>

                    <c:forEach items="${plantSnoei}" var="plant_snoei">
                        <tr>
                            <td>
                                ${plant_snoei.getMaand()}
                            </td>
                            <td>
                                ${plant_snoei.getNaam()}
                            </td>
                            <td class='clickableRow' href="editsnoei?plant_snoei_id=${plant_snoei.getId()}&plant_id=${plantId}"><a href="editsnoei?plant_snoei_id=${plant_snoei.getId()}&plant_id=${plantId}"> DELETE </a></td>
                        </tr>
                    </c:forEach>

                    <tr>
                    <form method="post" action="editsnoei">
                        <input type="hidden" name="plant_id" value="${plantId}">
                        <td>
                            <input id="plant_snoei_maand" type="number" name="plant_snoei_maand" max="12" min="0" placeholder="0"/>
                        </td>
                        <td>
                            <input id="plant_snoei_naam"type="text" name="plant_snoei_naam" placeholder="diepe snoei"/>
                        </td>
                        <td>
                            <input class="btn btn-default" type="submit" value="snoei toevoegen"/>
                        </td>
                    </form>
                    </tr>
                </table>
            </div>
            

            <div class="container-fluid">
                <h2>PIT</h2>
                <p class="text-danger">WORK IN PROGRESS</p>
                <table class="table table-hover">
                    <tr>
                        <th>PIT</th>
                        <th>Sub PIT</th>
                        <th>delete</th>
                    </tr>

                    <c:forEach items="${plant.getPit()}" var="pits">
                        <c:forEach items="${pits.getSubPit()}" var="subPit">
                            <tr>
                                <td>
                                    ${pits.getNaam()}
                                </td>
                                <td>
                                    ${subPit.getNaam()}
                                </td>
                                <td class='clickableRow' href="editpit?plantId=${plant.getId()}&pitId=${pits.getId()}&subPitId=${subPit.getId()}"><a href="editpit?plantId=${plant.getId()}&pitId=${pits.getId()}&subPitId=${subPit.getId()}"> DELETE </a></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>

                    <tr>
                    <form name="drop_list" method="post" action="editpit">
                        <input type="hidden" name="plant_id" value="${plantId}"/>
                        <td>
                            <select NAME="Category" onChange="SelectSubCat();" required class="btn btn-default btn-lg dropup">
                                <option disabled="disabled">Kies een PIT</option>
                            </select>
                        </td>
                        <td>
                            <select id="SubCat" NAME="SubCat" required class="btn btn-default btn-lg dropup">
                                <option disabled>Kies een PIT</option>
                            </select>
                        </td>
                        <td>
                            <input class="btn btn-default" type="submit" value="PIT toevoegen"/>
                        </td>
                    </form>
                    </tr>
                </table>

            </div>

        </div>

    </body>
</html>
