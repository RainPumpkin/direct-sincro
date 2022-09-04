import { Fragment, useContext, useState } from "react"
import { Link } from "react-router-dom"
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

    const onSubmitHandler = (event) => {
        event.preventDefault()
        const body = {nome: event.target.nome.value, nif: event.target.nif.value, tituloConducao: event.target.tituloConducao.value, email: event.target.email.value, password: event.target.password.value}
        const func = (ok) => {
            if(ok) {
                dispatch({nome: event.target.nome.value, nif: event.target.nif.value, tituloConducao: event.target.tituloConducao.value, email: event.target.email.value, password: event.target.password.value})
                setWarning(null)
                /*
                json.then((user)  => {
                    dispatch({logged: true, nif: event.target.nif.value, nome: user.nome, email: user.email, subscritor: user.subscritor})
                    setWarning(null)
                })*/
            } else {
                dispatch({noem: null, nif: null, tituloConducao: null, email: null, password: null})
                setWarning("Invalid credentials")
            }
        }
        request("/register", { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, func)
    }

    let notification
    if(warning != null) {
        notification = <div className="alert alert-danger" role="alert">{warning}</div>
    }

    return (
        <Fragment>
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="exampleInputName1">Name</label>
                    <input type="text" name="nome" className="form-control" id="exampleInputName1" placeholder="Enter name"/>
                </div>
                <div className="form-group">
                    <label htmlFor="exampleInputNif1">Nif</label>
                    <input type="text" name="nif" className="form-control" id="exampleInputNif1" placeholder="Enter nif"/>
                </div>
                <div className="form-group">
                    <label htmlFor="exampleInputTituloConducao1">TituloConducao</label>
                    <input type="text" name="tituloConducao" className="form-control" id="exampleInputTituloConducao1" placeholder="Enter Titulo Conducao"/>
                </div>
                <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Email</label>
                    <input type="text" name="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"/>
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