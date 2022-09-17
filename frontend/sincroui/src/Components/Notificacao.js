import { cancellableFetch } from "../Services/CancellableFetch"
import { useState} from "react"
import { Link } from "react-router-dom"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.text())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}
export const Notificacao = (props) => {
    console.log("notificacao")
    const not = props.elem
    const [veic, setVeic] = useState(null)
    const emitida = new Date(not.emitida).toLocaleString()
    const visualizada = () => {
        if(not.visualizada) return "Sim"
        return "Não"
    }
    const func = (ok, text) =>{
        text.then((text) => setVeic(text))
    }
    request(`/api/subscritores/${props.nif}/contraordenacoes/${not.contraordenacao}`, { 
        method: "GET"}, func)
        
    return(
        <div className="card border border-dark">
            <div className="card-body">
                <h3 className="card-title">{`Emissao Notificacao [${emitida}]`}&emsp;<Link to={`/veiculo/${veic}/contraordenacoes/${not.contraordenacao}`} className="card-link">{
                `Contraordenação [${not.contraordenacao}]`}</Link></h3>
                <p className="card-text">Mensagem:&emsp;{not.mensagem}&emsp;Visualizada:&emsp;{visualizada()}&emsp;Tipo:&emsp;{not.tipo}</p>
            </div>
        </div>
    )
}