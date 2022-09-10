import { Fragment, useContext, useState } from "react"
import { Navigate } from "react-router"
import { UserContext } from "../Components/UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"

export const NaoSubsPage = () => {

    const [user, dispatch] = useContext(UserContext)
    

    const request = (uri, opts, dispatch) => {
        const fetch = cancellableFetch(uri, opts)
        fetch.ready.then((response) => { if(response.ok) dispatch()})
        .catch((error) => { console.log(error) })
        return fetch.abort
    }
    
    
    const clickAction = () => {
        let write = user
        const func = () => {
            write.subscritor = true
            dispatch(write)
            
        }
        request(`/api/subscritores/${user.nif}`, { method: "POST"}, func)
        window.location.assign("/")
    }

    return(
        <Fragment>
            <h1>O cidadao não se encontra subscrito.</h1>
            <button  className="btn btn-primary" onClick={clickAction}>Subscrever</button>
        </Fragment>
    )
}
