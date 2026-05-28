<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrati</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>
<body>
	<form action="AuthControl" method="post" class="loginForm" id="regForm" onsubmit="return validate()">
		<input type="hidden" name="action" value="register">
		<div>
			<label for="nome">Nome:</label>
			<input type="text" name="nome" id="nome" required pattern="^[A-z]+$" 
				onchange="validateFormElem(this, document.getElementById('errorNome'), nameOrLastnameErrorMessage)">
			<span id="errorNome"></span>
		</div>
		<div>
			<label for="cognome">Cognome:</label>
			<input type="text" name="cognome" id="cognome" required pattern="^[A-z]+$"
				onchange="validateFormElem(this, document.getElementById('errorCognome'), nameOrLastnameErrorMessage)">
			<span id="errorCognome"></span>
		</div>
		<div>
			<label for="email">Email:</label>
			<input type="email" name="email" id="email" required 
				onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)">
			<span id="errorEmail"></span>
		</div>
		<div>
			<label for="password"> Password:</label>
			<input type="password" name="password" id="password" required pattern=".{8,}"
				onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)">
			<span id="errorPassword"></span>
		</div>
		
		<input type="submit" value="Registrati">
	</form>
	Hai già un account? <a href="<%=request.getContextPath()%>/AuthControl?action=redirectLogin">Accedi</a>
	
</body>
</html>