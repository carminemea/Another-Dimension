<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Carrello - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>

    <%@ include file="header.jsp" %>
    
    <div class="container">
        <div class="card cart-card">
            <h1 class="text-center">Carrello</h1>
            
            <c:choose>
                <c:when test="${empty sessionScope.carrello.prodotti}">
                    <div class="text-center">
                        <p>Il carrello è vuoto.</p>
                        <a href="${pageContext.request.contextPath}/ProductControl?action=viewCatalog" class="btn btn-primary">Torna al Catalogo</a>
                    </div>
                </c:when>
                
                <c:otherwise>
                    <div class="cart-layout">
                        
                        <c:forEach var="item" items="${sessionScope.carrello.prodotti}">
                            <c:set var="prodotto" value="${prodottiMap[item.idProdotto]}" />
                            
                            <div class="cart-item">
                                <div class="cart-item-image">
                                    <a href="${pageContext.request.contextPath}/ProductControl?action=viewProduct&id=${prodotto.id}">
                                        <c:choose>
                                            <c:when test="${not empty prodotto.immagini}">
                                                <img src="${pageContext.request.contextPath}/ImageControl?action=show&id=${prodotto.immagini[0].id}" alt="${prodotto.nome}">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="img-placeholder">N/A</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </div>
                                
                                <div class="cart-item-details">
                                    <a href="${pageContext.request.contextPath}/ProductControl?action=viewProduct&id=${prodotto.id}" class="cart-item-name">${prodotto.nome}</a>
                                    
                                    <div class="cart-item-options">
                                        <c:if test="${not empty item.coloreScelto}"><span>Colore: ${item.coloreScelto}</span><br></c:if>
                                        <c:if test="${not empty item.testoPersonalizzato}"><span>Testo: <em>${item.testoPersonalizzato}</em></span></c:if>
                                    </div>
                                    
                                    <div class="cart-item-price">€ ${item.prezzoAcquisto} / cad.</div>
                                </div>
                                
                                <div class="cart-item-actions">
                                    <div class="quantity-controls">
                                        <form class="ajax-cart-form" action="${pageContext.request.contextPath}/CartControl" method="POST">
                                            <input type="hidden" name="action" value="decrease">
                                            <input type="hidden" name="idProdotto" value="${item.idProdotto}">
                                            <input type="hidden" name="coloreScelto" value="${item.coloreScelto}">
                                            <input type="hidden" name="testoPersonalizzato" value="${item.testoPersonalizzato}">
                                            <button type="submit" class="btn-qty">-</button>
                                        </form>
                                        
                                        <span class="item-quantity">${item.quantita}</span>
                                        
                                        <form class="ajax-cart-form" action="${pageContext.request.contextPath}/CartControl" method="POST">
                                            <input type="hidden" name="action" value="increase">
                                            <input type="hidden" name="idProdotto" value="${item.idProdotto}">
                                            <input type="hidden" name="coloreScelto" value="${item.coloreScelto}">
                                            <input type="hidden" name="testoPersonalizzato" value="${item.testoPersonalizzato}">
                                            <button type="submit" class="btn-qty">+</button>
                                        </form>
                                    </div>
                                    
                                    <form class="ajax-cart-form" action="${pageContext.request.contextPath}/CartControl" method="POST">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="idProdotto" value="${item.idProdotto}">
                                        <input type="hidden" name="coloreScelto" value="${item.coloreScelto}">
                                        <input type="hidden" name="testoPersonalizzato" value="${item.testoPersonalizzato}">
                                        <button type="submit" class="btn-remove">Rimuovi</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                        
                    </div>
                    
                    <div class="cart-summary">
                        <h3>Totale: <span class="cart-total-price">€ ${sessionScope.carrello.totalPrice}</span></h3>
                        
                        <div class="checkout-section">
                            <c:choose>
                                <c:when test="${not empty sessionScope.utente}">
                                    <a href="${pageContext.request.contextPath}/CheckoutControl" class="btn btn-checkout">Procedi all'Acquisto</a>
                                </c:when>
                                <c:otherwise>
                                    <p class="login-prompt">Devi essere registrato per completare l'ordine.</p>
                                    <a href="${pageContext.request.contextPath}/AuthControl?action=redirectLogin" class="btn btn-login-checkout">Accedi per Acquistare</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    
                </c:otherwise>
            </c:choose>
            
        </div>
    </div>
</body>
</html>