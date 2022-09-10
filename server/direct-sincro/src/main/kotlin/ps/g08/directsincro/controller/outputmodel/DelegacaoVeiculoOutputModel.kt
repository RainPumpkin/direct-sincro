package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.DelegacaoVeiculo

data class DelegacaoVeiculoOutputModel(
    val Subscritor: String,
    val datainicio: Long?,
    val datafim: Long?,
    val dataCriacao: Long
)

data class MultipleDelegacaoVeiculoOutputModel(
    val delegacoes: List<DelegacaoVeiculoOutputModel>,
    val number : Int
)

fun getDelegacaoVeiculoOutputModel(delegacaoVeiculo: DelegacaoVeiculo) : DelegacaoVeiculoOutputModel {
    return DelegacaoVeiculoOutputModel(
        Subscritor = delegacaoVeiculo.subscritor,
        datainicio = delegacaoVeiculo.dataInicio,
        datafim = delegacaoVeiculo.dataFim,
        dataCriacao = delegacaoVeiculo.dataCriacao
    )
}

fun getAllDelegacaoVeiculoOutputModel(delegacoesVeiculo: List<DelegacaoVeiculo>): MultipleDelegacaoVeiculoOutputModel{
    val delegacoes = mutableListOf<DelegacaoVeiculoOutputModel>()
    for (delegacao in delegacoesVeiculo){
        delegacoes.add(getDelegacaoVeiculoOutputModel(delegacao))
    }
    return MultipleDelegacaoVeiculoOutputModel(delegacoes = delegacoes, number = delegacoes.size)
}