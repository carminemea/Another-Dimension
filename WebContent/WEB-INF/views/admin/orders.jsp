<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Ordini - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h1 class="text-center">Gestione Ordini</h1>
            
            <div class="text-center" style="margin-bottom: 20px;">
                <a href="${pageContext.request.contextPath}/admin/AdminControl" class="btn" style="width: auto; background-color: #8B8FD0;">Torna al Pannello Amministrazione</a>
            </div>
            
            <form action="${pageContext.request.contextPath}/admin/AdminOrderControl" method="get" id="filterForm">
                
                <div class="form-group">
                    <label for="startDate">Da data:</label>
                    <input type="date" name="startDate" id="startDate" class="input-field" value="${paramStartDate}">
                </div>
                
                <div class="form-group">
                    <label for="endDate">A data:</label>
                    <input type="date" name="endDate" id="endDate" class="input-field" value="${paramEndDate}">
                </div>
                
                <div class="form-group">
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
                
                <div class="buttonGroup">
                	<input type="submit" value="Filtra" class="btn" id="filterBtn">
                	<a href="${pageContext.request.contextPath}/admin/AdminOrderControl" class="btn" id="resetBtn">Reset</a>
                </div>
            </form>
			<div class="table-responsive">
            <table>
                    <tr style="background-color: #f4f4f4;">
                        <th>ID Ordine</th>
                        <th>Data</th>
                        <th>ID Cliente</th>
                        <th>Indirizzo Spedizione</th>
                        <th>Totale</th>
                        <th>Dettagli</th>
                    </tr>
                    <c:forEach var="ordine" items="${ordini}">
                        <tr>
                            <td>#${ordine.id}</td>
                            <td>${ordine.data}</td>
                            <td>${ordine.idUtente}</td>
                            <td>${ordine.indirizzo}</td>
                            <td>€ ${ordine.totale}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/AdminOrderControl?action=viewDetails&id=${ordine.id}" class="btn" style="margin-bottom: 10px;">Dettagli</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty ordini}">
                        <tr>
                            <td colspan="6" class="text-center">Nessun ordine trovato con i filtri selezionati.</td>
                        </tr>
                    </c:if>
            </table>
            </div>
        </div>
    </div>
</body>
</html>