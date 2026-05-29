<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registrati - Another Dimension</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/adminRegistration.js"></script>
</head>
<body>

    <nav>
        <a href="<%=request.getContextPath()%>/Home" class="nav-link">HOME</a>
    </nav>

    <div class="container">
        <div class="card">
            <h2>Registrati</h2>
            
            <form action="AuthControl" method="post" id="regForm" onsubmit="return validate()" novalidate>
                <input type="hidden" name="action" value="register">
                
                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome" class="input-field" required pattern="^[A-z]+$" 
                           onchange="validateFormElem(this, document.getElementById('errorNome'), nameOrLastnameErrorMessage)">
                    <span id="errorNome" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="cognome">Cognome:</label>
                    <input type="text" name="cognome" id="cognome" class="input-field" required pattern="^[A-z]+$"
                           onchange="validateFormElem(this, document.getElementById('errorCognome'), nameOrLastnameErrorMessage)">
                    <span id="errorCognome" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" class="input-field" required 
                           onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)">
                    <span id="errorEmail" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" class="input-field" required pattern=".{8,}"
                           onchange="validateFormElem(this, document.getElementById('errorPassword'), passwordErrorMessage)">
                    <span id="errorPassword" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                	<label for="admin">Admin:</label>
                	<input type="checkbox" name="admin" id="admin" onchange="insertMasterPassword(this)">
                </div>
                
                <div class="form-group" id="masterPasswordGroup" style="display: none;">
                    <label for="masterPassword">Master Password:</label>
                    <input type="password" name="masterPassword" id="masterPassword" class="input-field"
                    onchange="validateFormElem(this, document.getElementById('errorMasterPassword'), passwordErrorMessage)">
                    <span id="errorMasterPassword" class="error-msg"></span>
                </div>
                
                <input type="submit" value="Registrati" class="btn">
            </form>
            
            <p class="text-center">
                Hai già un account? <a href="<%=request.getContextPath()%>/AuthControl?action=redirectLogin" class="text-link">Accedi</a>
            </p>
        </div>
    </div>

</body>
</html>