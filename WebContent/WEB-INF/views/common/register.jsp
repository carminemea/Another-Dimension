<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati</title>
</head>
<body>
	<form action="AuthControl" method="post" class="loginForm">
		<input type="hidden" name="action" value="register">
		<label for="nome"> Nome:<input type="text" name="nome" id="nome" required></label>
		<label for="cognome"> Cognome:<input type="text" name="cognome" id="cognome" required></label>
		<label for="email"> Email:<input type="text" name="email" id="email" required></label>
		<label for="password"> Password:<input type="password" name="password" id="password" required></label>
		<input type="submit" value="Registrati">
	</form>
	Hai già un account? <a href="<%=request.getContextPath()%>/AuthControl?action=redirectLogin">Accedi</a>
</body>
</html>