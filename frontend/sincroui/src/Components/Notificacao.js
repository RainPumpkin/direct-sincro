import { Link } from "react-router-dom"

export const Notificacao = (props) => {
    console.log("notificacao")
    const not = props.elem
    return(
        <div className="card border border-dark">
            <div className="card-body">
                <h3 className="card-title">{`Emissao Notificacao [${not.emitida}]`}</h3>
                <p className="card-text">Mensagem: {not.mensagem}   Visualizada: {not.visualizada.toString()}   Tipo: {not.tipo}</p>
            </div>
        </div>
    )
}