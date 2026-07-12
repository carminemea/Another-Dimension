<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dettagli Ordine #${ordine.id} - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            
            <div class="dettaglioOrdine">
                <h2>Dettagli Ordine #${ordine.id}</h2>
                <a href="${pageContext.request.contextPath}/UserOrderControl" class="btn" style="width: auto; margin: 0;">Torna agli Ordini</a>
            </div>
            
            <div class="orderInfo">
                <p><strong>Data Ordine:</strong> ${ordine.data}</p>
                <p><strong>Info:</strong> ${cliente.nome} ${cliente.cognome} (${cliente.email})</p>
                <p><strong>Indirizzo Spedizione:</strong> ${ordine.indirizzo}</p>
                <p><strong>Totale Pagato:</strong> € ${ordine.totale}</p>
            </div>

            <h3>Articoli Acquistati</h3>
            <div class="table-responsive">
            <table id="tabellaDettagli">
                    <tr style="background-color: #000; color: #fff;">
                        <th>Cod. Prod.</th>
                        <th>Nome Prodotto</th>
                        <th>Quantità</th>
                        <th>Prezzo Cad.</th>
                        <th>Colore</th>
                        <th>Testo Personalizzato</th>
                    </tr>
                    <c:forEach var="item" items="${ordine.composizioni}">
                        <tr>
                            <td>${item.idProdotto}</td>
                            <td>${prodottiMap[item.idProdotto].nome}</td>
                            <td>${item.quantita}</td>
                            <td>€ ${item.prezzoAcquisto}</td>
                            <td>${empty item.coloreScelto ? 'N/A' : item.coloreScelto}</td>
                            <td>${empty item.testoPersonalizzato ? 'Nessuna personalizzazione' : item.testoPersonalizzato}</td>
                        </tr>
                    </c:forEach>
            </table>
            </div>
        </div>
    </div>
</body>
</html>