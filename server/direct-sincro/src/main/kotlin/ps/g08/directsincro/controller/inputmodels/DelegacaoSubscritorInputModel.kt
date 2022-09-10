package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.DelegacaoSubscritor
import ps.g08.directsincro.common.DelegacaoVeiculo

data class DelegacaoSubscritorInputModel(
    val dataCriacao: Long,
    val matricula: String
)


fun getDelegacaoSubFromInputModel(input : DelegacaoSubscritorInputModel) : DelegacaoSubscritor {
    return DelegacaoSubscritor(
        dataCriacao = input.dataCriacao,
        dataInicio = null,
        dataFim = null,
        matricula = input.matricula
    )
}