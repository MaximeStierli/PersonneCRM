<%-- 
    Document   : profil
    Created on : 17 nov. 2015, 10:14:15
    Author     : yasmine.mabrouk
--%>

<%@page import="java.awt.Image"%>
<%@page import="Model.Users"%>
<%@page import="DAO.UsersDAO"%>
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

    
        
        <%
        HttpSession s = request.getSession(true);
        String username = (String)s.getAttribute("username");
        username = "iconPersonLogin.png";
        %>
        <--
        UsersDAO usersDAO = new UsersDAO();
        Users utilusateur = usersDAO.select(username);
        Image img = usersDAO.getphotoById(utilusateur.getId());
        -->
        <form action ="maservlet">
            <h1><%=username%></h1>
        </form>
        <img  class= "Image" src= "bootstrap\img\iconPersonLogin.png" alt= "ImageProfil" > 
        
    
</html>
