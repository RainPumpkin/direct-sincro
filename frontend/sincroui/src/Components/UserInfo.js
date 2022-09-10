import { Fragment } from "react"



export const UserInfo = (props) => {
    let user = props.elem
    return(
        <Fragment>
            <h4>Nome: {user.nome}</h4>
            <h4>Nif: {user.nif}</h4>
            <h4>Email: {user.email}</h4>
        </Fragment>
        
    )
}//{logged: true, nif: data.nif, email:data.email, nome:data.nome, subscritor:data.subscritor}
