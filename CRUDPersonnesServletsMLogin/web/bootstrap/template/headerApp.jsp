<%-- 
    Document   : header
    Created on : 18 oct. 2013, 15:28:03
    Author     : Dyhia Dib - Ajtene Kurtaliqi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="fr">
    <head>
        <meta charset="utf-8" />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <%
            if (request.getParameter("typePage").equals("standard")) {
        %>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />

        <!-- Common styles -->
        <link href="bootstrap/css/common.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="bootstrap/css/index.css" rel="stylesheet" />

        <link href="bootstrap/js/libs/bootstrapdatatable/css/datatables.css" rel="stylesheet" />

        <script src="bootstrap/js/libs/jquery-1.9.0/jquery.min.js"></script>
        <script src="bootstrap/js/libs/twitter-bootstrap-3.0.0/bootstrap.js"></script>
        <script src="bootstrap/js/libs/datatables-1.9.4/jquery.dataTables.min.js"></script>
        <script src="bootstrap/js/libs/bootstrapdatatable/js/datatables.js"></script>
        <script src="bootstrap/js/libs/bootbox/bootbox.min.js"></script>
        <script src="bootstrap/js/include/tpl-header-normal-inc.js"></script>

        <%  } else if (request.getParameter("typePage").equals("login")) {
        %>
        <title>Formulaire de connexion</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Common styles -->
        <link href="bootstrap/css/common.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="bootstrap/css/signin.css" rel="stylesheet">

        <script src="bootstrap/js/libs/jquery-1.9.0/jquery.min.js"></script>
        <script src="bootstrap/js/libs/twitter-bootstrap-3.0.0/bootstrap.js"></script>
        <script src="bootstrap/js/libs/jquery-validate-1.10.0/jquery.validate.min.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="bootstrap/js/include/tpl-header-login-inc.js"></script>
    </head>
     <body style="background-color:white">
        <% }%>