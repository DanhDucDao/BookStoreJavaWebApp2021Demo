
$('#addToCartForm button').on('click', function(e) {
	sendAddToCartFormToServer(e);
})

function sendAddToCartFormToServer(e) {
	e.preventDefault();
	var details = $('#addToCartForm').serialize();
	$.ajax({
		type: "POST",
		url: 'http://localhost:8080/BookStore/addToCart',
		timeout: 2000,
		data: details,
		
		complete: function() {
			console.log('complete');
		},
		
		error: function(data) {
			console.log("error")
		},

		success: function(data) {
			console.log(data);
			displayCartPopup(data);
		}
	})
}

var model = document.getElementById("myModal");
var table = document.getElementById("cartTable")

window.onclick = function(event) {
	if(event.target == model) {
		model.style.display = "none";
	}
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function displayCartPopup(cart) {
	table.innerHTML = '';
	for(let i = 0; i < cart.lineItems.length ; i++) {
		var row = document.createElement("TR");
		var title = document.createElement("TD");
		var quantity = document.createElement("TD");
		var price = document.createElement("TD");
		var	money = document.createElement("TD");

		title.innerText = cart.lineItems[i].title;
		quantity.innerText = cart.lineItems[i].quantity;
		price.innerText = cart.lineItems[i].price;
		money.innerText = cart.lineItems[i].price * cart.lineItems[i].quantity;

		row.appendChild(title);
		row.appendChild(quantity);
		row.appendChild(price);
		row.appendChild(money);
		table.appendChild(row);

	}
	$('#myModal').modal();
}


