import {rowBuilder} from "./cardFactory.js";
import {apiGet} from "./api.js";

function productFiltering(){
    const products = document.querySelectorAll(".product");
    products.forEach(product => product.addEventListener("click", (event) => {
    }))
}

function changeButton(name){
    const productDropDownButton = document.getElementById("productDropDownMenu");
    productDropDownButton.innerText = name;
}

async function eventHandler(event){
    const productId =event.target.dataset.prodid;
    let url = "";
    let param = "";
    let response = await apiGet(url, param, productId);
    const rowClass = document.querySelector(".row");
    rowClass.innerHTML = rowBuilder(response);
}

productFiltering()