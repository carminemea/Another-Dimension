<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Ordini - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card" style="max-width: 100%;">
            <h2>Gestione Storico Ordini</h2>
            
            <form action="${pageContext.request.contextPath}/admin/AdminOrderControl" method="get" style="margin-bottom: 20px; display: flex; gap: 15px; align-items: flex-end;">
                
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="startDate">Da data:</label>
                    <input type="date" name="startDate" id="startDate" class="input-field" value="${paramStartDate}">
                </div>
                
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="endDate">A data:</label>
                    <input type="date" name="endDate" id="endDate" class="input-field" value="${paramEndDate}">
                </div>
                
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="utenteId">Cliente:</label>
                    <select name="utenteId" id="utenteId" class="input-field">
                        <option value="">Tutti i Clienti</option>
                        <c:forEach var="cliente" items="${utenti}">
                            <option value="${cliente.id}" <c:if test="${cliente.id == paramUtenteId}">selected</c:if>>
                                ${cliente.cognome} ${cliente.nome} (${cliente.email})
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <input type="submit" value="Filtra" class="btn" style="width: auto; margin-top: 0;">
                <a href="${pageContext.request.contextPath}/admin/AdminOrderControl" class="btn" style="background-color: #888; width: auto; margin-top: 0;">Reset</a>
            </form>

            <table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
                <thead>
                    <tr style="background-color: #f0f0f0;">
                        <th>ID Ordine</th>
                        <th>Data</th>
                        <th>ID Cliente</th>
                        <th>Indirizzo Spedizione</th>
                        <th>Totale</th>
                        <th>Dettagli (Print On Demand)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ordine" items="${ordini}">
                        <tr>
                            <td>#${ordine.id}</td>
                            <td>${ordine.data}</td>
                            <td>${ordine.idUtente}</td>
                            <td>${ordine.indirizzo}</td>
                            <td>€ ${ordine.totale}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/AdminOrderControl?action=viewDetails&id=${ordine.id}" class="btn" style="padding: 5px 10px; font-size: 14px;">Dettagli</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty ordini}">
                        <tr>
                            <td colspan="6" class="text-center">Nessun ordine trovato con i filtri selezionati.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>