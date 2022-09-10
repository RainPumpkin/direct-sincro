import { Fragment, useContext, useState } from "react"
import { Navigate } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { cancellableFetch } from "../Services/CancellableFetch"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => { if(response.ok) dispatch()})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const AdicionarVeiculo = () => {
    const [user, dispatch] = useContext(UserContext)

    const onSubmitHandler = (event) => {
        event.preventDefault()
            const body = {matricula: event.target.matricula.value, modelo: event.target.modelo.value, categoria: event.target.categoria.value, owner: user.nif}
            
            request(`/api/subscritores/${user.nif}/veiculos`, { method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)})
            window.location.assign("/veiculos")
        }
    

    return (
        <Fragment>
            <form onSubmit={onSubmitHandler}>
                <div className="form-group">
                    <label htmlFor="matricula">Matricula</label>
                    <input type="text" name="matricula" className="form-control" id="matricula" pattern="[A-Z]|[0-9])([A-Z]|[0-9])-([A-Z]|[0-9])([A-Z]|[0-9])-([A-Z]|[0-9])([A-Z]|[0-9]" placeholder="Enter matricula"/>
                </div>
                <div className="form-group">
                    <label htmlFor="modelo">Modelo</label>
                    <input type="text" name="modelo" className="form-control" id="modelo" placeholder="Enter modelo"/>
                </div>
                <div className="form-group">
                    <label htmlFor="categoria">Categoria</label>
                    <input type="text" name="categoria" className="form-control" id="categoria" placeholder="Enter Categoria"/>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
            
        </Fragment>
    )

}