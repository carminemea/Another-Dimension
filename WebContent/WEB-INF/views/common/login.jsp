<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accedi - Another Dimension</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validate.js"></script>
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="container">
        <div class="card">
            <h2>Accedi</h2>
            
            <form action="AuthControl" method="post" id="loginForm" onsubmit="return validate()">
                <input type="hidden" name="action" value="login">
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" class="input-field" required 
                           onchange="validateFormElem(this, document.getElementById('errorEmail'), emailErrorMessage)">
                    <span id="errorEmail" class="error-msg"></span>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" class="input-field" required>
                </div>
                
                <input type="submit" value="Accedi" class="btn">
            </form>
            
            <p class="text-center" style="color: red;">${error}</p>
            
            <p class="text-center">
                Non hai un account? <a href="<%=request.getContextPath()%>/AuthControl?action=redirectRegister" class="text-link">Registrati</a>
            </p>
        </div>
    </div>

</body>
</html>