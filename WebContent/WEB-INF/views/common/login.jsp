<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accedi</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>
<body>
		<form action="AuthControl" method="post" class="loginForm" id="loginForm" onsubmit="return validate()">
		<input type="hidden" name="action" value="login">
		<div>
			<label for="email">Email:</label>
			<input type="email" name="email" id="email" required 
				onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)">
			<span id="errorEmail"></span>
		</div>
		<div>
			<label for="password"> Password:</label>
			<input type="password" name="password" id="password" required>
		</div>
		<input type="submit" value="Accedi">
	</form>
	Non hai un account? <a href="<%=request.getContextPath()%>/AuthControl?action=redirectRegister">Registrati</a>
	<p style="color: red;">${error}</p>
</body>
</html>