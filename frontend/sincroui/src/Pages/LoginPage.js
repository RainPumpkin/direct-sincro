import { Fragment, useContext, useState } from "react"
import { Navigate } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok)})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const Login = () => {

    const [user, dispatch] = useContext(UserContext)
    const [warning, setWarning] = useState(null)

    const onSubmitHandler = (event) => {
        event.preventDefault()
        const body = {nif: event.target.nif.value, password: event.target.password.value}
        const func = (ok) => {
            if(ok) {
                dispatch({logged: true, nif: event.target.nif.value})
                setWarning(null)
                /*
                json.then((user)  => {
                    dispatch({logged: true, nif: event.target.nif.value, nome: user.nome, email: user.email, subscritor: user.subscritor})
                    setWarning(null)
                })*/
            } else {
                dispatch({logged: false, nif: null, nome:null, email:null, subscritor:null})
                setWarning("Invalid credentials")
            }
        }
        request("/login", { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, func)
    }

    let notification
    if(warning != null) {
        notification = <div className="alert alert-danger" role="alert">{warning}</div>
    }

    let redirect = null
    if(user!=null && user.logged) redirect = <Navigate to="/"/>

    return (
        <Fragment>
            {redirect}
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Name</label>
                    <input type="text" name="nif" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name"/>
                </div>
                <div className="form-group">
                    <label htmlFor="exampleInputPassword1">Password</label>
                    <input type="password" name="password" className="form-control" id="exampleInputPassword1" placeholder="Password"/>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
            {notification}
        </Fragment>
    )
}