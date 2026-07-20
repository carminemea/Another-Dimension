<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Carrello - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <script src="${pageContext.request.contextPath}/scripts/cartAjax.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/validate.js"></script>
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
        								<button type="button" class="btn-qty" onclick="aggiornaCarrello(this, 'decrease', '${item.idProdotto}', '${item.coloreScelto}', '${item.testoPersonalizzato}')">-</button>
        
        								<span class="item-quantity">${item.quantita}</span>
        
        								<button type="button" class="btn-qty" onclick="aggiornaCarrello(this, 'increase', '${item.idProdotto}', '${item.coloreScelto}', '${item.testoPersonalizzato}')">+</button>
    								</div>
    
    								<button type="button" class="btn-remove" onclick="aggiornaCarrello(this, 'delete', '${item.idProdotto}', '${item.coloreScelto}', '${item.testoPersonalizzato}')">Rimuovi</button>
								</div>
                            </div>
                        </c:forEach>
                        
                    </div>
                    
                    <div class="cart-summary">
                        <h3>Totale: <span class="cart-total-price" id="cart-total-price">€ ${sessionScope.carrello.totalPrice}</span></h3>
                        
                        <div class="checkout-section">
                            <c:choose>
                                <c:when test="${not empty sessionScope.utente}">
                                    <div class="checkout-form-container">
                                        <h3 style="margin-bottom: 15px;">Dettagli Spedizione e Pagamento</h3>
                                        
                                        <form action="${pageContext.request.contextPath}/CheckoutControl" method="POST" id="checkoutForm" onsubmit="return validateCheckout()" novalidate>
                                            <div class="form-group">
                                                <label for="indirizzo">Indirizzo di Spedizione:</label>
                                                <input type="text" name="indirizzo" id="indirizzo" class="input-field" required 
                                                       onchange="validateFormElem(this, document.getElementById('errorIndirizzo'), emptyFieldErrorMessage)">
                                                <span id="errorIndirizzo" class="error-msg"></span>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label for="cartaCredito">Numero Carta di Credito (fittizio):</label>
                                                <input type="text" name="cartaCredito" id="cartaCredito" class="input-field" required pattern="^\d{16}$" maxlength="16" placeholder="Es. 1234567812345678"
                                                       onchange="validateFormElem(this, document.getElementById('errorCarta'), 'Inserire esattamente 16 cifre valide')">
                                                <span id="errorCarta" class="error-msg"></span>
                                            </div>
                                            
                                            <button type="submit" class="btn btn-checkout">Conferma Ordine e Paga</button>
                                        </form>
                                    </div>
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