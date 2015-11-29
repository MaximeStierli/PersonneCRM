<%@page import="Model.Personne"%>
<%@page import="DAO.PersonneDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="bootstrap/template/headerApp.jsp">
    <jsp:param name="typePage" value="standard" />
</jsp:include>

<jsp:include page="bootstrap/template/Menu.jsp">
    <jsp:param name="url" value="<%=request.getServletPath()%>" />
</jsp:include>


<link href="bootstrap/css/chat.css" rel="stylesheet" />
<body style="background-color:white">
    <div class="container" style="float:left">
        <div class="row">
            <div class="col-md-5">
                <div class="panel panel-primary">
                    <div class="panel-heading" id="accordion">
                        <span class="glyphicon glyphicon-comment"></span> Chat (1)
                        <div class="btn-group pull-right">
                            <a type="button" class="btn btn-default btn-xs" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </a>
                        </div>
                    </div>
                    <p>Bonjour</p>

                    <div class="panel-body">
                        <ul class="chat">
                            <li class="left clearfix"><span class="chat-img pull-left">
                                    <img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />
                                </span>
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <strong class="primary-font">Ajtene Kurtaliqi</strong> <small class="pull-right text-muted">
                                            <span class="glyphicon glyphicon-time"></span>12 mins ago</small>
                                    </div>
                                    <p>
                                        Nous sommes encore en train de travailler en ce moment... Apéro plus tard ?
                                    </p>
                                </div>
                            </li>

                        </ul>
                    </div>
                    <div class="panel-footer">
                        <div class="input-group">
                            <form action="ServletCommentaire">
                                <input name ="commentaire" id="btn-input" type="text" class="form-control input-sm" placeholder="Type your message here..." />
                                
                                <span class="input-group-btn">

                                    <button class="btn btn-warning btn-sm" id="btn-chat">
                                        Send</button>
                                </span>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>