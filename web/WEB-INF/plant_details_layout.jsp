<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant details:</title>
        <jsp:include page="head.jsp"/>
    </head>
    <body>
        <h1>Details</h1>
        <h2>Vermeerder</h2>
        <div class="container-fluid">

            <form class="form-horizontal" role="form" action="00000000000000000000000" method="post">

                <div class="form-group">
                    <label for="plant_vermeerder_maand" class="col-sm-2 control-label">Maand:</label>
                    <div class="col-sm-10">
                        <input id="plant_vermeerder_maand" class="form-control" type="number" name="plant_vermeerder_maand" max="12" min="1" placeholder="0"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="plant_vermeerder_naam" class="col-sm-2 control-label">Methode:</label>
                    <div class="col-sm-10">
                        <input id="plant_vermeerder_naam" class="form-control" type="text" name="plant_vermeerder_naam" placeholder="stek"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type ="submit" class="btn btn-default" value="wijzig"/>
                    </div>
                </div>
            </form>
        </div>
        <h2>Snoei</h2>

        <h2>aankoop</h2>

        <h2>PIT</h2>




    </body>
</html>
