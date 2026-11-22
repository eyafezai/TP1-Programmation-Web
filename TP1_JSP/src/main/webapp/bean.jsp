<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Person Information</title>
</head>
<body>

    <h1>Person Information</h1>

    <!-- Creation du JavaBean -->
    <jsp:useBean id="person"
        class="isep.lab1.bean.Person"
        scope="request" />

    <!-- Recuperation des donnees du formulaire -->
    <jsp:setProperty name="person" property="*" />

    <!-- Affichage des donnees -->
    <p>First Name:
        <jsp:getProperty name="person" property="firstName" />
    </p>

    <p>Last Name:
        <jsp:getProperty name="person" property="lastName" />
    </p>

    <p>Birth Day:
        <jsp:getProperty name="person" property="birthDay" />
    </p>

    <p>Sex:
        <jsp:getProperty name="person" property="sex" />
    </p>
</body>
</html>