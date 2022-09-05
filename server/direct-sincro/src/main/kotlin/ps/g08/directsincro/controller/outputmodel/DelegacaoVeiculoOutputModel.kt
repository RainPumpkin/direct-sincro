package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.DelegacaoVeiculo

data class DelegacaoVeiculoOutputModel(
    val usuario: String?,
    val datainicio: Long,
    val datafim: Long?,
)

data class MultipleDelegacaoVeiculoOutputModel(
    val delegacoes: List<DelegacaoVeiculoOutputModel>,
    val number : Int
)

fun getDelegacaoVeiculoOutputModel(delegacaoVeiculo: DelegacaoVeiculo) : DelegacaoVeiculoOutputModel {
    return DelegacaoVeiculoOutputModel(
        usuario = delegacaoVeiculo.usuario,
        datainicio = delegacaoVeiculo.dataInicio,
        datafim = delegacaoVeiculo.dataFim,
    )
}

fun getAllDelegacaoVeiculoOutputModel(delegacoesVeiculo: List<DelegacaoVeiculo>): MultipleDelegacaoVeiculoOutputModel{
    val delegacoes = mutableListOf<DelegacaoVeiculoOutputModel>()
    for (delegacao in delegacoesVeiculo){
        delegacoes.add(getDelegacaoVeiculoOutputModel(delegacao))
    }
    return MultipleDelegacaoVeiculoOutputModel(delegacoes = delegacoes, number = delegacoes.size)
}