<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Pannello Admin - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/catalog.css">
</head>
<body>

    <%@ include file="../common/header.jsp" %>

    <div class="container text-center">
        <h1>Pannello di Amministrazione</h1>
        
        <div class="product-grid" style="grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));">
            
            <div class="card">
                <h2>Gestione Prodotti</h2>
                <p>Inserisci, modifica o rimuovi i prodotti in vendita, gestisci immagini e disponibilità.</p>
                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=viewCatalog" class="btn">Vai ai Prodotti</a>
            </div>

            <div class="card">
                <h2>Gestione Colori</h2>
                <p>Aggiungi e rimuovi le varianti di colore associabili ai prodotti stampabili in 3D.</p>
                <a href="${pageContext.request.contextPath}/admin/AdminColorControl?action=viewColori" class="btn">Vai ai Colori</a>
            </div>

            <div class="card">
                <h2>Gestione Ordini</h2>
                <p>Visualizza gli ordini effettuati, le specifiche e le richieste di personalizzazione.</p>
                <a href="${pageContext.request.contextPath}/admin/AdminOrderControl?action=viewOrders" class="btn">Vai agli Ordini</a>
            </div>

        </div>
    </div>

</body>
</html>