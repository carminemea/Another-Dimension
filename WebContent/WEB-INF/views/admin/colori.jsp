<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Colori - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h1 class="text-center">Gestione Colori</h1>

            <div class="text-center" style="margin-bottom: 20px;">
                <a href="${pageContext.request.contextPath}/admin/AdminControl" class="btn" style="width: auto; background-color: #8B8FD0;">Torna al Pannello Amministrazione</a>
            </div>

            <div class="color-form-container">
                <h3>Aggiungi Nuovo Colore</h3>
                <form action="${pageContext.request.contextPath}/admin/AdminColorControl" method="post" id="colorInsertForm">
                    <input type="hidden" name="action" value="insert">
                    
                    <div class="form-group" style="flex-grow : 1">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" id="nome" class="input-field" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="codiceHex">Seleziona Tinta:</label>
                        <input type="color" name="codiceHex" id="codiceHex" class="color-picker-input" required>
                    </div>
                    
                    <input type="submit" value="Salva Colore" class="btn" style="width : auto">
                </form>
            </div>
            <div class="table-responsive">
            <table>
                <tr style="background-color: #000; color: #fff;">
                    <th>ID</th>
                    <th>Anteprima</th>
                    <th>Nome</th>
                    <th>Codice Hex</th>
                    <th>Azioni</th>
                </tr>
                <c:forEach var="colore" items="${colori}">
                    <tr>
                        <td>${colore.id}</td>
                        <td>
                            <div class="color-preview-box" style="background-color: ${colore.codiceHex};"></div>
                        </td>
                        <td>${colore.nome}</td>
                        <td>${colore.codiceHex}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/AdminColorControl?action=delete&id=${colore.id}" class="btn" id="deleteBtn">Elimina</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty colori}">
                    <tr>
                        <td colspan="5" style="padding:20px">Nessun colore presente nel catalogo. Inizia ad aggiungerne uno!</td>
                    </tr>
                </c:if>
            </table>
            </div>
        </div>
    </div>
</body>
</html>