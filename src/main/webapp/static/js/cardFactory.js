export function rowBuilder(result){
    let rowHTML = "";
    if(result.length === 0){
        return rowHTML
    }
    if(result.length > 0){
        result.forEach(res => {
            rowHTML += cardBuilder(res.defaultPrice, res.catId, res.name, res.id, res.description, res.defaultCurrency)
        })
    } else {
        rowHTML += cardBuilder(result.defaultPrice, result.catId, result.name, result.id, result.description, result.defaultCurrency)
    }
    return rowHTML
}


function cardBuilder(defaultPrice, catId, name, id, description, defaultCurrency){
    const cardHTML =  `<div class="col col-sm-12 col-md-6 col-lg-4">` +
            `<div class="card" data-category="${catId}" data-name="${name}">` +
                `<img class="product-height" src="/static/img/product_${id}.jpg" alt="">` +
                `<div class="card-header">`+
                    `<h4 class="card-title">${name}</h4>` +
                    `<p class="card-text prod-desc">${description}.</p>` +
                `</div>` +
                `<div class="card-body">` +
                    `<div class="card-text">` +
                        `<p class="lead">${defaultPrice} ${defaultCurrency}</p>`+
                    `</div>` +
                `<div class="card-text">` +
                    `<button type="button" class="btn btn-light" data-id="${id}" data-price="${defaultPrice}" data-prodName="${name}" data-id="${id}" id="addToCart">Add to cart</button>` +
                `</div>` +
                `</div>` +
            `</div>` +
        `</div>`
    return cardHTML
}

export function shoppingCartCardBuilder(id, productName, description, price, count){
    return "        <div class=\"card\">\n" +
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
        `                                <p class=\"lead item-price\"> ${price} USD</p>\n` +
        "                            </div>\n" +
        "                            <div class=\"quantity\">\n" +
        `                                <button class=\"minus-btn\" type=\"button\" name=\"button\" data-id=\"${id}\">\n` +
        "                                    -\n" +
        "                                </button>\n" +
        `                                <input type=\"text\" name=\"name\" class=\"changeQuantity\" value=\"${count}\">\n` +
        `                                <button class=\"plus-btn\" type=\"button\" name=\"button\" data-id=\"${id}\">\n` +
        "                                    +\n" +
        "                                </button>\n" +
        "                            </div>\n" +
        `                            <div class=\"total-price\"> ${price*count} USD </div>\n` +
        "                        </div>\n" +
        "                    </div>\n" +
        "                    <br>\n" +
        "                </div>\n";
}

