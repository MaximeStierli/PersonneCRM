<%-- 
    Document   : listeDesPersonnes
    Created on : Nov 12, 2015, 5:39:35 PM
    Author     : ajtene.kurtaliq
--%>

<%@page import="DAO.PersonneDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Personne"%>
<%@page import="java.util.Vector"%>
<%@page import="servlets.HtmlHttpUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="bootstrap/template/Menu.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>

<html>
    <head>
        <meta charset="utf-8">
        <title>Liste des personnes</title>
    </head>
    <body style="background-color:white">

       
        <div id="wrap">
            <div class="container">
                <%
                  /*  
                     if (request.getParameter("suppr") != null) {
                     out.println("<div class=\"alert alert-success\">Suppression de la personne : " + request.getParameter("p") + " " + request.getParameter("n") + " réussi</div>");
                     } else if (request.getParameter("mod") != null) {
                     out.println("<div class=\"alert alert-success\">Modification de la personne : " + request.getParameter("p") + " " + request.getParameter("n") + " réussi</div>");
                     }
                     if (request.getParameter("bon") != null) {
                  */   
                %>
                <table id="lstPersonnes" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Adresse</th>
                            <th>Ville</th>
                            <th>Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            PersonneDAO pdao = new PersonneDAO();
                            ArrayList<Personne> listePersonne = new ArrayList();
                            listePersonne = pdao.findAll();
                            for (Personne pers : listePersonne) {
                                out.println("<tr>");
                                out.println("<td>" + pers.getNom() + "</td>");
                                out.println("<td>" + pers.getPrenom() + "</td>");
                                out.println("<td>" + pers.getAdresse() + "</td>");
                                out.println("<td>" + pers.getVille() + "</td>");
                                out.println("<td><a class=\"btn btn-default btn-lg glyphicon glyphicon-edit\" title=\"Modifier\" href=\"index.jsp?mod=" + pers.getId() + "\"></a>");
                                out.println("<a class=\"btn btn-default btn-lg glyphicon glyphicon-trash\" title=\"Supprimer\" href=\"index?suppr=" + pers.getId() + "&n=" 
                                        + pers.getNom() + "&p=" 
                                        + pers.getPrenom() + "\" onclick=\"return(confirm('Etes-vous sûr de vouloir supprimer cette personne ?'));\"></a></td>");
                                out.println("</tr>");
                            }

                        %>
                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>
