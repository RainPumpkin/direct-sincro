import { Fragment, useContext } from "react"
import { Link } from "react-router-dom"
import { UserContext } from "../Components/UserContext"
import { UserInfo } from "../Components/UserInfo"

export const Home = () => {
    const [user] = useContext(UserContext)

    return(
        <Fragment>
            <h1>Direct-Sincro Application</h1>
            <p></p>
            <UserInfo elem={user}/>
            <p></p>
            <h2><Link to = "/veiculos">Veiculos</Link></h2>
            <p></p>
            <h2><Link to = "/delegacoes">Delegações</Link></h2>
        </Fragment>
    )
}
