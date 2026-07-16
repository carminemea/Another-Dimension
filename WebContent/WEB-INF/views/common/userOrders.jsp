<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordini - User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h1 class="text-center">Storico Ordini</h1>
            
			<div class="table-responsive">
            <table>
                    <tr style="background-color: #f4f4f4;">
                        <th>ID Ordine</th>
                        <th>Data</th>
                        <th>Indirizzo Spedizione</th>
                        <th>Totale</th>
                        <th>Dettagli</th>
                    </tr>
                    <c:forEach var="ordine" items="${ordini}">
                        <tr>
                            <td>#${ordine.id}</td>
                            <td>${ordine.data}</td>
                            <td>${ordine.indirizzo}</td>
                            <td>€ ${ordine.totale}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/UserOrderControl?action=viewDetails&id=${ordine.id}" class="btn" style="margin-bottom: 10px;">Dettagli</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty ordini}">
                        <tr>
                            <td colspan="6" class="text-center">Nessun ordine presente.</td>
                        </tr>
                    </c:if>
            </table>
            </div>
        </div>
    </div>
</body>
</html>