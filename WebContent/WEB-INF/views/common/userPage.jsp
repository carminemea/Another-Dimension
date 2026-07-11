<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>

	<%@ include file="header.jsp" %>
	
	<div class="container text-center">
        <h1>User Page</h1>
        <div class="card">
            <p>Ciao, <b>${sessionScope.utente.nome} ${sessionScope.utente.cognome}</b>!</p>
            <p>Sei loggato come: <b>${sessionScope.role}</b></p>
        </div>
    </div>
	
	<c:if test="${utente.ruolo == 'admin' }">
		<a href="#">Gestione Admin</a>
	</c:if>
</body>
</html>