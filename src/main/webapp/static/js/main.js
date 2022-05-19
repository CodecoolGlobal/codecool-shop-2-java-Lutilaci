let shoppingCartContent = [];

function modalWindowHandler() {
    let modal = document.getElementById("myModal");
    let btn = document.getElementById("shoppingCart");
    let span = document.getElementsByClassName("close")[0];
    let modalBody = document.querySelector(".modal-body");

    btn.onclick = function () {
        modal.style.display = "block";
        modalBody.innerHTML = addToCart(shoppingCartContent);
        checkOutAddListener()
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
    let addCard = document.querySelectorAll("#addToCart");
    let cartItemNumber = document.querySelector(".cart-item-number");
    cartItemNumber.innerText = "0";


    let list = []
    let count = {"id": 0}

    let counter = 0;
    for(let i = 0; i < addCard.length; i++) {
        addCard[i].addEventListener("click", () => {
            let price = addCard[i].dataset.price.split(" ");
            let item = {
                "id": addCard[i].dataset.id,
                "productName": addCard[i].dataset.prodname,
                "quantity": 0,
                "description": addCard[i].dataset.description,
                "price": price[0],
                "picture": document.querySelector(".product-height").src
            }
            if (list.includes(addCard[i].dataset.id)) {

            } else {
                list.push(addCard[i].dataset.id)
            }
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

function checkOutAddListener(){
    let checkOut = document.getElementById("checkOut");
    checkOut.addEventListener("click", ()=> {
        console.log("HALLELUJA")
    })
}

function inCartEventListenerPlacer(){

    let totalTotalPrice = document.getElementById("total-total-price");
    let itemPrice = document.querySelectorAll(".item-price");
    let changeQuantity = document.querySelectorAll(".changeQuantity");
    let minusButton = document.querySelectorAll(".minus-btn");
    let plusButton = document.querySelectorAll(".plus-btn");
    let totalPRice = document.querySelectorAll(".total-price");
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
    let setOfIds = []
    shoppingCartContent.forEach(sct => {
        if(!setOfIds.includes(sct.id)){
            setOfIds.push(sct.id)
        }
    })
    let modalContent = "";
    setOfIds.forEach(id => {
        let count = 0
        let productName = "";
        let description = ""
        let price = ""

        for(let i = 0; i < shoppingCartContent.length; i++){
            if(shoppingCartContent[i].id == id){
                count++
                productName = shoppingCartContent[i].productName
                description = shoppingCartContent[i].description
                price = shoppingCartContent[i].price

            }
        }
        modalContent += "        <div class=\"card\">\n" +
            "                        <div class=\"item\">\n" +
            "                            <div class=\"buttons\">\n" +
            "                                <span class=\"delete-btn\"></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"image\">\n" +
            `                                <img class=\"product-height\" src=/static/img/product_${id}.jpg width=\"auto\" height=\"auto\"/>\n` +
            "                            </div>\n" +
            "                            <div class=\"description\">\n" +
            "                                <h4 class=\"card-title\">" + productName + "</h4>\n" +
            "                                <p class=\"card-text prod-desc\">" + description + "</p>\n" +
            "                            </div>\n" +
            "                            <div class=\"card-text\">\n" +
            `                                <p class=\"lead item-price\"> ${price}  USD </p>\n` +
            "                            </div>\n" +
            "                            <div class=\"quantity\">\n" +
            "                                <button class=\"minus-btn\" type=\"button\" name=\"button\">\n" +
            "                                    -\n" +
            "                                </button>\n" +
            `                                <input type=\"text\" name=\"name\" class=\"changeQuantity\" value=\"${count}\">\n` +
            "                                <button class=\"plus-btn\" type=\"button\" name=\"button\">\n" +
            "                                    +\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            `                            <div class=\"total-price\"> ${price*count} USD </div>\n` +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <br>\n" +
            "                </div>\n";

        //Create card here with

    })
    modalContent += "<div class=\"modal-footer\">\n" +
        `                    <div id=\"total-total-price\"> 0 </div>\n` +
        "                    <button type=\"button\" class=\"btn btn-light\" id=\"checkOut\">\n" +
        "                        Checkout\n" +
        "                    </button>\n" +
        "                </div>";
    return modalContent
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