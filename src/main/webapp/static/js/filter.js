import {rowBuilder} from "./cardFactory.js";
import {apiGet} from "./api.js";

function productFiltering(){
    const products = document.querySelectorAll(".product");
    for(let i = 0; i < products.length; i++){
        let id = products[i].dataset.prodid
        products[i].addEventListener("click", async () => {
            if(products[i].classList.contains("highlight")){
                let url = `/api/get/products`
                await eventHandler(url)
                removeHighlight()
                changeProductButton("Products")
            } else {
                highlight(products[i])
                changeProductButton(products[i].textContent)
                let url = `/api/get/product?prodid=${id}`
                await eventHandler(url)
            }
        })
    }
}

function changeButton(name){
    const productDropDownButton = document.getElementById("productDropDownMenu");
    productDropDownButton.innerText = name;
}

async function eventHandler(productId){
    let url = "product";
    let param = "prodid";
    let response = await apiGet(url, param, productId);
    const rowClass = document.getElementById("products");
    rowClass.innerHTML = rowBuilder(response);
}

productFiltering()