import { Fragment, useContext, useState } from "react"
import { UserContext } from "./UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"
import { Link, Navigate } from "react-router-dom";
import { subscribeToPushNotification } from "../Services/HandleSubscription"
import { unsubscribeFromPushNotification } from "../Services/HandleSubscription"


const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const Navbar = () => {

    const [user, dispatch] = useContext(UserContext)

    if(!user || !user.logged){
        return(<Fragment></Fragment>)
    }

    const unsubClick = () => {
        
        let write = user

        const func = (ok) => {        
            if (ok) {
                write.subscritor = false
                dispatch(write)
                window.location.reload()
            }
        }
        const get = (ok, json) => {
            if (ok){
                json.then((date) => {
                    
                    request(`/api/subscritores/${user.nif}/deactivate`, { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(date)}, func)
                })
            }
        }
        request(`/api/subscritores/${user.nif}/date`, {method: "GET"}, get)
        
        
    }

    
    const subClick = () => {
        
        let write = user
        const func = (ok) => {
            if (ok) {
                write.subscritor = true
                dispatch(write)
                window.location.reload()
            }
        }
        console.log("tou aqui",user)
        request(`/api/subscritores/${user.nif}/activate`, { method: "POST"}, func)
    }
    
    let unsub = null
    if (user.subscritor) unsub = <button className="btn btn-outline-success my-2 my-sm-0 me-2" onClick={unsubClick}>Unsubscribe</button>
    else unsub = <button className="btn btn-outline-success my-2 my-sm-0 me-2" onClick={subClick}>Subscribe</button>

    const logoutClick = () => {
        const func = async () => {
            let usr = user
            subscribe(usr)
            dispatch({logged: false, nif: null, loading: false})
            //window.location.assign("/login")
        }
        request("/logout", { method: "POST"}, func)
       
    }
    let subscribe = (usr) => {
        console.warn(user)
    if(user!=null && user.subscritor) {
        unsubscribeFromPushNotification(usr.nif)
    }
}

    return(
        <Fragment>
            <nav className="navbar navbar-light bg-light">
                <div className="container-fluid justify-content-between" style={{margin: "auto", width: "90%"}}>
                    <Link className="navbar-brand ms-2" to="/">Home</Link>
                    {unsub}
                    
                    <button className="btn btn-outline-success my-2 my-sm-0 me-2" onClick={logoutClick}>LogOut</button>
                    {subscribe}
                </div>
            </nav>
        </Fragment>
    )
}