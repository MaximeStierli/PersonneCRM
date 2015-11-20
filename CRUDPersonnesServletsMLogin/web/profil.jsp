<%-- 
    Document   : profil
    Created on : 17 nov. 2015, 10:14:15
    Author     : yasmine.mabrouk
--%>

<%@page import="DAO.AjoutDAO"%>
<%@page import="servlets.HtmlHttpUtils"%>
<%@page import="java.awt.Image"%>
<%@page import="Model.Users"%>
<%@page import="DAO.UsersDAO"%>
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



    <%
        HttpSession s = request.getSession(true);
        String username = (String) s.getAttribute("username");
        username = username + ".PNG";
        String src = "bootstrap\\img\\" + username;
    %>

    <%
        UsersDAO userDao = new UsersDAO();
        Users user = userDao.select(username);
        //test user1
        // car userDao.select(username) retourne tjrs  null 
        Users user1 = new Users((long) 1, "nom", "adress");
        String name = user1.getUsername();
        String mail = user1.getEmail();

        AjoutDAO ajoutDao = new AjoutDAO();
        int nbPoint = ajoutDao.countAjout(user1.getId());
    %>    
    <form method="post" action="modefierPwd.jsp">
        <div  style="width:900px ; height:600px; padding-left: 20px; padding-top: 20px;  margin-left:0px; margin-top: 100px; background: whitesmoke ">
            <legend>Votre profil </legend>
            <div style="padding-left: 150px;">
                <img  class= "Imagee" src="<%=src%>" alt= "ImageProfil" width="200px" height="200px" />
            <br>
            <br>

            <table style="width:90%; border-color:white ">
                <tr>
                    <td>Nom utilisateur</td>
                    <td><%=name%></td> 
                    <td></td>
                </tr>
                <tr>
                    <td>Adress E-mail</td>
                    <td><%=mail%></td> 
                    <td></td>
                </tr>
                <tr>
                    <td>mots de passe</td>
                    <td>********</td> 
                    <td><div class="col-md-4">
                            <input type="submit" value="modifier MDP" class="btn btn-primary"></input>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Nombre de point</td>
                    <td><%=nbPoint%></td> 
                    <td></td>
                </tr>
            </table> 
            </div>
        </div> 
    </form>



</html>
