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
//TEMPORARIO para ver se funciona
/*
data class ContraordenacaoOutputModel (
    val numeroAuto: String,
    val data: Long,
    val classificacaoInfracao: String,
    val valorCoima: Double,
    val local: String,
    val entidadeAutuante: String
)
*/