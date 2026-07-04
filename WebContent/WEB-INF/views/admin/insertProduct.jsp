<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto - Admin</title>
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
                    <textarea name="descrizione" id="descrizione" class="input-field" required rows="3"
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
                    <label for="immagine">Immagine Prodotto:</label>
                    <input type="file" name="immagine" id="immagine" class="input-field" accept="image/png, image/jpeg" required
                    onchange="validateFormElem(this, document.getElementById('errorImmagine'), emptyFieldErrorMessage)">
                    <span id="errorImmagine" class="error-msg"></span>
                </div>
                
                <input type="submit" value="Aggiungi Prodotto" class="btn">
            </form>
        </div>
    </div>
</body>
</html>