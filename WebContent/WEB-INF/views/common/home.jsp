<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/homeStyle.css">
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="container" style="max-width: 1100px;">
        
        <section class="hero-section">
            <div class="hero-content">
                <h1>Another Dimension</h1>
                <p>Scopri una nuova dimensione del design. Esplora la nostra collezione unica di oggetti in stampa 3D pensati per arricchire i tuoi spazi con stile e innovazione.</p>
                <a href="${pageContext.request.contextPath}/ProductControl?action=viewCatalog" class="btn" style="width: auto; padding: 15px 30px; font-size: 1.1rem;">Esplora il Catalogo</a>
            </div>
            <div class="hero-image">
                <img src="${pageContext.request.contextPath}/images/home-hero.png" alt="Home Decor in Stampa 3D">
            </div>
        </section>

        <section class="info-section">
            <div class="info-image">
                <img src="${pageContext.request.contextPath}/images/home-about.png" alt="Processo di Stampa 3D">
            </div>
            <div class="info-text">
                <h2>Oltre l'ordinario</h2>
                <p>Nati dalla passione per la tecnologia e il design, ci dedichiamo alla creazione di elementi di Home Decor esclusivi. La stampa 3D ci permette di superare i limiti delle produzioni tradizionali, offrendo forme e geometrie impossibili da realizzare altrimenti.</p>
                <p>Ogni nostro pezzo non è solo un oggetto, ma il risultato di un processo innovativo che unisce estetica minimale e funzionalità avanzata.</p>
            </div>
        </section>

        <section class="info-section reverse">
            <div class="info-image">
                <img src="${pageContext.request.contextPath}/images/home-custom.png" alt="Prodotti Personalizzati">
            </div>
            <div class="info-text">
                <h2>Creato apposta per te</h2>
                <p>Non offriamo solo articoli standard, ma un'esperienza su misura. Grazie al Print on Demand, molti dei nostri prodotti possono essere personalizzati direttamente da te.</p>
                <p>Scegli tra un'ampia palette di colori o aggiungi dediche e testi personalizzati prima di aggiungere al carrello. Noi ci occuperemo di stampare la tua visione in 3D, trasformando la tua idea in un oggetto reale e tangibile.</p>
            </div>
        </section>

    </div>
    
    <%@ include file="footer.jsp" %>

</body>
</html>