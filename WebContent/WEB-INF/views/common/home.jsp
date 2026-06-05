<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="container text-center">
        <h1>Benvenuto nel nostro E-commerce!</h1>

        <c:if test="${empty sessionScope.utente}">
            <p>Esplora il nostro catalogo o accedi per iniziare lo shopping.</p>
        </c:if>

        <c:if test="${not empty sessionScope.utente}">
            <div class="card" style="margin-top: 40px;">
                <p>Ciao, <b>${sessionScope.utente.nome} ${sessionScope.utente.cognome}</b>!</p>
                <p>Sei loggato come: <b>${sessionScope.role}</b></p>
            </div>
        </c:if>
    </div>

</body>
</html>