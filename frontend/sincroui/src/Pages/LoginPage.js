import { Fragment, useContext, useState } from "react"
import { Navigate } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const Login = () => {

    const [user, dispatch] = useContext(UserContext)
    const [warning, setWarning] = useState(null)
    const [redirect, setRedir] = useState(null)

    const onSubmitHandler = (event) => {
        event.preventDefault()
        const body = {nif: event.target.nif.value, password: event.target.password.value}
        const func = (ok, json) => {  
            if(ok) {
                json.then((user)  => {
                    dispatch({logged: true, nif: event.target.nif.value, nome: user.nome, email: user.email, subscritor: user.subscritor})
                    setWarning(null)
                })
            } else {
                dispatch({logged: false, nif: null, nome:null, email:null, subscritor:null, loading: false})
                setWarning("Invalid credentials")
            }
        }
        request("/login", { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, func)
    }

    let notification
    if(warning != null) {
        notification = <div className="alert alert-danger" role="alert">{warning}</div>
    }

    if(user!=null && user.logged && !user.loading){
        console.log("hi go home")
        console.log(user)
        return(<Fragment><Navigate to="/"/></Fragment>)
    } 
    return (
        <Fragment>
            {redirect}
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Nif</label>
                    <input type="text" name="nif" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter nif"/>
                </div>
                <p></p>
                <div className="form-group">
                    <label htmlFor="exampleInputPassword1">Password</label>
                    <input type="password" name="password" className="form-control" id="exampleInputPassword1" placeholder="Password"/>
                </div>
                <p></p>
                <p></p>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
            {notification}
            <p></p>
            <button type="button" className="btn btn-primary" onClick={() => {setRedir(<Navigate to="/register"/>)}}>Register</button>
        </Fragment> 
    )
}