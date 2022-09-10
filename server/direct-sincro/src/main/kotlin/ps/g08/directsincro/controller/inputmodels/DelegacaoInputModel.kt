package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.DelegacaoVeiculo

data class DelegacaoInputModel(
    val subscritor: String,
    val dataCriacao: Long,
    val dataInicio: Long?,
    val dataFim: Long?,
)


fun getDelegacaoFromInputModel(input : DelegacaoInputModel) : DelegacaoVeiculo{
    return DelegacaoVeiculo(
        dataCriacao = input.dataCriacao,
        dataInicio = input.dataInicio,
        dataFim = input.dataFim,
        subscritor = input.subscritor,
    )
}
