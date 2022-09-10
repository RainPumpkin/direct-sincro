import { Fragment } from "react"


export const DelegacaoVeiculo = (props) => {
    let eliminarDlegacao = props.eliminarDlegacao
    let elem = props.elem
    let inicio = elem.datainicio? new Date(elem.datainicio).toLocaleString():null
    let fim = elem.datafim? new Date(elem.datafim).toLocaleString():null
    let dataCriacao = new Date(elem.dataCriacao).toLocaleString()
    return(
        <Fragment>
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">nif:{elem.subscritor}&emsp;{eliminarDlegacao}</h5>
                    <p className="card-text">Data Criacao: {dataCriacao}&emsp;Inicio:{inicio}&emsp;Fim:{fim}</p>
                </div>
            </div>
        </Fragment>
        
    )
}