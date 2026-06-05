<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, inital-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/headerStyle.css">
<title>Header</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/headerScript.js"></script>
</head>
<body>
	<nav>
		<ul class="sidebar">
			<li onclick=hideSidebar() ><a href="#"><img src="${pageContext.request.contextPath}/images/close.svg" alt="Close"/></a></li>
			<li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
			<li><a href="#">Print on Demand</a></li>
			<li><a href="#">Prodotti</a></li>
			<li><a href="#"><img src="${pageContext.request.contextPath}/images/cart.svg" alt="Carrello"/></a></li>
			<c:choose>
				<c:when test="${empty sessionScope.utente}">
					<li><a href="${pageContext.request.contextPath}/AuthControl?action=redirectLogin"><img src="${pageContext.request.contextPath}/images/account.svg" alt="Account"/></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/AuthControl?action=logout"><img src="${pageContext.request.contextPath}/images/logout.svg" alt="Logout"/></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<ul>
			<li><a href="${pageContext.request.contextPath}/Home" id="logoHeader"><img src="${pageContext.request.contextPath}/images/logo.svg" alt="Another Dimension" height="125%" /></a></li>
			<li class="hide934"><a href="${pageContext.request.contextPath}/Home">Home</a></li>
			<li class="hide934"><a href="#">Print on Demand</a></li>
			<li class="hide934"><a href="#">Prodotti</a></li>
			<li class="hide934"><a href="#"><img src="${pageContext.request.contextPath}/images/cart.svg" alt="Carrello"/></a></li>
			<c:choose>
				<c:when test="${empty sessionScope.utente }">
					<li class="hide934"><a href="${pageContext.request.contextPath}/AuthControl?action=redirectLogin"><img src="${pageContext.request.contextPath}/images/account.svg" alt="Account"/></a></li>
				</c:when>
				<c:otherwise>
					<span class="welcome-name">Ciao, ${sessionScope.utente.nome}</span>
					<li class="hide934"><a href="${pageContext.request.contextPath}/AuthControl?action=logout"><img src="${pageContext.request.contextPath}/images/logout.svg" alt="Logout"/></a></li>
				</c:otherwise>
			</c:choose>
			<li class="menu-button" onclick=showSidebar() ><a href="#"><img src="${pageContext.request.contextPath}/images/menu.svg" alt="Menu"/></a></li>
		</ul>
	</nav>	
</body>
</html>