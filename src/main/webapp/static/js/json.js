import {apiGet} from "./api.js";

export async function sessionStorageToJson(){
    let keys = [];
    for(let i = 0; i < sessionStorage.length; i++){
        keys.push(sessionStorage.key(i))
    }
    let test = ""
    for(let i = 0; i < keys.length; i++){
        test += keys[i]
        if(i !== keys.length-1){
            test += ","
        }
    }
    let url = `/api/products?products=${test}`
    let products = await apiGet(url)
    return products;

    // console.log(sessionStorage)
    // const jsonObj = JSON.parse(Object.keys(sessionStorage))
}
