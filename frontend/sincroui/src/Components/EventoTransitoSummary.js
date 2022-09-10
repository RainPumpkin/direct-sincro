import { Fragment } from "react"
import { Link } from "react-router-dom"

export const ContraordenacaoSumario = (props) => {
    let elem = props.elem
    return(
        <Fragment>
            <h2><Link to= {`/veiculo/${props.matricula}/contraordenacoes/${elem.numeroAuto}`}>{elem.numeroAuto}</Link></h2>
        </Fragment>
    )
}