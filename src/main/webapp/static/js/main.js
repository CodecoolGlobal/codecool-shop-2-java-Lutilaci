import {shoppingCartCardBuilder} from "./cardFactory.js";
import {sessionStorageToJson} from "./json.js";
let shoppingCartContent = [];

function modalWindowHandler() {
    let modal = document.getElementById("myModal");
    let btn = document.getElementById("shoppingCart");
    let span = document.getElementsByClassName("close")[0];
    let modalBody = document.querySelector(".modal-body");

    btn.addEventListener("click", ()=> {
        modal.style.display = "block";
        modalBody.innerHTML = addToCart(shoppingCartContent);
        inCartEventListenerPlacer();
        checkOutAddListener()
    })

    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}

export function addToCartEventListener(){
    let addCard = document.querySelectorAll("#addToCart");
    let cartItemNumber = document.querySelector(".cart-item-number");
    cartItemNumber.innerText = "0";


    let list = []
    // let count = {"id": 0}
    //
    // let counter = 0;
    for(let i = 0; i < addCard.length; i++) {
        addCard[i].addEventListener("click", () => {
            console.log("clicked")
            let price = addCard[i].dataset.price.split(" ")[0];
            console.log(price)
            let item = {
                "id": addCard[i].dataset.id,
                "productName": addCard[i].dataset.prodname,
                "quantity": 0,
                "description": addCard[i].dataset.description,
                "price": price,
                "picture": document.querySelector(".product-height").src
            }

            if (list.includes(addCard[i].dataset.id)) {

            } else {
                list.push(addCard[i].dataset.id)
            }
            shoppingCartContent.push(item);
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
        let modalBody = document.querySelector(".modal-body");
        modalBody.innerHTML = `<div class="container shipping-details background">
        <form action="/api/post/order-details" method="post">
            <h1>
                Shipping Details
            </h1>
            <div class="name">
                <div class="grid">
                    <label htmlFor="f-name">First name</label>
                    <input type="text" name="f-name">
                </div>
                <div class="grid">
                    <label htmlFor="l-name">Last name</label>
                    <input type="text" name="l-name">
                </div>
                <div class="grid">
                    <label htmlFor="email">Email</label>
                    <input type="text" name="email">
                </div>
            </div>
            <div class="street grid">
                <label htmlFor="street">Street address</label>
                <input type="text" name="address">
            </div>
            <div class="address-info">
                <div class="grid">
                    <label htmlFor="city">City</label>
                    <input type="text" name="city">
                </div>
                <div class="grid">
                    <label htmlFor="zip">Zip code</label>
                    <input type="text" name="zip">
                </div>
            </div>
    <div class="card-details container">
        <h1>
            Payment Information
        </h1>
        <div class="row">
            <div class="form-group col-sm-7 grid cc-name">
                <label for="card-holder">Cardholder</label>
                <input type="text" name="card-holder">
            </div>
            <div class="form-group col-sm-5 end cc-exp">
                <label class="grid" for="card-num">Expiry date</label>
                <input class="exp" type="text" name="month" placeholder="MM" aria-label="MM" minlength="2" maxlength="2">
                <span class="date-separator">/</span>
                <input class="exp" type="text" name="year" placeholder="YY" aria-label="YY" minlength="2" maxlength="2">
            </div>
            <div class="cc-num form-group col-sm-8 grid">
                <label for="card-num">Credit Card Number</label>
                <input type="text" name="card-num" placeholder="XXXX-XXXX-XXXX-XXXX" minlength="16" maxlength="16">
            </div>
            <div class="form-group col-sm-4 end cc-cvc">
                <label class="grid" for="card-num">CVC</label>
                <input class="exp" type="text" name="security" placeholder="***" minlength="3" maxlength="3">
            </div>
        </div>
        <div class="btns">
            <button id="finish" class="btn btn-primary" type="submit">Complete payment</button>
        </div>
    </div>
        </form>
    </div>
</div>
</div>`
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
                let prodId = plusButton[i].dataset.id;
                removeProductFromSessionStorage(prodId.toString());
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
            let prodId = plusButton[i].dataset.id;
            addProductToSessionStorage(prodId.toString());
        })
    }

    for (let i = 0; i < totalPRice.length; i++) {
        TP += parseInt(totalPRice[i].innerText);
        totalTotalPrice.innerText = "$" + TP.toString();
    }
}


function addToCart(shoppingCartContent) {
    let modalContent = "";
    shoppingCartContent.forEach(content => {
        let id = content.id
        let productName = content.productName
        let description = content.description
        let price = content.price
        let count = content.quantity
        modalContent += shoppingCartCardBuilder(id, productName, description, price, count);
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

modalWindowHandler()
eventListenerAdder()
// sessionStorageHandler()