package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.Contraordenacao

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

data class MultipleContraordenacaoOutputModel(
    val contraordenacaos : List<ContraordenacaoOutputModel>,
    val number : Int
)

fun getContraordenacaoOutputModel(obj: Contraordenacao) : ContraordenacaoOutputModel{
    return ContraordenacaoOutputModel(
        numeroAuto = obj.numeroAuto,
        estadoPagamento = obj.estadoPagamento,
        data = obj.data,
        catagoriaVeiculo = obj.catagoriaVeiculo,
        classificacaoInfracao = obj.classificacaoInfracao,
        descricao = obj.descricao,
        valorCoima = obj.valorCoima,
        local = obj.local,
        entidadeAutuante = obj.entidadeAutuante,
        dataLimiteDefesa = obj.dataLimiteDefesa
    )
}

fun getMultipleContraordenacaoOutputModel(contraordenacoes : List<Contraordenacao>) : MultipleContraordenacaoOutputModel{
    val result = mutableListOf<ContraordenacaoOutputModel>()

    for (contraordenacao in contraordenacoes) {
        result.add(getContraordenacaoOutputModel(contraordenacao))
    }

    return MultipleContraordenacaoOutputModel(contraordenacaos = result, number = result.size)
}

