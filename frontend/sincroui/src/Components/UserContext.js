import { createContext, useEffect, useState } from 'react'
import { cancellableFetch } from "../Services/CancellableFetch"

export const UserContext = createContext(null)
export const UserProvider = UserContext.Provider

function request(uri, opts, dispatch) {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {
        response.json().then((data) => {
            if(fetch.signal.aborted) {
                dispatch((prevState) => {return {...prevState}})
                return
            }
            if(response.ok) dispatch({logged: true, nif: data.nif, email:data.email, nome:data.nome, subscritor:data.subscritor, loading:false})
            else dispatch({logged: false, nif: null, loading: false})
        })
    }).catch(() => { dispatch((prevState) => {return {...prevState}}) })
    return fetch.abort
}

export const UserContainer = (props) => {

    const [context, setContext] = useState({logged: false, nif: null, email:null, nome:null, subscritor:null, loading:true})

    useEffect(() => {
        return request("/check", {method: "GET"}, setContext)
    }, [])
    if(context.loading){
        console.log("wait for check")
        console.log(context)
        return(<UserProvider value={[context, setContext]}></UserProvider>)
    } else {
        console.log("check done")
        console.log(context)
        return(<UserProvider value={[context, setContext]}>{props.children}</UserProvider>)
    }
}

