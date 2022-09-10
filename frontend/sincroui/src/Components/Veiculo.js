import { Link } from "react-router-dom"

export const Veiculo = (props) => {
    let func = props.func
    let criar = props.criar
    const veic = props.elem
    return(
        <div className="card border border-dark">
            <div className="card-body">
                <h3 className="card-title"><Link to={`/veiculo/${veic.matricula}`} className="card-link">{
                `Veiculo Matricula [${veic.matricula}]`}</Link>&emsp;{func}&emsp;{criar}</h3>
                <p className="card-text">Modelo: {veic.modelo}&emsp;Categoria: {veic.categoria}</p>

            </div>
        </div>
    )
}
/*
data class VeiculoOutputModel(
    val matricula: String,
    val modelo: String,
    val categoria: String
)
*/