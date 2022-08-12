package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.Contraordenacao

data class EventoOutputModel (
    val numeroAuto: String,
    val estadoPagamento: String,
    val data: Long,
    val tipo: String,
    val classificacaoInfracao: String,
    val descricao: String,
    val valor: Double,
    val localizacao: String,
    val entidadeAutuante: String,
    val dataLimiteDefesa: Long
)

data class MultipleEventoOutputModel(
    val eventos : List<EventoOutputModel>,
    val number : Int
)

fun getEventoOutputModel(obj: Contraordenacao) : EventoOutputModel{
    return EventoOutputModel(
        numeroAuto = obj.numeroAuto,
        estadoPagamento = obj.estadoPagamento,
        data = obj.data,
        tipo = obj.catagoriaVeiculo,
        classificacaoInfracao = obj.classificacaoInfracao,
        descricao = obj.descricao,
        valor = obj.valorCoima,
        localizacao = obj.local,
        entidadeAutuante = obj.entidadeAutuante,
        dataLimiteDefesa = obj.dataLimiteDefesa
    )
}

fun getMultipleEventoOutputModel(eventos : List<Contraordenacao>) : MultipleEventoOutputModel{
    val result = mutableListOf<EventoOutputModel>()

    for (evento in eventos) {
        result.add(getEventoOutputModel(evento))
    }

    return MultipleEventoOutputModel(eventos = result, number = result.size)
}

