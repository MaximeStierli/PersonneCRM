<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta charset="utf-8">
<title>Arc'People</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Connexion à mon application">
<link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<!-- ci-dessous notre fichier CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/app.css" />
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato:400,700,300" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<%
    String active = request.getParameter("url");
    //int nbEnregistrement = PersonneDAO.nbEnregistrements();

    String activeIndex = "", activeListePersonnes = "", activeAjouterPersonne = "", activeProfil = "", actSrc = "", actHlp = "", actProf = "";

    if (active.equals("/accueil.jsp")) {
        activeIndex = "class=\"active\"";
    } else if (active.equals("/listeDesPersonnes.jsp")) {
        activeListePersonnes = "class=\"active\"";
    } else if (active.equals("/ajouterPersonne.jsp")) {
        activeAjouterPersonne = "class=\"active\"";
    } else if (active.equals("/profil.jsp")) {
        activeProfil = "class=\"active\"";
    } /*else if (active.equals("/rechercherPersonne.jsp")) {
     actSrc = "class=\"active\"";
     } else if (active.equals("/aide.jsp")) {
     actHlp = "class=\"active\"";
     } else if (active.equals("/profil.jsp")) {
     actProf = "class=\"active\"";
     }*/
%>


<nav class="navbar navbar-default" style="border-radius:0 0 0 0;">
    <div class="container-fluid" >
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
           
            <a class="navbar-brand">Arc'People</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li <% out.println(activeIndex);%>><a href="accueil.jsp">Accueil <span class="sr-only">(current)</span></a></li>
                <li <% out.println(activeListePersonnes);%>><a href="listeDesPersonnes.jsp">Liste des personnes</a></li>
                <li <% out.println(activeAjouterPersonne);%>><a href="ajouterPersonne.jsp">Ajouter des personnes</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li <% out.println(activeProfil);%>><a href="profil.jsp">Profil</a></li>
                <li><a href="ServletLogout">Déconnexion</a></li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>