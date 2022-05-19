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

function categoryFiltering(){
    const categories = document.querySelectorAll(".category");
    categories.forEach((category) => {
        console.log(category)
        category.addEventListener("click", async () =>{
            let id = category.dataset.catid
            if(category.classList.contains("highlight")){
                let url = `/api/get/products`
                await eventHandler(url)
                removeHighlight()
                changeCategoryButton("Category")
            } else {
                highlight(category)
                changeCategoryButton(category.textContent)
                let url = `/api/get/products-by-category?catId=${id}`
                await eventHandler(url)
            }
        })
    })

}

async function eventHandler(productId){
    let url = "product";
    let param = "prodid";
    let response = await apiGet(url, param, productId);
    const rowClass = document.getElementById("products");
    rowClass.innerHTML = rowBuilder(response);
}

productFiltering()