import { Fragment, useContext, useState, useEffect } from "react"
import { Link } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { Veiculo } from "../Components/Veiculo"
import {get} from "../Services/RequestService"

export const VeiculosPage = () => {
    const [user] = useContext(UserContext)
    const [loading, setLoading] = useState(true)
    const [veiculos, setVeiculos] = useState(null)
    const [errorInfo, setErrorInfo] = useState(null)

    useEffect(() => {
        return get(
            `/api/subscritores/${user.nif}/veiculos`,
            (data) => {
                setVeiculos(data)
                setLoading(false)
            },
            (error) => {
                setErrorInfo(error)
                setLoading(false)
            }
        )
    }, [user.nif]);

    const redirect = () => {
        if(user.subscritor)
            window.location.assign("/adicionarveiculo")
    }

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
                    <h2>Veiculos:</h2>
                    {veiculos.veiculos.map((elem, idx) => <Veiculo key={idx} elem={elem}/>)}
                    <p></p>
                    <button type="button" className="btn btn-primary" onClick={redirect}>Adicionar Veiculo</button>
                </Fragment>
            )
        }
    }
}