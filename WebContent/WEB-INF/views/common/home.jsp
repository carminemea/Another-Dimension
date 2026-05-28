<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Another Dimension</title>
</head>
<body>
    <h1>Benvenuto nel nostro E-commerce!</h1>

    <c:if test="${empty sessionScope.utente}">
        <p>
            <a href="${pageContext.request.contextPath}/AuthControl?action=redirectLogin">Accedi</a> | 
            <a href="${pageContext.request.contextPath}/AuthControl?action=redirectRegister">Registrati</a>
        </p>
    </c:if>

    <c:if test="${not empty sessionScope.utente}">
        <p>
            Ciao, <b>${sessionScope.utente.nome} ${sessionScope.utente.cognome}</b>! 
            Sei loggato come: ${sessionScope.role}
        </p>
        <a href="${pageContext.request.contextPath}/AuthControl?action=logout">Esci (Logout)</a>
    </c:if>

</body>
</html>