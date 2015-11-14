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

<%
    /* Vérification que l'utilisateur est passé par le login et n'accède pas
     * directement à la page "index.jsp" dans URL. En cas de fraude,
     * retour au login et on invalide la session en cours avec la servlet logout.
     */
    if (!HtmlHttpUtils.isAuthenticate(request)) {
        response.sendRedirect("ServletLogout");
    }
%>

<%! HttpSession session;
    private ArrayList<Personne> listePers;
%>
<%  session = request.getSession(true);
    listePers = new ArrayList<Personne>();

    // Initialiser la liste de client ou la récupérer de la session
    if (session.getAttribute("clients") == null) {
        listePers.addAll(PersonneDAO.findAll());
    } else {
        listePers.addAll((ArrayList) session.getAttribute("personnes"));

    }

%> 

<jsp:include page="bootstrap/template/Menu.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>

<html>
    <head>
        <meta charset="utf-8">
        <title>Liste des personnes</title>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

        <!-- DATATABLE-->
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.6/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.6/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/plug-ins/1.10.10/i18n/French.json"></script>
        <!-- DATATABLE-->


        <script>
            jQuery(document).ready(function () {
                $('#lstPersonnes').DataTable({
                    "pageLength": 10,
                    "language": {
                        "sProcessing": "Traitement en cours...",
                        "sSearch": "Rechercher&nbsp;:",
                        "sLengthMenu": "Afficher _MENU_ &eacute;l&eacute;ments",
                        "sInfo": "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
                        "sInfoEmpty": "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
                        "sInfoFiltered": "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                        "sInfoPostFix": "",
                        "sLoadingRecords": "Chargement en cours...",
                        "sZeroRecords": "Aucun &eacute;l&eacute;ment &agrave; afficher",
                        "sEmptyTable": "Aucune donnée disponible dans le tableau",
                        "oPaginate": {
                            "sFirst": "Premier",
                            "sPrevious": "Pr&eacute;c&eacute;dent",
                            "sNext": "Suivant",
                            "sLast": "Dernier"
                        },
                        "oAria": {
                            "sSortAscending": ": activer pour trier la colonne par ordre croissant",
                            "sSortDescending": ": activer pour trier la colonne par ordre décroissant"
                        }
                    }

                });

            });
        </script>
    </head>

    <body style="background-color:white;">
        <div id="wrap">
            <div class="container">
                <table id="lstPersonnes" class="datatable table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Ville</th>
                            <th>Adresse</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Personne pers : listePers) { %> 
                        <tr class="lignePersonne" id="<% out.print(pers.getId()); %>">

                            <td>  <% out.print(pers.getNom()); %> </td>
                            <td>  <% out.print(pers.getPrenom()); %> </td>  
                            <td>  <% out.print(pers.getVille()); %> </td>
                            <td>  <% out.print(pers.getAdresse()); %> </td>

                            <td class=" dt-body-center"><a class="btn btn-default btn-lg glyphicon glyphicon-edit" title="Modifier" href="ServletMAJPersonne"></a>
                                <a class="btn btn-default btn-lg glyphicon glyphicon-trash" title="Supprimer" href="ServletEffacerPersonne" onclick="return(confirm('Etes-vous sûr de vouloir supprimer cette personne ?'))"></a>

                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
