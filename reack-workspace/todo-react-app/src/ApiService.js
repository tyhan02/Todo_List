import { API_BASE_URL} from "./app-config";
import response from "assert";

export function call(api, method, request){
    let options = {
        headers: new Headers({
            "Content-Type": "application/json",
        }),
        url: API_BASE_URL + api,
        method: method,
    };
    if (request) {
        //Get method
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options).then((request) =>
        response.json().then((json) => {
        if (!response.ok) {
            return Promise.reject(json);
        }
        return json;
    })
    );
}