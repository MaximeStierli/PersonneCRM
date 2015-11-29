<%-- 
    Document   : login
    Created on : 6 janv. 2010, 14:19:14
    Author     : termine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
    <head>

        <meta charset="utf-8">
        <title>Arc'People - Connexion</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Connexion à mon application">
        <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
        <!-- ci-dessous notre fichier CSS -->
        <link rel="stylesheet" type="text/css" href="bootstrap/css/app.css" />
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Lato:400,700,300" />
        <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <table id="page-table"><tr><td id="page-td">
                    <div id="global">

                        <div class="container" >
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="form-form">

                                        <div class="main">


                                            <h1></h1><br /><br /><br />
                                            <h1>Connexion</h1>
                                            <h2>Gest'People : Fast, Powerful and Simple</h2>

                                            <form action="ServletLogin" name="login" role="form" class="form-horizontal" method="post" accept-charset="utf-8">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                         <%--
                                                        <span class="input-group-addon">
                                                            <img src="bootstrap/img/iconPassword.png" width="30px">
                                                        </span>
                                                        --%>
                                                        <input name="username" placeholder="Idenfiant" class="form-control" type="text" id="UserUsername"/>
                                                    </div>
                                                </div> 

                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <%--
                                                        <span class="input-group-addon">
                                                            <img src="bootstrap/img/iconPassword.png" width="30px">
                                                        </span>
                                                        --%>
                                                        <input name="password" placeholder="Mot de passe" class="form-control" type="password" id="UserPassword"/>
                                                    </div>
                                                </div> 


                                                <%
                                                    String loginError = (String) session.getAttribute("loginError");
                                                    if (loginError != null) {
                                                %>
                                                <label class="label-fontcolor-red">Nom d'utilisateur ou mot de passe incorrect !</label>
                                                <%
                                                        //effacer le message de session
                                                        session.setAttribute("loginError", null);
                                                    }
                                                %>

                                                <div class="form-group">
                                                    <div class="col-md-offset-0 col-md-12"><input  class="btn btn-success btn btn-success" type="submit" value="Connexion"/></div>
                                                </div>

                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </td></tr></table>
    </body>
</html>