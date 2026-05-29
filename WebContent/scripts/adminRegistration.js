function insertMasterPassword(elem) {
	
	const container = document.getElementById("masterPasswordGroup");
	const field = document.getElementById("masterPassword");
	
	if(elem.checked) {
		container.style.display = 'block';
		field.required = true;
	} else {
		container.style.display = 'none';
		field.required = false;
	}
}