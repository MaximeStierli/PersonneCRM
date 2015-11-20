<%-- 
    Document   : modefierPwd
    Created on : 20 nov. 2015, 13:23:13
    Author     : yasmine.mabrouk
--%>

<%@page import="servlets.HtmlHttpUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    /* Vérification que l'utilisateur est passé par le login et n'accède pas
     * directement à la page "index.jsp" dans URL. En cas de fraude,
     * retour au login et on invalide la session en cours avec la servlet logout.
     */
    if (!HtmlHttpUtils.isAuthenticate(request)) {
        response.sendRedirect("ServletLogout");
    }
%>
<html>
    <jsp:include page="bootstrap/template/Menu.jsp">
        <jsp:param name="url" value="<%=request.getServletPath()%>" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/profil.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <form class="form-horizontal"method="post" action="ServletEditeProfil">
        <div  style="width:900px ; height:600px; padding-left: 20px; padding-top: 20px;  margin-left:0px; margin-top: 100px; background: whitesmoke ">
            <fieldset>

                <!-- Form Name -->
                <legend>Changer mots de passe </legend>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="Ancien mots passe">Ancien mots de passe</label>
                    <div class="col-md-4">
                        <input id="Ancien mots passe" name="Ancien mots passe" type="password" placeholder="votre ancien mots de passe " class="form-control input-md">

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="passwordinput">Nouveau mots de passe</label>
                    <div class="col-md-4">
                        <input id="passwordinput" name="pwd" type="password" placeholder="votre nouveau mots de passe" class="form-control input-md">

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="passwordinput">confirmer mots de passe </label>
                    <div class="col-md-4">
                        <input id="passwordinput" name="passwordinput" type="password" placeholder="confirmer votre nouveau mots de passe" class="form-control input-md">

                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for=""></label>
                    <div class="col-md-4">
                        <button id="" name="" class="btn btn-primary">modifier</button>
                    </div>
                </div>

            </fieldset>
        </div>
        </form>

    </body>
</html>
