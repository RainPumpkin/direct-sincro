import { Fragment } from "react"


export const DelegacaoVeiculo = (props) => {
    let elem = props.elem
    let inicio = new Date(elem.datainicio)
    let fim = new Date(elem.datafim)
    return(
        <Fragment>
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">nif:{elem.usuario}</h5>
                    <p className="card-text">Inicio:{inicio}    Fim:{fim}</p>
                </div>
            </div>
        </Fragment>
        
    )
}

/*
data class DelegacaoVeiculoOutputModel(
    val usuario: String?,
    val datainicio: Long,
    val datafim: Long,
)
 */