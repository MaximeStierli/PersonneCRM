<%-- 
    Document   : modefierPwd
    Created on : 20 nov. 2015, 13:23:13
    Author     : yasmine.mabrouk
--%>

<%@page import="Model.Users"%>
<%@page import="DAO.UsersDAO"%>
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
        <form class="form-horizontal"method="post" action="ServletEditerProfil">
            <div  style="width:900px ; height:600px; padding-left: 20px; padding-top: 20px;  margin-left:0px; margin-top: 100px; background: white; ">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Changer mot de passe </legend>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Ancien mot de passe</label>
                        <div class="col-md-4">
                            <input id="Ancien mots passe" name="Ancien_mots_passe" type="password" placeholder="Ancien mot de passe " class="form-control input-md" required="required">

                        </div>
                    </div>

                    <%
                        if (session.getAttribute("editeProfilError1") != null) {
                    %>

                    <div class="alert alert-danger" role="alert" style="margin-left: 110px; margin-right: 300px;" >
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        Ancien mot de passe est incorrect
                    </div>
                    <% }
                    session.setAttribute("editeProfilError1", null);
                    %>
                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Nouveau mot de passe</label>
                        <div class="col-md-4">
                            <input id="passwordinput" name="pwd" type="password" placeholder="Nouveau mot de passe" class="form-control input-md" required="required">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" >Confirmer mot de passe </label>
                        <div class="col-md-4">
                            <input id="passwordinput" name="pwd2" type="password" placeholder="Confirmer nouveau mot de passe" class="form-control input-md" required="required">

                        </div>
                    </div>
                    <%
                        if (session.getAttribute("editeProfilError2") != null) {
                    %>
                   
                    <div class="alert alert-danger" role="alert" style="margin-left: 110px; margin-right: 300px;" >
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        Les mots de passe ne sont pas identiques
                    </div>
                    <% }
                     session.setAttribute("editeProfilError2", null);
                    %>
                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" ></label>
                        <div class="col-md-4">
                            <button id="" name="" class="btn btn-primary">Modifier</button>
                        </div>
                    </div>

                </fieldset>
            </div>
        </form>

    </body>
</html>
