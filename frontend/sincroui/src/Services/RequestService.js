import { cancellableFetch } from "./CancellableFetch"

const parse = (response, setData, setError) => {
    response.body.then((data) => {
        if(response.signal.aborted) return
        if(response.ok) setData(data)
        else setError(data)
    })
}

const request = (uri, opts, setData, setError) => {
    const aux = cancellableFetch(uri, opts)
    aux.ready.then((response) => {
        //console.log(response)
        parse({ok: response.ok, body: response.json(), signal: aux.signal}, setData, setError)
    }).catch((reason) => console.log("On catch: " + reason))
    return aux.abort
}

export const get = (uri, setData, setError) => request(uri, {method: 'GET'}, setData, setError)
export const post = (uri, body, setData, setError) => request(uri, {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, setData, setError)
//export const put = (uri, body, setData, setError) => request(uri, {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, setData, setError)
//export const del = (uri, setData, setError) => request(uri, {method: 'DELETE'}, setData, setError)
export const del = (uri, setData, setError) => fetch(uri, {method: 'DELETE'}).then(response => {response.ok ? setData() : setError()}).catch(err => setError(err));
export const altPost = (uri, body, setData, setError) => fetch(uri, {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}).then(response => {response.ok ? setData() : setError()}).catch(err => setError(err));
export const put = (uri, body, setData, setError) => fetch(uri, {method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}).then(response => {response.ok ? setData() : setError()}).catch(err => setError(err));