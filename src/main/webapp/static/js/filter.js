import {rowBuilder} from "./cardFactory.js";
import {apiGet} from "./api.js";
import {eventListenerAdder} from "./main.js";

let supp_id = 0
let cat_id = 0
function supplierFilteringEventListener(){
    const suppliers = document.querySelectorAll(".supplier");
    suppliers.forEach((supplier) => {
        supplier.addEventListener("click", async () => {
            let supplierId = supplier.dataset.suppid
            let url;
            supp_id = supplierId
            if(supplier.classList.contains("highlight")){
                supp_id = 0
                removeSupplierHighlight()
                changeSupplierButtonText("Supplier");
                if(cat_id === 0){
                    url = "/api/products"
                } else {
                    url = `/api/products?catid=${cat_id}`
                }
            } else {
                removeSupplierHighlight()
                changeSupplierButtonText(supplier.innerText)
                highlight(supplier)
                if(cat_id === 0){
                    url = `/api/products?suppid=${supplierId}`
                } else {
                    url = `/api/products?suppid=${supplierId}&catid=${cat_id}`
                }
            }
            await eventHandler(url)
            eventListenerAdder()
        })
    })
}

function categoryFilteringEventListener(){
    const categories = document.querySelectorAll(".category");
    categories.forEach((category) => {
        category.addEventListener("click", async () =>{
            let id = category.dataset.catid
            let url;
            cat_id = id
            if(category.classList.contains("highlight")){
                cat_id = 0

                changeCategoryButtonText("Category")
                removeCategoryHighlight()
                if(supp_id === 0){
                    url = "/api/products"
                } else {
                    url = `/api/products?suppid=${supp_id}`
                }
            } else {
                removeCategoryHighlight()
                changeCategoryButtonText(category.innerText)
                highlight(category)
                if(supp_id === 0){
                    url = `/api/products?catid=${id}`
                } else {
                    url = `/api/products?suppid=${supp_id}&catid=${id}`
                }
            }
            await eventHandler(url);
            eventListenerAdder()

        })
    })
}

async function eventHandler(url){
    let response = await apiGet(url);
    const rowClass = document.getElementById("products");
    rowClass.innerHTML = rowBuilder(response);
}

function changeSupplierButtonText(name){
    const supplierDropDownButton = document.getElementById("supplierDropDownMenu");
    supplierDropDownButton.innerText = name;
}

function changeCategoryButtonText(name){
    const categoryDropDownButton = document.getElementById("categoryDropDownMenu");
    categoryDropDownButton.innerText = name;
}

function removeCategoryHighlight(){
    const categories = document.querySelectorAll(".category")
    categories.forEach(categories => {
        categories.classList.remove("highlight")
    })
}

function removeSupplierHighlight(){
    const suppliers = document.querySelectorAll(".supplier")
    suppliers.forEach(supplier => {
        supplier.classList.remove("highlight")
    })
}

function highlight(element){
    element.classList.add("highlight")
}

supplierFilteringEventListener()
categoryFilteringEventListener()
