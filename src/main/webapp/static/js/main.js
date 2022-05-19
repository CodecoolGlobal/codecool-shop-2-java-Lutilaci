let shoppingCartContent = [];
// let checkoutfooter = "<div class=\"modal-footer\">\n" +
//     "                    <div id=\"total-total-price\">" + 0 + "</div>\n" +
//     "                    <button type=\"button\" class=\"btn btn-light\" id=\"checkOut\">\n" +
//     "                        Checkout\n" +
//     "                    </button>\n" +
//     "                </div>";


function modalWindowHandler() {
    let modal = document.getElementById("myModal");
    let btn = document.getElementById("shoppingCart");
    let span = document.getElementsByClassName("close")[0];
    let modalBody = document.querySelector(".modal-body");

    btn.onclick = function () {
        modal.style.display = "block";
        modalBody.innerHTML = addToCart(shoppingCartContent);
        // modalBody.innerHTML += checkoutfooter;
        inCartEventListenerPlacer();
    }

    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}

function eventListenerAdder(){
    let itemPrice = document.querySelectorAll(".item-price");
    const addCard = document.querySelectorAll("#addToCart");
    let cartItemNumber = document.querySelector(".cart-item-number");
    let modalBody = document.querySelector(".modal-body");
    cartItemNumber.innerText = "0";
    let TP = 0 + " USD";


    for(let i = 0; i < addCard.length; i++){
        addCard[i].addEventListener("click", () => {
            let price = addCard[i].dataset.price.split(" ");
            let item = {"id": addCard[i].dataset.id, "productName": addCard[i].dataset.prodname, "description": addCard[i].dataset.description, "price": price[0], "picture": document.querySelector(".product-height").src}
            shoppingCartContent.push(item);
            console.log(shoppingCartContent)
            addProductToSessionStorage(addCard[i].dataset.id);
            let cartNumber = 0;
            let keySet = Object.keys(sessionStorage);
            for (let j = 0; j < keySet.length; j++) {
                let value = keySet[j]
                let data2 = sessionStorage.getItem(value);
                cartNumber += parseInt(data2)
            }
            cartItemNumber.innerText = cartNumber.toString();
        })
    }

}

function inCartEventListenerPlacer(){

    let totalTotalPrice = document.getElementById("total-total-price");
    let itemPrice = document.querySelectorAll(".item-price");
    let changeQuantity = document.querySelectorAll(".changeQuantity");
    let minusButton = document.querySelectorAll(".minus-btn");
    let plusButton = document.querySelectorAll(".plus-btn");
    let totalPRice = document.querySelectorAll(".total-price");
    // const addCard = document.querySelectorAll("#addToCart");
    let cartItemNumber = document.querySelector(".cart-item-number");
    cartItemNumber.innerText = "0";
    let TP = 0;

    for (let i = 0; i < minusButton.length; i++) {
            minusButton[i].addEventListener("click", () => {
                if (changeQuantity[i].value !== "1") {
                let counter = parseInt(changeQuantity[i].value);
                counter--;
                changeQuantity[i].value = counter.toString();
                let totalpr = parseInt(itemPrice[i].innerText) * changeQuantity[i].value;
                totalPRice[i].innerText = totalpr.toString() + " USD";
                TP -= parseInt(itemPrice[i].innerText);
                totalTotalPrice.innerText = "$" + TP;
            }
        })
    }

    for (let i = 0; i < plusButton.length; i++) {
        plusButton[i].addEventListener("click", () => {
            let counter = parseInt(changeQuantity[i].value);
            counter++;
            changeQuantity[i].value = counter.toString();
            let totalpr = parseInt(itemPrice[i].innerText) * changeQuantity[i].value;
            totalPRice[i].innerText = totalpr.toString() + " USD";
            TP += parseInt(itemPrice[i].innerText);
            totalTotalPrice.innerText = "$" + TP;
        })
    }

    for (let i = 0; i < totalPRice.length; i++) {
        TP += parseInt(totalPRice[i].innerText);
        totalTotalPrice.innerText = "$" + TP.toString();
    }
}



function addToCart(shoppingCartContent) {
    let modalContent = "";
    for (let i = 0; i < shoppingCartContent.length; i++) {
        modalContent += "     <!-- Product -->\n" +
            "                    <div class=\"card\">\n" +
            "                        <div class=\"item\">\n" +
            "                            <div class=\"buttons\">\n" +
            "                                <span class=\"delete-btn\"></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"image\">\n" +
            `                                <img class=\"product-height\" src=/static/img/product_${shoppingCartContent[i].id}.jpg width=\"auto\" height=\"auto\"/>\n` +
            "                            </div>\n" +
            "                            <div class=\"description\">\n" +
            "                                <h4 class=\"card-title\">" + shoppingCartContent[i].productName + "</h4>\n" +
            "                                <p class=\"card-text prod-desc\">" + shoppingCartContent[i].description + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"card-text\">\n" +
            `                                <p class=\"lead item-price\"> ${shoppingCartContent[i].price}  USD </p>\n` +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"quantity\">\n" +
            "                                <button class=\"minus-btn\" type=\"button\" name=\"button\">\n" +
            "                                    -\n" +
            "                                </button>\n" +
            "                                <input type=\"text\" name=\"name\" class=\"changeQuantity\" value=\"1\">\n" +
            "                                <button class=\"plus-btn\" type=\"button\" name=\"button\">\n" +
            "                                    +\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            "                            <div class=\"total-price\">" + shoppingCartContent[i].price + "</div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <br>\n" +
            "\n" +
            "                </div>\n";
    }
    modalContent += "<div class=\"modal-footer\">\n" +
        "                    <div id=\"total-total-price\">" + 0 + "</div>\n" +
        "                    <button type=\"button\" class=\"btn btn-light\" id=\"checkOut\">\n" +
        "                        Checkout\n" +
        "                    </button>\n" +
        "                </div>";
    return modalContent;
}



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

// Remove saved data from sessionStorage

    sessionStorage.removeItem('key');
// Remove all saved data from sessionStorage

    sessionStorage.clear();
}

eventListenerAdder()
modalWindowHandler()

// sessionStorageHandler()