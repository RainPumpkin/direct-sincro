import { Fragment } from "react"
import { cancellableFetch } from "../Services/CancellableFetch"


const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const DelegacaoSubscritor = (props) => {
    
    let elem = props.elem
    let inicio = elem.datainicio? new Date(elem.datainicio).toLocaleString():null
    let fim = elem.datafim? new Date(elem.datafim).toLocaleString():null
    let dataCriacao = new Date(elem.dataCriacao).toLocaleString()
    let redirect = window.location.reload()
    let button = null

    console.log(props)
    const checkOngoingDelegacoes = () => {
        console.warn()
        if(elem.datainicio != null) {
            button =
            <button type="button" className="btn btn-primary" onClick={() => 
                request(`/api/subscritores/${props.nif}/delegados/${elem.matricula}/aceitar`, { 
                    method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(dataCriacao)
                }, redirect)}>Entregar</button>
        } 
        /*if(delegacao.datainicio === null) {
            button = () => {
                <p>
                    <button type="button" className="btn btn-primary" onClick={() => 
                    request(`/api/subscritores/${user.nif}/delegados/${matricula}/aceitar`, { 
                        method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(dataCriacao)
                    }, redirect)}>Entregar</button>
                    <button type="button" className="btn btn-primary" onClick={() => 
                    request(`/api/subscritores/${user.nif}/delegados/${matricula}/aceitar`, { 
                        method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(dataCriacao)
                    }, redirect)}>Entregar</button>
                </p>
                
            }
                
        }*/
        
    }
    return(
        <Fragment>
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">matricula: {elem.matricula}</h5>
                    <p className="card-text">Inicio: {inicio}&emsp;Fim: {fim}</p>
                </div>
            </div>
        </Fragment>
        
    )
}