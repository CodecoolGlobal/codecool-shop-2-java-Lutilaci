function modalWindowHandler() {
    let modal = document.getElementById("myModal");
    let btn = document.getElementById("shoppingCart");
    let span = document.getElementsByClassName("close")[0];
    btn.onclick = function () {
        modal.style.display = "block";
    }

    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function eventListenerAdder(){
    const addCard = document.querySelectorAll("#addToCart");
    for(let i = 0; i < addCard.length; i++){
        addCard[i].addEventListener("click", event => {
            alert("Added to cart")
            addProductToSessionStorage(addCard[i].dataset.id)
            let data = sessionStorage.getItem(addCard[i].dataset.id);
            console.log(data)
        })
    }

    // sessionStorageHandler(addCard)
}

// function
function addProductToSessionStorage(productId){
    let data = sessionStorage.getItem(productId)
    if(data != null){
        sessionStorage.setItem(productId, (parseInt(data)+1).toString());
    } else {
        sessionStorage.setItem(productId, "1");
    }
}
function sessionStorageHandler(){
    // Save data to sessionStorage
    sessionStorage.setItem('key', 'value');

// Get saved data from sessionStorage
    let data = sessionStorage.getItem('key');

    console.log(data)
// Remove saved data from sessionStorage

    sessionStorage.removeItem('key');
// Remove all saved data from sessionStorage

    sessionStorage.clear();
}
eventListenerAdder()
modalWindowHandler()
sessionStorageHandler()