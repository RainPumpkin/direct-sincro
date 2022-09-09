import { Fragment, useContext, useState } from "react"
import { UserContext } from "./UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"
import { Link, Navigate } from "react-router-dom";

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => { if(response.ok) dispatch()})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const Navbar = () => {

    const [user, dispatch] = useContext(UserContext)
    const [redirect, setRedir] = useState({redirect: null})

    if(!user || !user.logged){
        return(<Fragment></Fragment>)
    }

    const unsubClick = () => {
        let write = user
        const func = () => {
            write.subscritor = false
            dispatch(write)
        }
        request(`/api/subscritores/${user.nif}`, { method: "DELETE"}, func)
        setRedir({redirect:<Navigate to="/naosubscritor"/>})
    }

    let unsub = null
    if (user.subscritor) unsub = <button className="btn btn-outline-success my-2 my-sm-0 me-2" onClick={unsubClick}>Unsubscribe</button>

    const logoutClick = () => {
        const func = () => {
            dispatch({logged: false, nif: null, loading: false})
        }
        request("/logout", { method: "POST"}, func)
        setRedir({redirect:<Navigate to="/login"/>})
    }

    return(
        <Fragment>
            <nav className="navbar navbar-light bg-light">
                <div className="container-fluid justify-content-between" style={{margin: "auto", width: "90%"}}>
                    {redirect.redirect}
                    <Link className="navbar-brand ms-2" to="/">Home</Link>
                    {unsub}
                    <button className="btn btn-outline-success my-2 my-sm-0 me-2" onClick={logoutClick}>LogOut</button>
                </div>
            </nav>
        </Fragment>
    )
}