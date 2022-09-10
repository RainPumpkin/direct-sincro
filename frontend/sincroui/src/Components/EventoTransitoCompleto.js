export const ContraordenacaoCompleta = (props) => {
    let elem = props.elem
    
    return(
        <div>
            <ul className="list-group">
                <ul className="list-group-item">numeroAuto: {elem.numeroAuto}</ul>
                <ul className="list-group-item">estadoPagamento: {elem.estadoPagamento}</ul>
                <ul className="list-group-item">data: {elem.data}</ul>
                <ul className="list-group-item">categoriaVeiculo: {elem.categoriaVeiculo}</ul>
                <ul className="list-group-item">classificacaoInfracao: {elem.classificacaoInfracao}</ul>
                <ul className="list-group-item">descricao: {elem.descricao}</ul>
                <ul className="list-group-item">valorCoima: {elem.valorCoima}</ul>
                <ul className="list-group-item">local: {elem.local}</ul>
                <ul className="list-group-item">entidadeAutuante: {elem.entidadeAutuante}</ul>
                <ul className="list-group-item">dataLimiteDefesa: {elem.dataLimiteDefesa}</ul>
                <ul className="list-group-item">normaInfringida: {elem.normaInfringida}</ul>
            </ul>
        </div>
    )
}

/*
data class ContraordenacaoOutputModel (
    val numeroAuto: String,
    val estadoPagamento: String,
    val data: Long,
    val catagoriaVeiculo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valorCoima: Double,
    val local: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Long
)
*/