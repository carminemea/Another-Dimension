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

            <div style="background-color: #f4f4f4; padding: 20px; border-radius: 8px; margin-bottom: 30px;">
                <h3 style="margin-top: 0;">Aggiungi Nuovo Colore</h3>
                <form action="${pageContext.request.contextPath}/admin/AdminColorControl" method="post" style="display: flex; gap: 15px; align-items: flex-end; flex-wrap: wrap;">
                    <input type="hidden" name="action" value="insert">
                    
                    <div class="form-group" style="margin-bottom: 0; flex-grow: 1;">
                        <label for="nome">Nome Colore:</label>
                        <input type="text" name="nome" id="nome" class="input-field" required>
                    </div>
                    
                    <div class="form-group" style="margin-bottom: 0;">
                        <label for="codiceHex">Seleziona Tinta:</label>
                        <input type="color" name="codiceHex" id="codiceHex" required style="height: 40px; width: 60px; cursor: pointer; border: 1px solid #ccc; border-radius: 4px; padding: 0;">
                    </div>
                    
                    <input type="submit" value="Salva Colore" class="btn" style="width: auto; margin: 0; margin-bottom: 2px;">
                </form>
            </div>

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
                            <div style="width: 30px; height: 30px; background-color: ${colore.codiceHex}; margin: 0 auto; border: 1px solid #ccc; border-radius: 4px;"></div>
                        </td>
                        <td>${colore.nome}</td>
                        <td>${colore.codiceHex}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/AdminColorControl?action=delete&id=${colore.id}" class="btn" id="deleteBtn" style="margin-bottom: 0;">Elimina</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty colori}">
                    <tr>
                        <td colspan="5" class="text-center" style="padding: 20px;">Nessun colore presente nel catalogo. Inizia ad aggiungerne uno!</td>
                    </tr>
                </c:if>
            </table>
            
        </div>
    </div>
</body>
</html>