import { Fragment, useContext, useState, useEffect } from "react"
import { Link } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { Notificacao } from "../Components/Notificacao"
import { get } from "../Services/RequestService"

export const NotificacoesPage = () => {
    const [user] = useContext(UserContext)
    const [loading, setLoading] = useState(true)
    const [notificacoes, setNotificacoes] = useState({not: null})
    const [errorInfo, setErrorInfo] = useState(null)

    useEffect(() => {
        return get(
            `/api/subscritores/${user.nif}/notificacoes`,
            (data) => {
                setNotificacoes(data)
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
                    <h2>Notificações:</h2>
                    {notificacoes.notificacoes.map((elem, idx) => <Notificacao key={idx} elem={elem}/>)}
                </Fragment>
            )
        }
    }
}