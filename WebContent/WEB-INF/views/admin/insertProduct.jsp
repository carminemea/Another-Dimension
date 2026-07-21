<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validate.js"></script>
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h2>Inserisci Nuovo Prodotto</h2>
            
            <form action="${pageContext.request.contextPath}/admin/AdminProductControl" method="post" id="insertProductForm" enctype="multipart/form-data" onsubmit="return validateProduct()">
                <input type="hidden" name="action" value="insert">
                
                <div class="form-group">
                    <label for="nome">Nome Prodotto:</label>
                    <input type="text" name="nome" id="nome" class="input-field" required
                    onchange="validateFormElem(this, document.getElementById('errorNomeProd'), textErrorMessage)">
                    <span id="errorNomeProd" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="descrizione">Descrizione:</label>
                    <textarea name="descrizione" id="descrizione" class="input-field" required rows="1"
                    onchange="validateFormElem(this, document.getElementById('errorDescrizione'), textErrorMessage)"></textarea>
                    <span id="errorDescrizione" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="prezzo">Prezzo (€):</label>
                    <input type="number" name="prezzo" id="prezzo" class="input-field" step="0.01" min="0.01" required
                    onchange="validateFormElem(this, document.getElementById('errorPrezzo'), priceErrorMessage)">
                    <span id="errorPrezzo" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" name="disponibile" value="true" checked> Disponibile per l'acquisto
                    </label>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" name="testoPersonalizzabile" value="true"> Testo Personalizzabile
                    </label>
                </div>

                <div class="form-group">
                    <label>Colori Disponibili:</label>
                    <div style="display: flex; gap: 15px; flex-wrap: wrap; margin-top: 8px;">
                        <c:forEach var="colore" items="${coloriDisponibili}">
                            <label style="display: flex; align-items: center; gap: 5px; cursor: pointer; font-weight: normal;">
                                <input type="checkbox" name="colori" value="${colore.id}">
                                <span style="display: inline-block; width: 16px; height: 16px; background-color: ${colore.codiceHex}; border: 1px solid #ccc; border-radius: 4px;"></span>
                                ${colore.nome}
                            </label>
                        </c:forEach>
                        <c:if test="${empty coloriDisponibili}">
                            <span style="color: #777; font-style: italic; font-size: 14px;">Nessun colore presente nel database.</span>
                        </c:if>
                    </div>
                </div>

                <div class="form-group">
    				<label for="copertina">Immagine di Copertina:</label>
    				<input type="file" name="copertina" id="copertina" class="input-field" accept="image/png, image/jpeg" required
    					onchange="validateFormElem(this, document.getElementById('errorCopertina'), emptyFieldErrorMessage)">
    				<span id="errorCopertina" class="error-msg"></span>
				</div>

				<div class="form-group">
    				<label for="immagini">Immagini Galleria:</label>
    				<input type="file" name="immagini" id="immagini" class="input-field" accept="image/png, image/jpeg" multiple>
				</div>
                
                <input type="submit" value="Aggiungi Prodotto" class="btn">
            </form>
            <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=viewCatalog" class="btn">Annulla</a>
        </div>
    </div>
</body>
</html>