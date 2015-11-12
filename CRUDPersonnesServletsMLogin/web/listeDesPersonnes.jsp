<%-- 
    Document   : listeDesPersonnes
    Created on : Nov 12, 2015, 5:39:35 PM
    Author     : ajtene.kurtaliq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="bootstrap/template/navigationBar.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>

<jsp:include page="bootstrap/template/navigationBar.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>

<html>
    <head>
        <meta charset="utf-8">
        <title>Liste des personnes</title>
    </head>
    <body style="background-color:white">
        <h1>Hello World!</h1>
        <div id="wrap">
            <table id="lstPersonnes" cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Adresse</th>
                        <th>Ville</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String nom = request.getParameter("nom");
                        String prenom = request.getParameter("prenom");
                        String adresse = request.getParameter("adresse");
                        String ville = request.getParameter("ville");
                        /*PersonneDAO pdao = new PersonneDAO();
                         Vector<Personne> v = pdao.research(new Personne(nom, prenom, adresse, ville));
                         for (int i = 0; i < v.size(); i++) {
                         Personne p = v.elementAt(i);
                         out.println("<tr>");
                         out.println("<td>" + p.getNom() + "</td>");
                         out.println("<td>" + p.getPrenom() + "</td>");
                         out.println("<td>" + p.getAdresse() + "</td>");
                         out.println("<td>" + p.getVille() + "</td>");
                         out.println("<td>");
                         out.println("<a class=\"btn btn-default btn-lg glyphicon glyphicon-edit\" title=\"Modifier\" href=\"creationPersonne.jsp?mod=" + p.getId() + "\"></a>");
                         out.println("<a class=\"btn btn-default btn-lg glyphicon glyphicon-trash\" title=\"Supprimer\" href=\"ServletFaireEffacementPersonne?suppr=" + p.getId() + "&n=" + p.getNom() + "&p=" + p.getPrenom() + "\" onclick=\"return(confirm('Etes-vous sûr de vouloir supprimer cette entrée?'));\"></a>");
                         out.println("</td>");
                         out.println("</tr>");
                         }*/
                    %>
                </tbody>
            </table>
        </div>


        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">Panel heading</div>
            <div class="panel-body">
                <p>Nom</p>
            </div>

            <!-- Table -->
            <table class="table">
                ...
            </table>
        </div>

    </body>
</html>
