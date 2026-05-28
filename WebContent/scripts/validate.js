let count = 1;
const nameOrLastnameErrorMessage = "Questo campo deve essere composto da solo lettere";
const emailErrorMessage = "L'email dovrebbe essere di questo tipo: username@domain.ext";
const emptyFieldErrorMessage = "Questo campo non può essere vuoto"
const passwordErrorMessage = "La password deve contenere..."

function validateFormElem(formElem, span, errorMessage) {
	if(formElem.checkValidity()){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.style.color = "red";
	if (formElem.validity.valueMissing){
		span.innerHTML = emptyFieldErrorMessage;
	} else {
		span.innerHTML = errorMessage;
	}
	return false;
}


function validate() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanNome = document.getElementById("errorNome");
	if(!validateFormElem(form.nome, spanNome, nameOrLastnameErrorMessage)){
		valid = false;
	} 
	let spanCognome = document.getElementById("errorCognome");
	if (!validateFormElem(form.cognome, spanCognome, nameOrLastnameErrorMessage)){
		valid = false;
	}
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, spanEmail, emailErrorMessage)){
		valid = false;
	}
	let spanPassword = document.getElementById("errorEmail");
		if (!validateFormElem(form.password, spanPassword, passwordErrorMessage)){
			valid = false;
		}
	return valid;
}
