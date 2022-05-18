export async function apiGet(url, param, value){
    let response = await fetch(`/api/get/${url}?${param}=${value}`, {
        method:'GET'
    })
    if(response.status===200){
        return response.json()
    }
}

//api/get/product-by-category?catId=1