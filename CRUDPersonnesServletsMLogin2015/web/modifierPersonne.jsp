<%-- 
    Document   : modifierPersonne
    Created on : 24 nov. 2015, 09:27:20
    Author     : ajtene.kurtaliq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="bootstrap/template/headerApp.jsp">
    <jsp:param name="typePage" value="standard" />
</jsp:include>

<jsp:include page="bootstrap/template/Menu.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier une personne</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="bootstrap/css/chat.css" rel="stylesheet" />
        <script src='nprogress.js'></script>
        <link rel='stylesheet' href='bootstrap/css/nprogress.css'/>
        <script src="/js/jquery-2.1.1.min.js"></script>
        <script src="bootstrap/js/nprogress.js"></script>
    </head>
    <script>
        $('body').show();
        $('.version').text(NProgress.version);
        NProgress.start();
        setTimeout(function () {
            NProgress.done();
            $('.fade').removeClass('out');
        }, 1000);
        $("#b-0").click(function () {
            NProgress.start();
        });
        $("#b-40").click(function () {
            NProgress.set(0.4);
        });
        $("#b-inc").click(function () {
            NProgress.inc();
        });
    </script>
    <body>
    <center>
        <form action="ServletFaireMAJPersonne" name="modifierPersonne" role="form" class="form-horizontal" method="get" accept-charset="utf-8" 
              style="background-color:white; padding-top:20px;padding-right:20px;padding-left:20px;padding-bottom:20px; width:700px;">
            <fieldset>
                <!-- Form Name -->
                <legend style="text-align: left;">Modifier une personne</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="nom" style="display: none;">Id</label>  
                    <div class="col-md-4">
                        <input id="nom" name="id" type="text" class="form-control input-md" value="<%= request.getParameter("id")%>" style="display:none">

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="nom">Nom</label>  
                    <div class="col-md-6">
                        <input id="nom" name="nom" type="text" class="form-control input-md" value="<%= request.getParameter("nom")%>">

                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label" for="prenom">Prénom</label>  
                    <div class="col-md-6">
                        <input id="prenom" name="prenom" type="text" class="form-control input-md" value="<%= request.getParameter("prenom")%>">

                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label" for="adresse">Adresse</label>  
                    <div class="col-md-6">
                        <input id="adresse" name="adresse" type="text" class="form-control input-md" value="<%= request.getParameter("adresse")%>">

                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label" for="Ville">Ville</label>  
                    <div class="col-md-6">
                        <input id="Ville" name="ville" type="text" class="form-control input-md" value="<%= request.getParameter("ville")%>">

                    </div>
                </div>

                <!-- Button -->
                </br>
                <div class="form-group" style="">
                    <div class="col-md-offset-0 col-md-10">
                        <input  class="btn btn-primary" type="submit" value="Enregistrer" style="float:right;"/></div>
                </div>

            </fieldset>
        </form>
    </center>
</body>
</html>
<script>
    $("#b-100").click(function () {
        NProgress.done();
    });
</script>

