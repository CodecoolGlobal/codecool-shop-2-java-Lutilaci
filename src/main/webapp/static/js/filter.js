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

async function eventHandler(url){
    let response = await apiGet(url);
    const rowClass = document.getElementById("products");
    rowClass.innerHTML = rowBuilder(response);
}

function changeProductButton(name){
    const productDropDownButton = document.getElementById("productDropDownMenu");
    productDropDownButton.innerText = name;
}

function changeCategoryButton(name){
    const categoryDropDownButton = document.getElementById("supplierDropDownMenu");
    categoryDropDownButton.innerText = name;
}

function removeHighlight(){
    const products = document.querySelectorAll(".product")
    const categories = document.querySelectorAll(".category")
    products.forEach(product => {
        product.classList.remove("highlight")
    })
    categories.forEach(category => {
        category.classList.remove("highlight")
    })
}

function highlight(element){
    removeHighlight()
    element.classList.add("highlight")
}

productFiltering()
categoryFiltering()
