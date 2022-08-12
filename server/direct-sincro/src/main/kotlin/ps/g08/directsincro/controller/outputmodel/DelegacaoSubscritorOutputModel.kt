package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.DelegacaoSubscritor

data class DelegacaoSubscritorOutputModel(
    val matricula: String,
    val datainicio: Long,
    val datafim: Long,
)

fun getDelegacaoSubscritorOutputModel(delegacaoSubscritor: DelegacaoSubscritor) : DelegacaoSubscritorOutputModel {
    return DelegacaoSubscritorOutputModel(
        matricula = delegacaoSubscritor.matricula,
        datainicio = delegacaoSubscritor.dataInicio,
        datafim = delegacaoSubscritor.dataFim,
    )
}

fun getAllDelegacaoSubscritorOutputModel(delegacoesSubscritor: List<DelegacaoSubscritor>): List<DelegacaoSubscritorOutputModel>{
    val delegacoes = mutableListOf<DelegacaoSubscritorOutputModel>()
    for (delegacao in delegacoesSubscritor){
        delegacoes.add(getDelegacaoSubscritorOutputModel(delegacao))
    }
    return delegacoes
}