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

export const Register = () => {

    const [user, dispatch] = useContext(UserContext)
    const [warning, setWarning] = useState(null)
    const [registered, setReg] = useState(false)

    const onSubmitHandler = (event) => {
        event.preventDefault()
        if(event.target.password.value !== event.target.confP.value){
            setWarning("Mismatch Passwords")
        } else {
            const body = {nome: event.target.nome.value, nif: event.target.nif.value, tituloConducao: event.target.tituloConducao.value, email: event.target.email.value, password: event.target.password.value}
            const func = (ok) => {
                if(ok) {
                    setReg(true)
                    setWarning(null)
                } else {
                    setReg(false)
                    setWarning("Invalid credentials")
                }
            }
            request("/register", { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, func)
        }
    }

    let redirect = null
    if(user!=null && user.logged) redirect = <Navigate to="/"/>
    else if(registered){
        redirect = <Navigate to="/login"/>
    }

    let notification
    if(warning != null) {
        notification = <div className="alert alert-danger" role="alert">{warning}</div>
    }

    return (
        <Fragment>
            {redirect}
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="nome">Name</label>
                    <input type="text" name="nome" className="form-control" id="nome" placeholder="Enter name"/>
                </div>
                <div className="form-group">
                    <label htmlFor="nif">Nif</label>
                    <input type="text" name="nif" className="form-control" id="nif" pattern="[0-9]{9}" placeholder="Enter nif"/>
                </div>
                <div className="form-group">
                    <label htmlFor="tituloConducao">TituloConducao</label>
                    <input type="text" name="tituloConducao" className="form-control" id="tituloConducao" placeholder="Enter Titulo Conducao"/>
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input type="email" name="email" className="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email"/>
                </div>
                <div className="form-group">
                    <label htmlFor="pass">Password</label>
                    <input type="password" name="password" className="form-control" id="pass" placeholder="Password"/>
                </div>
                <div className="form-group">
                    <label htmlFor="Confpass">Confirm Password</label>
                    <input type="password" name="confirmPass" className="form-control" id="confP" placeholder="Confirm Password"/>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
            {notification}
        </Fragment>
    )
}