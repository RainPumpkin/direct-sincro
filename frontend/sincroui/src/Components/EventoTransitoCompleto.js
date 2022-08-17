export const ContraordenacaoCompleta = (props) => {
    let curr = Date.now()
    let butao
    if(curr <= props.dataLimiteDefesa){
        butao = <button type="button" class="btn btn-primary">Pedido de Defesa</button>
    } else {
        butao = <button type="button" class="btn btn-light">Pedido de Defesa</button>
    }
    return(
        <div>
            <ul class="list-group">
                <ul class="list-group-item">numeroAuto={props.numeruAuto}</ul>
                <ul class="list-group-item">estadoPagamento={props.estadoPagamento}</ul>
                <ul class="list-group-item">data={props.data}</ul>
                <ul class="list-group-item">categoriaVeiculo={props.categoriaVeiculo}</ul>
                <ul class="list-group-item">classificacaoInfracao={props.classificacaoInfracao}</ul>
                <ul class="list-group-item">descricao={props.descricao}</ul>
                <ul class="list-group-item">valorCoima={props.valorCoima}</ul>
                <ul class="list-group-item">local={props.local}</ul>
                <ul class="list-group-item">entidadeAutuante={props.entidadeAutuante}</ul>
                <ul class="list-group-item">dataLimiteDefesa={props.dataLimiteDefesa}</ul>
            </ul>
            {butao}
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