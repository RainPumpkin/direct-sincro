import { Fragment } from "react"


export const DelegacaoSubscritor = (props) => {
    let elem = props.elem
    let inicio = new Date(elem.datainicio).toLocaleString()
    let fim = elem.datafim? new Date(elem.datafim).toLocaleString():null
    return(
        <Fragment>
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">matricula: {elem.matricula}</h5>
                    <p className="card-text">Inicio: {inicio}   Fim: {fim}</p>
                </div>
            </div>
        </Fragment>
        
    )
}