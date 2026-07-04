const nameOrLastnameErrorMessage = "Questo campo deve essere composto da solo lettere";
const emailErrorMessage = "L'email dovrebbe essere di questo tipo: username@domain.ext";
const emptyFieldErrorMessage = "Questo campo non può essere vuoto";
const passwordErrorMessage = "La password deve contenere almeno 8 caratteri";

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
	let spanPassword = document.getElementById("errorPassword");
	if (!validateFormElem(form.password, spanPassword, passwordErrorMessage)){
		valid = false;
	}
	
	if(document.getElementById("admin").checked) {
		let spanMasterPassword = document.getElementById("errorMasterPassword");
		if(!validateFormElem(form.masterPassword, spanMasterPassword, passwordErrorMessage))
			valid = false;
	}
		
	return valid;
}

/*Sezione per MasterPassword*/

function insertMasterPassword(elem) {
	
	if(elem.checked) {
		let container = document.getElementById("regForm");
		let div = document.createElement("div");
		div.id = "masterPasswordDiv";
		div.classList.add("form-group");
		
		let label = document.createElement("label");
		label.htmlFor = "masterPassword";
		label.appendChild(document.createTextNode("Master Password:"));
		div.appendChild(label);
		
		let input = document.createElement("input");
		input.type = "password";
		input.name = "masterPassword";
		input.id = "masterPassword";
		input.required = true;
		input.classList.add("input-field");
		div.appendChild(input);
		
		let span = document.createElement("span");
		span.id = "errorMasterPassword";
		span.classList.add("error-msg");
		div.appendChild(span);
		
		//serve () => altrimenti prova ad eseguire subito la funzione
		input.addEventListener("change", () => validateFormElem(input, document.getElementById('errorMasterPassword'), passwordErrorMessage));

		//prendo il parent node della checkbox e inserisco il div dopo
		elem.parentNode.after(div);
			
	} else {
		let div = document.getElementById("masterPasswordDiv");
		
		if(div)
			div.remove();
	}
	
}

/* Sezione inserimento Prodotti */
const priceErrorMessage = "Il prezzo deve essere un numero maggiore di zero";
const textErrorMessage = "Questo campo non può essere vuoto e deve contenere testo valido";

function validateProduct() {
    let valid = true;
    let form = document.getElementById("insertProductForm");
    
    let spanNome = document.getElementById("errorNomeProd");
    if(!validateFormElem(form.nome, spanNome, textErrorMessage)){
        valid = false;
    }
    
    let spanDesc = document.getElementById("errorDescrizione");
    if(!validateFormElem(form.descrizione, spanDesc, textErrorMessage)){
        valid = false;
    }
    
    let spanPrezzo = document.getElementById("errorPrezzo");
    if(!validateFormElem(form.prezzo, spanPrezzo, priceErrorMessage)){
        valid = false;
    }
    
    let spanImmagine = document.getElementById("errorImmagine");
    if(!validateFormElem(form.immagine, spanImmagine, emptyFieldErrorMessage)){
        valid = false;
    }
    
    return valid;
}