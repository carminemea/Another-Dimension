<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Prodotti - Another Dimension</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css">
</head>
<body>
    <%@ include file="../common/header.jsp" %>

    <div class="container">
        <div class="card">
            <h1 class="text-center">Gestione Catalogo Prodotti</h1>
            
            <div class="text-center" style="margin-bottom: 20px;">
                <a href="${pageContext.request.contextPath}/admin/AdminControl" class="btn" style="width: auto; background-color: #8B8FD0;">Torna al Pannello Amministrazione</a>
            </div>

            <div class="table-responsive">
            <table>
                    <tr style="background-color: #f4f4f4;">
                        <th>ID</th>
                        <th>Immagine</th>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Disponibile</th>
                        <th>Azioni</th>
                    </tr>
                    <c:forEach var="prodotto" items="${prodotti}">
                        <tr>
                            <td>${prodotto.id}</td>
                            <td><img src="${pageContext.request.contextPath}/ImageControl?action=show&id=${prodotto.immagini[0].id}" alt="Img" class="product-thumbnail"></td>
                            <td>${prodotto.nome}</td>
                            <td>€ ${prodotto.prezzo}</td>
                            <td>${prodotto.disponibile ? 'Sì' : 'No'}</td>
                            <td>
                            <c:choose>
                            	<c:when test="${prodotto.disponibile}">
	                                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=delete&id=${prodotto.id}" class="btn" id="deleteBtn">Elimina</a>
                            	</c:when>
                            	<c:otherwise>
                            		<a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=setAvailable&id=${prodotto.id}" class="btn" id="availableBtn">Rendi Disponibile</a>
                            	</c:otherwise>
                            </c:choose>
                                <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=showUpdateForm&id=${prodotto.id}" class="btn">Modifica</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty prodotti}">
                        <tr>
                            <td colspan="6" class="text-center">Nessun prodotto presente nel catalogo.</td>
                        </tr>
                    </c:if>
            </table>
            </div>
          <a href="${pageContext.request.contextPath}/admin/AdminProductControl?action=showInsertForm" class="btn" id="addBtn">+ Aggiungi Nuovo Prodotto</a>  
        </div>
    </div>
</body>
</html>