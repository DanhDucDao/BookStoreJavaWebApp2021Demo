var selectEl = document.getElementById("paymentType");;
var cashEl = document.getElementById("cashType");
var creditEl = document.getElementById("creditType");
var checkEl = document.getElementById("checkType");

selectEl.addEventListener('change', function(event) {
    displayPayment(event);
})

window.addEventListener('load', function() {
    cashEl.style.setProperty('display', 'block');
    creditEl.style.setProperty('display', 'none');
    checkEl.style.setProperty('display', "none");
})

function displayPayment(event) {
    switch(selectEl.value) {
        case 'cash' :
            cashEl.style.setProperty('display', 'block');
            creditEl.style.setProperty('display', 'none');
            checkEl.style.setProperty('display', "none");
            break;
        case 'check' :
            cashEl.style.setProperty('display', 'none');
            creditEl.style.setProperty('display', 'none');
            checkEl.style.setProperty('display', "block");
            break;
        case 'credit' :
            cashEl.style.setProperty('display', 'none');
            creditEl.style.setProperty('display', 'block');
            checkEl.style.setProperty('display', "none");
            break;
            
    }
}