<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin - Prodotti</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h1 class="text-center">Gestione Catalogo Prodotti</h1>

            <table>
                    <tr style="background-color: #f4f4f4;">
                        <th>ID</th>
                        <th>Immagine</th>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Disponibile</th>
                        <th>Azioni</th>
                    </tr>
                    <c:forEach var="prodotto" items="${prodotti}">
                        <tr>
                            <td>${prodotto.id}</td>
                            <td>${prodotto.immagini}</td> <!-- Aggiungere immagini con servlet apposita -->
                            <td>${prodotto.nome}</td>
                            <td>€ ${prodotto.prezzo}</td>
                            <td>${prodotto.disponibile ? 'Sì' : 'No'}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=showUpdateForm&id=${prodotto.id}" class="btn">Modifica</a>
                                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=delete&id=${prodotto.id}" class="btn" id="deleteBtn">Elimina</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty prodotti}">
                        <tr>
                            <td colspan="5" class="text-center">Nessun prodotto presente nel catalogo.</td>
                        </tr>
                    </c:if>
            </table>
          <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=showInsertForm" class="btn" id="addBtn">+ Aggiungi Nuovo Prodotto</a>  
        </div>
    </div>
</body>
</html>