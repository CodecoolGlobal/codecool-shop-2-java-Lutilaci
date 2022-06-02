
import {shoppingCartCardBuilder} from "./cardFactory.js";
import {sessionStorageToJson} from "./json.js";
import {registrationAddEventListener} from "./registration.js";
import {sessionStorageKeys} from "./json.js";
let shoppingCartContent = [];

function modalWindowHandler() {
    let modal = document.getElementById("myModal");
    let btn = document.getElementById("shoppingCart");
    let span = document.getElementsByClassName("close")[0];
    let modalBody = document.querySelector(".modal-body");

    btn.addEventListener("click", () => {
        modal.style.display = "block";
        modalBody.innerHTML = addToCart(shoppingCartContent);
        inCartEventListenerPlacer();
        checkOutAddListener()
    })

    span.addEventListener("click", () => {
        modal.style.display = "none";
    })


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

    for (let i = 0; i < addCard.length; i++) {
        addCard[i].addEventListener("click", () => {
            let price = addCard[i].dataset.price.split(" ")[0];
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
        let productIds =  sessionStorageKeys()
        modalBody.innerHTML = `<div class="container shipping-details background">
        <form action="/api/post/order-details?productIds=${productIds}" method="post">
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
        finishButtonEventListenerAdder();
    })

    function finishButtonEventListenerAdder() {
        finishBtn.addEventListener("click", () => {
            modalbody.innerHTML = `<div className="container">
            <h1>
                Order confirmation
            </h1>
            <div className="row">
                <div className="col-md-auto">
                    <label>Shipping info</label>
                    <table>
                        <tr>
                            <td>Name</td>
                            <td className="data" th:text="${firstName + ' ' + lastName}"></td>
                        </tr>
                        <tr>
                            <td>Street</td>
                            <td className="data" th:text="${address}"></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td className="data" th:text="${city}"></td>
                        </tr>
                        <tr>
                            <td>Zip code</td>
                            <td className="data" th:text="${zip}"></td>
                        </tr>
                    </table>
                </div>
                <div className="col-md-auto">
                    <label>Payment info</label>
                    <table>
                        <tr>
                            <td>Amount</td>
                            <td className="data">$ 236.00</td>
                        </tr>
                        <tr>
                            <td>Card</td>
                            <td className="data">****4567</td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td className="data">Successful</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td className="data" th:text="${email}"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <h1>Products</h1>
            <div className="col">
                <div>
                    <table className="producttable">
                        <thead>
                        <tr className="producttable">
                            <th scope="col" className="producttable">Product</th>
                            <th scope="col" className="producttable">Amount</th>
                            <th scope="col" className="producttable">Unit price</th>
                            <th scope="col" className="producttable">Total price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr className="producttable">
                            <td className=" producttable">Bottle Water</td>
                            <td className=" producttable">2</td>
                            <td className=" producttable">$ 8.00</td>
                            <td className=" producttable">$ 16.00</td>
                        </tr>
                        <tr className="producttable">
                            <td className=" producttable">Self-watering can</td>
                            <td className=" producttable">1</td>
                            <td className=" producttable">$ 20.00</td>
                            <td className=" producttable">$ 20.00</td>
                        </tr>
                        <tr className="producttable">
                            <td className=" producttable">Double Champagne Glass</td>
                            <td className=" producttable">4</td>
                            <td className=" producttable">$ 50.00</td>
                            <td className=" producttable">$ 200.00</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>`
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

function addProductToSessionStorage(productId) {
    let data = sessionStorage.getItem(productId)
    if (data != null) {
        sessionStorage.setItem(productId, (parseInt(data) + 1).toString());
    } else {
        sessionStorage.setItem(productId, "1");
    }
}

function removeProductFromSessionStorage(productId){
    let data = sessionStorage.getItem(productId);
    if(data === "1"){
        sessionStorage.removeItem(productId)
    } else {
        sessionStorage.setItem(productId, (parseInt(data)-1).toString());
    }
}

async function loadCart(){
    let productsFromCart = await sessionStorageToJson();
    console.log(productsFromCart)
    for(let i = 0; i < productsFromCart.length; i++){
        let strId = productsFromCart[i].id
        console.log(typeof strId)
        let quantity = sessionStorage.getItem(strId)
        let item = {
            "id": (productsFromCart[i].id).toString(),
            "productName": productsFromCart[i].name,
            "quantity": quantity,
            "description": productsFromCart[i].description,
            "price": productsFromCart[i].defaultPrice,
            "picture": document.querySelector(".product-height").src
        }
        shoppingCartContent.push(item);
    }
    document.querySelector(".modal-body").innerHTML = addToCart(shoppingCartContent)
}

loadCart().then(r => console.log("Cart is loaded!"))
modalWindowHandler()
addToCartEventListener()
registrationAddEventListener();
