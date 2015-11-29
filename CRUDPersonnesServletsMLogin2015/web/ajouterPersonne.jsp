<%-- 
    Document   : ajouterPersonne
    Created on : 24 nov. 2015, 16:13:11
    Author     : ajtene.kurtaliq
--%>

<%@page import="servlets.HtmlHttpUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    /* Vérification que l'utilisateur est passé par le login et n'accède pas
     * directement à la page "index.jsp" par l'URL.
     */
    if (!HtmlHttpUtils.isAuthenticate(request)) {
        response.sendRedirect("ServletLogout");
    }
%>
<jsp:include page="bootstrap/template/headerApp.jsp">
    <jsp:param name="typePage" value="standard" />
</jsp:include>

<jsp:include page="bootstrap/template/Menu.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter des personnes</title>
        <script src='nprogress.js'></script>
        <link rel='stylesheet' href='bootstrap/css/nprogress.css'/>
        <script src="/js/jquery-2.1.1.min.js"></script>
        <script src="bootstrap/js/nprogress.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
        <form action="ServletCreationPersonne" name="ajouterPersonne" role="form" class="form-horizontal" method="get" accept-charset="utf-8" 
              style="background-color:white; padding-top:20px;padding-right:20px;padding-left:20px;padding-bottom:20px; width:700px;">
            <fieldset>

                <!-- Form Name -->
                <legend style="text-align: left;">Ajouter une personne</legend>
                <br/>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="nom">Nom</label>  
                    <div class="col-md-6">
                        <input id="nom" name="nom" type="text" placeholder="Ex : Smith, Obama, etc." class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="prenom">Prénom</label>  
                    <div class="col-md-6">
                        <input id="prenom" name="prenom" type="text" placeholder="Ex : Marie, John, etc." class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="adresse">Adresse</label>  
                    <div class="col-md-6">
                        <input id="adresse" name="adresse" type="text" placeholder="Ex : Rue de la Promenade 4" class="form-control input-md" required="">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="ville">Ville</label>  
                    <div class="col-md-6">
                        <input id="ville" name="ville" type="text" placeholder="Ex : 7500 Paris" class="form-control input-md" required="">

                    </div>
                </div>

                </br>
                <div class="form-group" style="">
                    <div class="col-md-offset-0 col-md-10">
                        <input  class="btn btn-primary" type="submit" value="Valider" style="float:right;"/></div>
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
