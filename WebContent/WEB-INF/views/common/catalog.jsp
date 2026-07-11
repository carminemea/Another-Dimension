<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Catalogo - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/catalog.css"> </head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container" style="max-width: 1200px; margin: 0 auto;">
        <h1 class="text-center">I Nostri Prodotti</h1>

        <div class="product-grid">
            <c:forEach var="prodotto" items="${prodotti}">
                <a href="${pageContext.request.contextPath}/ProductControl?action=viewProduct&id=${prodotto.id}" class="product-card-link">
                    <div class="product-card">
                        <div class="product-image">
                            <c:if test="${not empty prodotto.immagini}">
                                <img src="${pageContext.request.contextPath}/ImageControl?action=show&id=${prodotto.immagini[0].id}" alt="<c:out value="${prodotto.nome}"/>">
                            </c:if>
                        </div>
                        <div class="product-info">
                            <h3 class="product-title"><c:out value="${prodotto.nome}"/></h3>
                            <p class="product-price">&euro; ${prodotto.prezzo}</p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</body>
</html>