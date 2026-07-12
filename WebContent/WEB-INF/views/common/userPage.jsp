<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Il tuo Account - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>

    <%@ include file="header.jsp" %>
    
    <div class="container text-center">
        <h1 style="margin-bottom: 20px">Il tuo Account</h1>
        <div class="card">
            <h2 style="margin-bottom: 10px">Dati Personali</h2>
            <p>Nome: <b>${sessionScope.utente.nome} ${sessionScope.utente.cognome}</b></p>
            <p>Email: <b>${sessionScope.utente.email}</b></p>
            <p>Tipo Account: <b>${sessionScope.role}</b></p>
            
            <hr style="margin: 20px 0; border: 0; border-top: 1px solid #ccc;">
            
            <h2 style="margin-bottom: 10px">I tuoi Acquisti</h2>
            <!-- TO DO -->
            <a href="${pageContext.request.contextPath}/UserOrderControl?action=viewOrders" class="btn" style="width: auto;">Visualizza i Miei Ordini</a>
            
            <c:if test="${sessionScope.role == 'admin'}">
                <hr style="margin: 20px 0; border: 0; border-top: 1px solid #ccc;">
                <h2 style="margin-bottom: 10px">Pannello di Amministrazione</h2>
                <p>Accedi agli strumenti di gestione del sito e del catalogo.</p>
                <a href="${pageContext.request.contextPath}/admin/AdminControl" class="btn" style="width: auto; background-color: #D12335;">Gestione Admin</a>
            </c:if>
        </div>
    </div>
    
</body>
</html>