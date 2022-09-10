import { Fragment, useContext, useState, useEffect } from "react"
import { Link } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { DelegacaoSubscritor } from "../Components/DelegacaoSubscritor"
import { cancellableFetch } from "../Services/CancellableFetch"
import {get} from "../Services/RequestService"

const request = (uri, opts, dispatch) => {
    const fetch = cancellableFetch(uri, opts)
    fetch.ready.then((response) => {if(!fetch.signal.aborted) dispatch(response.ok, response.json())})
    .catch((error) => { console.log(error) })
    return fetch.abort
}

export const DelegacoesPage = () => {
    const [user, dispatch] = useContext(UserContext)
    const [loading, setLoading] = useState(true)
    const [delegacoes, setDelegacoes] = useState({elem:null})
    const [errorInfo, setErrorInfo] = useState(null)

    useEffect(() => {
        return get(
            `/api/subscritores/${user.nif}/delegados`,
            (data) => {
                setDelegacoes(data)
                setLoading(false)
            },
            (error) => {
                setErrorInfo(error)
                setLoading(false)
            }
        )
    }, [user.nif]);

    if (loading) {
        // Still loading
        return (
            <Fragment>
            </Fragment>
        )
    } else {
        if (errorInfo) {
            // Error happened
            return (
                <Fragment>
                    <h3>{errorInfo.code} - {errorInfo.title}</h3>
                    <hr></hr>
                    <p>{errorInfo.description}</p>
                    <p><Link to={`/Home`}>Back to home</Link></p>
                </Fragment>
            )
        } else {
            // Info get
            return (
                <Fragment>
                    <h2>Delegações:</h2>
                    {delegacoes.delegacoes.map((elem, idx) => <DelegacaoSubscritor key={idx} elem={elem}/>)}
                </Fragment>
            )
        }
    }
}