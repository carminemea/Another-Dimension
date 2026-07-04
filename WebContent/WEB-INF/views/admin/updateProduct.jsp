<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Modifica Prodotto - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validate.js"></script>
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h2>Modifica Prodotto</h2>
            
            <form action="${pageContext.request.contextPath}/admin/AdminProductControl" method="post" id="insertProductForm" onsubmit="return validateProduct()">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${prodotto.id}">
                
                <div class="form-group">
                    <label for="nome">Nome Prodotto:</label>
                    <input type="text" name="nome" id="nome" class="input-field" value="${prodotto.nome}" required>
                    <span id="errorNomeProd" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="descrizione">Descrizione:</label>
                    <textarea name="descrizione" id="descrizione" class="input-field" required rows="4">${prodotto.descrizione}</textarea>
                    <span id="errorDescrizione" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="prezzo">Prezzo (€):</label>
                    <input type="number" name="prezzo" id="prezzo" class="input-field" step="0.01" min="0.01" value="${prodotto.prezzo}" required>
                    <span id="errorPrezzo" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" name="disponibile" value="true" <c:if test="${prodotto.disponibile}">checked</c:if>> Disponibile per l'acquisto
                    </label>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" name="testoPersonalizzabile" value="true" <c:if test="${prodotto.testoPersonalizzabile}">checked</c:if>> Testo Personalizzabile
                    </label>
                </div>

                <input type="hidden" name="immagine" id="immagine" value="dummy">

                <input type="submit" value="Salva Modifiche" class="btn">
            </form>
        </div>
    </div>
</body>
</html>