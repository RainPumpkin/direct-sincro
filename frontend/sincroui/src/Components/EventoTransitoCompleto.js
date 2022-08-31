export const ContraordenacaoCompleta = (props) => {
    let elem = props.elem
    let curr = Date.now()
    let butao
    if(curr <= elem.dataLimiteDefesa){
        butao = <button type="button" class="btn btn-primary">Pedido de Defesa</button>
    } else {
        butao = <button type="button" class="btn btn-light">Pedido de Defesa</button>
    }
    return(
        <div>
            <ul class="list-group">
                <ul class="list-group-item">numeroAuto={elem.numeruAuto}</ul>
                <ul class="list-group-item">estadoPagamento={elem.estadoPagamento}</ul>
                <ul class="list-group-item">data={elem.data}</ul>
                <ul class="list-group-item">categoriaVeiculo={elem.categoriaVeiculo}</ul>
                <ul class="list-group-item">classificacaoInfracao={elem.classificacaoInfracao}</ul>
                <ul class="list-group-item">descricao={elem.descricao}</ul>
                <ul class="list-group-item">valorCoima={elem.valorCoima}</ul>
                <ul class="list-group-item">local={elem.local}</ul>
                <ul class="list-group-item">entidadeAutuante={elem.entidadeAutuante}</ul>
                <ul class="list-group-item">dataLimiteDefesa={elem.dataLimiteDefesa}</ul>
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