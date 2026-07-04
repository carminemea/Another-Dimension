<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dettagli Ordine #${ordine.id} - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card" style="max-width: 100%;">
            
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                <h2>Dettaglio Ordine #${ordine.id}</h2>
                <a href="${pageContext.request.contextPath}/admin/AdminOrderControl" class="btn" style="width: auto; margin: 0; background-color: #666;">Torna agli Ordini</a>
            </div>
            
            <div style="background-color: #f9f9f9; padding: 15px; border-radius: 8px; margin-bottom: 20px;">
                <p><strong>Data Ordine:</strong> ${ordine.data}</p>
                <p><strong>Cliente:</strong> ${cliente.nome} ${cliente.cognome} (${cliente.email})</p>
                <p><strong>Indirizzo Spedizione:</strong> ${ordine.indirizzo}</p>
                <p><strong>Totale Pagato:</strong> € ${ordine.totale}</p>
            </div>

            <h3>Articoli Acquistati</h3>
            <table border="1" style="width: 100%; border-collapse: collapse; text-align: center; margin-top: 15px;">
                <thead>
                    <tr style="background-color: #000; color: #fff;">
                        <th>Cod. Prod.</th>
                        <th>Nome Prodotto</th>
                        <th>Quantità</th>
                        <th>Prezzo Cad.</th>
                        <th>Colore</th>
                        <th>Testo Personalizzato (Print On Demand)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${ordine.composizioni}">
                        <tr>
                            <td>${item.idProdotto}</td>
                            <td>${prodottiMap[item.idProdotto].nome}</td>
                            <td>${item.quantita}</td>
                            <td>€ ${item.prezzoAcquisto}</td>
                            <td>${empty item.coloreScelto ? 'N/A' : item.coloreScelto}</td>
                            
                            <td style="font-weight: bold; color: #d9534f;">
                                ${empty item.testoPersonalizzato ? 'Nessuna personalizzazione' : item.testoPersonalizzato}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
        </div>
    </div>
</body>
</html>