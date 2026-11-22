<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Scriptlet</title>
</head>
<body>
	<h1>Informations du serveur</h1>

    <%
        java.util.Date date = new java.util.Date();

        out.println("Date et heure actuelles : " + date);

        out.println("<br>");

        out.println("Nom de l'hote distant : "
                    + request.getRemoteHost());
    %>
</body>
</html>