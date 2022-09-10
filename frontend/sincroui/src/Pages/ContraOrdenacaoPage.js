import * as React from "react"
import { Fragment, useContext, useState, useEffect } from "react"
import { Link } from "react-router-dom"
import { useParams } from "react-router"
import { UserContext } from "../Components/UserContext"
import { ContraordenacaoCompleta } from "../Components/EventoTransitoCompleto"
import { cancellableFetch } from "../Services/CancellableFetch"
import { get } from "../Services/RequestService"

const ContraOrdenacaoP = (props) => {
    const [user, dispatch] = useContext(UserContext)
    const [loading, setLoading] = useState(true)
    const [contraordenacao, setContraordenacao] = useState({contraordenacao: null})
    const [errorInfo, setErrorInfo] = useState(null)
    const matricula = props.matricula
    const numeroAuto = props.numeroAuto

    const request = (uri, opts, dispatch) => {
        const fetch = cancellableFetch(uri, opts)
        fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
        .catch((error) => { console.log(error) })
        return fetch.abort
    }

    useEffect(() => {
        return get(
            `/api/subscritores/${user.nif}/veiculos/${matricula}/contraordenacoes/${numeroAuto}`,
            (data) => {
                setContraordenacao({elem: data})
                setLoading(false)
            },
            (error) => {
                setErrorInfo(error)
                setLoading(false)
            }
        )
    }, [matricula, numeroAuto, user.nif])

    const pagar = () => {
        window.location.reload()
        request(`/api/subscritores/${user.nif}/veiculos/${matricula}/contraordenacoes/${numeroAuto}`, { method: "POST" })
    }

    let butao = null
    if(contraordenacao != null && contraordenacao.elem != null &&contraordenacao.elem.estadoPagamento == 'Por pagar' && user.subscritor){
        butao = <button type="button" class="btn btn-primary" onClick={pagar}>Pagar</button>
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
                    <p><Link to={`/veiculos`}>Back to Veiculo</Link></p>
                </Fragment>
            )
        } else {
            // Info get
            console.log("result")

            return (
                <Fragment>
                    <h2>Contraordenação:</h2>
                    <ContraordenacaoCompleta elem={contraordenacao.elem}/>
                    <p></p>
                    {butao}
                </Fragment>
            )
        }
    }
}

const MemorableContraordenacao = React.memo(ContraOrdenacaoP)

export const ContraOrdenacaoPage = () => {
    const {matricula} = useParams()
    const {numeroAuto} = useParams()
    console.log("result")
    return(<MemorableContraordenacao matricula={matricula} numeroAuto={numeroAuto}/>)
}