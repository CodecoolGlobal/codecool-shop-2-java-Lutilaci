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
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}

function eventListenerAdder(){
    let totalTotalPrice = document.getElementById("total-total-price");
    let itemPrice = document.querySelectorAll(".item-price");
    let changeQuantity = document.querySelectorAll(".changeQuantity");
    let minusButton = document.querySelectorAll(".minus-btn");
    let plusButton = document.querySelectorAll(".plus-btn");
    let totalPRice = document.querySelectorAll(".total-price");
    const addCard = document.querySelectorAll("#addToCart");
    let cartItemNumber = document.querySelector(".cart-item-number");
    cartItemNumber.innerText = "0";
    let TP = 0;

    for(let i = 0; i < addCard.length; i++){
        let modalBody = document.querySelector(".modal-body");
        addCard[i].addEventListener("click", () => {
            addProductToSessionStorage(addCard[i].dataset.id);
            let productId = addCard[i].dataset.id;
            let price = addCard[i].dataset.price.split(" ");
            let productPrice = price[0];
            console.log(productPrice)
            let productName = addCard[i].dataset.prodName;
            let description = addCard[i].dataset.description;
            document.querySelector(".product-height").src ="/static/img/product_" + productId + ".jpg";
            modalBody.innerHTML += addToCart(productId, productPrice, productName, description);
            let cartNumber = 0;
            let keySet = Object.keys(sessionStorage);
            for (let j = 0; j < keySet.length; j++) {
                let value = keySet[j]
                let data2 = sessionStorage.getItem(value);
                cartNumber += parseInt(data2)
            }
            cartItemNumber.innerText = cartNumber.toString();
        })
        // let productName =
    }

    for (let i = 0; i < totalPRice.length; i++) {
        TP += parseInt(totalPRice[i].innerText);
        totalTotalPrice.innerText = "$" + TP.toString();
    }

    for (let i = 0; i < minusButton.length; i++) {
        console.log(changeQuantity[i].value)
        if (changeQuantity[i].value !== "1") {
            minusButton[i].addEventListener("click", () => {
                let counter = parseInt(changeQuantity[i].value);
                counter--;
                changeQuantity[i].value = counter.toString();
                let totalpr = parseInt(itemPrice[i].innerText) * changeQuantity[i].value;
                totalPRice[i].innerText = totalpr.toString() + " USD";
                TP -= parseInt(itemPrice[i].innerText);
                totalTotalPrice.innerText = "$" + TP.toString();
            })
        }
    }

    for (let i = 0; i < plusButton.length; i++) {
        plusButton[i].addEventListener("click", () => {
            let counter = parseInt(changeQuantity[i].value);
            counter++;
            changeQuantity[i].value = counter.toString();
            let totalpr = parseInt(itemPrice[i].innerText) * changeQuantity[i].value;
            totalPRice[i].innerText = totalpr.toString() + " USD";
            TP += parseInt(itemPrice[i].innerText);
            totalTotalPrice.innerText = "$" + TP.toString();
        })
    }
}

function addToCart(productId, price, productName, description, TP){

    return "     <!-- Product -->\n" +
        "                    <div class=\"card\">\n" +
        "                        <div class=\"item\">\n" +
        "                            <div class=\"buttons\">\n" +
        "                                <span class=\"delete-btn\"></span>\n" +
        "                            </div>\n" +
        "                            <div class=\"image\">\n" +
        "                                <img class=\"product-height\" width=\"100\" height=\"auto\"/>\n" +
        "                            </div>\n" +
        "                            <div class=\"description\">\n" +
        "                                <h4 class=\"card-title\">" + productName + "</h4>\n" +
        "                                <p class=\"card-text prod-desc\">" + description + "</p>\n" +
        "                            </div>\n" +
        "                            <div class=\"card-text\">\n" +
        "                                <p class=\"lead item-price\">" + price + "</p>\n" +
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
        "                            <div class=\"total-price\">" + TP + "</div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                    <br>\n" +
        "\n" +
        "                </div>\n" +
        "                <div class=\"modal-footer\">\n" +
        "                    <div id=\"total-total-price\">" + TP + "</div>\n" +
        "                    <button type=\"button\" class=\"btn btn-light\" id=\"checkOut\">\n" +
        "                        Checkout\n" +
        "                    </button>\n" +
        "                </div>";

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