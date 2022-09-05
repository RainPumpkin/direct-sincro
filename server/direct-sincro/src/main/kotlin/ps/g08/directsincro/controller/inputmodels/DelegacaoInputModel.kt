package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.DelegacaoVeiculo

data class DelegacaoInputModel(
    val usuario: String,
    val dataInicio: Long,
    val dataFim: Long?,
    val estado: String
)


fun getDelegacaoFromInputModel(input : DelegacaoInputModel) : DelegacaoVeiculo{
    return DelegacaoVeiculo(
        dataInicio = input.dataInicio,
        dataFim = input.dataFim,
        usuario = input.usuario,
    )
}
