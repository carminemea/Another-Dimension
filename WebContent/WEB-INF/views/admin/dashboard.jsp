<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin - Prodotti</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card" style="max-width: 100%;">
            <h2>Gestione Catalogo Prodotti</h2>
            <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=showInsertForm" class="btn" style="width: auto; margin-bottom: 20px;">+ Aggiungi Nuovo Prodotto</a>

            <table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
                <thead>
                    <tr style="background-color: #f0f0f0;">
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Disponibile</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="prodotto" items="${prodotti}">
                        <tr>
                            <td>${prodotto.id}</td>
                            <td>${prodotto.nome}</td>
                            <td>€ ${prodotto.prezzo}</td>
                            <td>${prodotto.disponibile ? 'Sì' : 'No'}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=showUpdateForm&id=${prodotto.id}" class="btn" style="padding: 5px 10px; font-size: 14px;">Modifica</a>
                                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=delete&id=${prodotto.id}" style="color: red;">Elimina</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty prodotti}">
                        <tr>
                            <td colspan="5" class="text-center">Nessun prodotto presente nel catalogo.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>