import * as React from "react"
import { Fragment, useContext, useState, useEffect } from "react"
import { Link } from "react-router-dom"
import { useParams } from "react-router"
import { UserContext } from "../Components/UserContext"
import { Veiculo } from "../Components/Veiculo"
import { get } from "../Services/RequestService"
import { ContraordenacaoSumario } from "../Components/EventoTransitoSummary"
import { DelegacaoVeiculo } from "../Components/DelegacaoVeiculo"
import { cancellableFetch } from "../Services/CancellableFetch"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok)})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

let eliminarDelegacao = null
let criar = null

const VeiculoP = (props) => {
    const [user, dispatch] = useContext(UserContext)
    const [loading, setLoading] = useState(true)
    const [veiculo, setVeiculo] = useState({veic: null})
    const [errorInfo, setErrorInfo] = useState(null)
    const matricula = props.matricula

    useEffect(() => {
        return get(
            `/api/subscritores/${user.nif}/veiculos/${matricula}/all`,
            (data) => {
                setVeiculo({veic: data})
                setLoading(false)
            },
            (error) => {
                setErrorInfo(error)
                setLoading(false)
            }
        )
    }, [matricula, user.nif])

    let content = <h1 className="text-center">404 Vehicle not found</h1>

    const redirectInfoVeiculo = () => { window.location.reload() }
    const redirect = () => { window.location.assign("/veiculos") }
    const redirectToForm = () => {
        window.location.assign(`/delegarform/${matricula}`)
    }
    
    let criar = null
    let eliminarVeiculo = null
    console.warn(veiculo)
    if(veiculo.veic != null){
        const delegacoes = veiculo.veic.delegacoes.delegacoes
        if (delegacoes.length === 0 || delegacoes.find(delegacao => 
            delegacao.datafim != null)){
            
            criar = <button className="btn btn-primary" onClick={redirectToForm}>Criar Delegação</button>
            
        }
        if (delegacoes.length === 0 || delegacoes.find(delegacao => delegacao.datainicio === null && delegacao.datafim === null)) {
            eliminarDelegacao = <button type="button" className="btn btn-primary" onClick={() => 
                request(`/api/subscritores/${user.nif}/veiculos/${matricula}/delegacoes/delete`, { 
                    method: "DELETE", headers: {'Content-Type': 'application/json'}, body: 
                        veiculo.veic.delegacoes.delegacoes[0].dataCriacao}, redirectInfoVeiculo)
            }>Eliminar Delegação</button>
            
            eliminarVeiculo = <button type="button" className="btn btn-primary" onClick={() => 
                request(`/api/subscritores/${user.nif}/veiculos/${matricula}`, { 
                    method: "DELETE"}, redirect)
            }>Eliminar Veiculo</button>
        }
    }
    
    

    if (loading) {
        // Still loading
        console.log("still loading")
        return (
            <Fragment>
            </Fragment>
        )
    } else {
        if (errorInfo) {
            // Error happened
            console.log("error")
            return (
                <Fragment>
                    <h3>{errorInfo.code} - {errorInfo.title}</h3>
                    <hr></hr>
                    <p>{errorInfo.description}</p>
                    <p><Link to={`/veiculos`}>Back to Veiculos</Link></p>
                </Fragment>
            )
        } else {
            // Info get
            console.log("result")

            return (
                <Fragment>
                      
                    <Veiculo elem={veiculo.veic} func={eliminarVeiculo} criar={criar}/>
                    <ContraordencaoList elem={veiculo.veic.contraordenacoes.contraordenacoes} matricula={matricula}/>
                    <DelegacoesList elem={veiculo.veic.delegacoes} matricula={matricula} />
                    
                </Fragment>
            )
        }
    }
}

//props.elem
const ContraordencaoList =  (props) => {
    if(props.elem==null) return(
        <Fragment>
            <h2>Lista de Contraordenacoes</h2>
            <h4>Não existe nenhuma contraordenação</h4>
        </Fragment>
    )
    return(
        <Fragment>
            <h2>Lista de Contraordenacoes</h2>
            {props.elem.map((elem, idx) => <ContraordenacaoSumario key={idx} elem = {elem} matricula={props.matricula}/>)}
        </Fragment>
    )
}

const DelegacoesList = (props) => {
    return(
        <Fragment>
            <h2>Lista de Delegações</h2>
            {props.elem.delegacoes.map((elem, idx) =><DelegacaoVeiculo key={idx} elem={elem} matricula={props.matricula} eliminarDlegacao={eliminarDelegacao}/> )}
        </Fragment>
    )
}

const MemorableVeiculo = React.memo(VeiculoP)

export const VeiculoPage = () => {
    const {matricula} = useParams()
    return(<MemorableVeiculo matricula={matricula}/>)
}

/*
veiculo info
list de contraordenações
list de emprestimos
*/
//add collapse to lists
//https://getbootstrap.com/docs/5.0/components/collapse/