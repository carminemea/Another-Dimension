function createXMLHttpRequest() {
	var request;
	try {
		// Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	} catch (e) {
		// past versions of Internet Explorer 
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");  
		} catch (e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
	return request;
}

function aggiornaCarrello(buttonElement, action, idProdotto, coloreScelto, testoPersonalizzato) {
    var xhr = createXMLHttpRequest();
    if (!xhr) return;

    // parametri per la servlet
    var params = "action=" + action + 
                 "&idProdotto=" + encodeURIComponent(idProdotto) + 
                 "&coloreScelto=" + encodeURIComponent(coloreScelto) + 
                 "&testoPersonalizzato=" + encodeURIComponent(testoPersonalizzato);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);
                
                // ricarico in caso di carrello vuoto
                if (response.carrelloVuoto) {
                    window.location.reload();
                    return;
                }
                
                var cartItemRow = buttonElement.closest('.cart-item');
                
                if (response.nuovaQuantita > 0) {
                    var quantitySpan = cartItemRow.querySelector('.item-quantity');
                    if (quantitySpan) {
                        quantitySpan.innerHTML = response.nuovaQuantita;
                    }
                } else {
                    cartItemRow.remove();
                }
                
                var totalSpan = document.getElementById("cart-total-price");
                if (totalSpan) {
                    totalSpan.innerHTML = "€ " + response.totaleCarrello.toFixed(2);
                }
                
            } else {
                alert("Si è verificato un errore durante l'aggiornamento del carrello.");
            }
        }
    };

    xhr.open("POST", "CartControl", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("connection", "close");
    xhr.send(params);
}