import { Fragment, useContext, useState } from "react"
import { Navigate, Link } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const Delegar = (props) => {
    const [user, dispatch] = useContext(UserContext)
    const [redirect, setRedir] = useState({redirect: null})
    const matricula = props.matricula

    const onSubmitHandler = (event) => {
        event.preventDefault()
        const body = {dataCriacao: Date.now(), subscritor: event.targer.nif.value, dataInicio: null, dataFim: null}
        const func = () => {
            window.location.reload()
        }
        request(`/api/subscritores/${user.nif}/veiculos/${matricula}/delegacoes`, { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)}, func)
    }

    return (
        <Fragment>
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="nif">Nif</label>
                    <input type="text" name="nif" className="form-control" id="nif" pattern="[0-9]{9}" placeholder="Enter nif"/>
                </div>
                <p></p>
                <p></p>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </Fragment>
    )
}

