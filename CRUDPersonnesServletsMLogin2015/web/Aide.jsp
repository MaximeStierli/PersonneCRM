<%-- 
    Document   : Aide
    Created on : 29 nov. 2015, 15:08:47
    Author     : yasmine.mabrouk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="bootstrap/template/Menu.jsp">
        <jsp:param name="url" value="<%=request.getServletPath()%>" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 s>Page d'aide</h1>
        <h3>Question: comment ajouter une personne</h3>
        
    </body>
</html>
