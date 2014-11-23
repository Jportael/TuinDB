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