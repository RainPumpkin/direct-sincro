package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.Veiculo

data class VeiculoInputModel (
    val matricula: String,
    val modelo: String,
    val categoria: String
    )

fun getVeiculoFromVeiculoInputModel(input : VeiculoInputModel, subscritor: String) : Veiculo {
    return Veiculo(
        matricula = input.matricula,
        modelo = input.modelo,
        categoria = input.categoria,
        delegacoes = emptyList(),
        contraordenacoes = emptyList(),
        owner = subscritor
    )
}