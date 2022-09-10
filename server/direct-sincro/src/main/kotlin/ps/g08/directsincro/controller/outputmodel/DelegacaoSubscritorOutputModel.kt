package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.DelegacaoSubscritor

data class DelegacaoSubscritorOutputModel(
    val dataCriacao: Long,
    val matricula: String,
    val datainicio: Long?,
    val datafim: Long?,
)

data class MultipleDelegacaoSubscritorOutputModel(
    val delegacoes: List<DelegacaoSubscritorOutputModel>,
    val number : Int
)

fun getDelegacaoSubscritorOutputModel(delegacaoSubscritor: DelegacaoSubscritor) : DelegacaoSubscritorOutputModel {
    return DelegacaoSubscritorOutputModel(
        matricula = delegacaoSubscritor.matricula,
        datainicio = delegacaoSubscritor.dataInicio,
        datafim = delegacaoSubscritor.dataFim,
        dataCriacao = delegacaoSubscritor.dataCriacao
    )
}

fun getAllDelegacaoSubscritorOutputModel(delegacoesSubscritor: List<DelegacaoSubscritor>): MultipleDelegacaoSubscritorOutputModel{
    val delegacoes = mutableListOf<DelegacaoSubscritorOutputModel>()
    for (delegacao in delegacoesSubscritor){
        delegacoes.add(getDelegacaoSubscritorOutputModel(delegacao))
    }
    return MultipleDelegacaoSubscritorOutputModel(delegacoes = delegacoes, number = delegacoes.size)
}