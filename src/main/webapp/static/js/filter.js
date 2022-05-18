function productFiltering(){
    const products = document.querySelectorAll(".product");
    console.log('im inside filtering')
    // for(let i = 0; i < products.length; i++){
    //     console.log(products[i])
    //     products[i].addEventListener('click', event => {
    //         console.log(products[i].innerText);
    //     })
    // }
    products.forEach(product => productFilteringEvent(product));

}

function productFilteringEvent(product){
    product.addEventListener("click", (event) => {
        const productName =event.target.innerHTML;
        const productCards = document.querySelectorAll(".card")
        productCards.forEach(card => {
            if (card.dataset.name === productName){
                const prodToShow = card.parentElement.innerHTML;
                console.log(prodToShow)
                const prodRow = document.getElementById("products");
                prodRow.innerHTML = prodToShow;
                changeButton(productName);
            }
        })
    })
}

function changeButton(name){
    const productDropDownButton = document.getElementById("productDropDownMenu");
    productDropDownButton.innerText = name;
}

productFiltering()