<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${prodotto.nome}"/> - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/catalog.css">
    <script>
        function changeMainImage(imgUrl) {
            document.getElementById('mainProductImage').src = imgUrl;
        }
    </script>
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container" style="max-width: 1000px;">
        <div class="product-detail-container">
            
            <div class="product-gallery">
                <div class="main-image-wrapper">
                    <c:if test="${not empty prodotto.immagini}">
                        <img id="mainProductImage" src="${pageContext.request.contextPath}/ImageControl?action=show&id=${prodotto.immagini[0].id}" alt="<c:out value="${prodotto.nome}"/>">
                    </c:if>
                </div>
                
                <c:if test="${fn:length(prodotto.immagini) > 1}">
                    <div class="thumbnails">
                        <c:forEach var="img" items="${prodotto.immagini}">
                            <img src="${pageContext.request.contextPath}/ImageControl?action=show&id=${img.id}" 
                                 class="thumb" 
                                 onclick="changeMainImage('${pageContext.request.contextPath}/ImageControl?action=show&id=${img.id}')"
                                 alt="Miniatura">
                        </c:forEach>
                    </div>
                </c:if>
            </div>

            <div class="product-details">
                <h1 class="detail-title"><c:out value="${prodotto.nome}"/></h1>
                <p class="detail-price">&euro; ${prodotto.prezzo}</p>
                <p class="detail-desc"><c:out value="${prodotto.descrizione}"/></p>

                <form action="${pageContext.request.contextPath}/CartControl" method="POST" class="add-to-cart-form">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="idProdotto" value="${prodotto.id}">

                    <div class="form-group">
                        <label for="quantita">Quantità:</label>
                        <input type="number" name="quantita" id="quantita" value="1" min="1" class="input-field" style="width: 80px;" required>
                    </div>

                    <c:if test="${not empty prodotto.colori}">
                        <div class="form-group">
                            <label>Scegli Colore:</label>
                            <div style="display: flex; gap: 15px; flex-wrap: wrap; margin-top: 8px;">
                                <c:forEach var="colore" items="${prodotto.colori}">
                                    <label style="display: flex; align-items: center; gap: 5px; cursor: pointer;">
                                        <input type="radio" name="coloreScelto" value="${colore.nome}" required>
                                        <span class="color-span" style="background-color: ${colore.codiceHex};"></span>
                                        ${colore.nome}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${prodotto.testoPersonalizzabile}">
                        <div class="form-group" style="text-align: left">
                            <label for="testoPersonalizzato">Testo Personalizzato:</label>
                            <input type="text" name="testoPersonalizzato" id="testoPersonalizzato" class="input-field" placeholder="Max 20 caratteri" maxlength="20">
                        </div>
                    </c:if>

                    <button type="submit" class="btn detail-btn">Aggiungi al Carrello</button>
                </form>
            </div>
            
        </div>
    </div>
</body>
</html>