<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accedi</title>
</head>
<body>
	<form action="AuthControl" method="post" class="loginForm">
		<input type="hidden" name="action" value="login">
		<label for="email"> Email:<input type="text" name="email" id="email" required></label>
		<label for="password"> Password:<input type="password" name="password" id="password" required></label>
		<input type="submit" value="Accedi">
	</form>
	Non hai un account? <a href="<%=request.getContextPath()%>/AuthControl?action=redirectRegister">Registrati</a>
	<% String error = (String) request.getAttribute("error"); 
   	if(error != null) { %>
    <p style="color: red;"><%= error %></p>
	<% } %>
</body>
</html>