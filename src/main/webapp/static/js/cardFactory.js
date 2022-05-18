export function rowBuilder(result){
    let rowHTML = "";
    rowHTML += cardBuilder(result.defaultPrice, result.catId, result.name, result.id, result.description, result.defaultCurrency)
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
        `<button type="button" class="btn btn-light" data-id="${id}" id="addToCart">Add to cart</button>` +
        `</div>` +
        `</div>` +
        `</div>` +
        `</div>`
    return cardHTML
}

