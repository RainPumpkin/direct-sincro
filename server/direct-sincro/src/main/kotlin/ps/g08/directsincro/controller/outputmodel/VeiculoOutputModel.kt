package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.Veiculo

data class VeiculoOutputModel(
    val matricula: String,
    val modelo: String,
    val categoria: String
)

data class MultipleVeiculoOutputModel(
    val veiculos: List<VeiculoOutputModel>,
    val number : Int
)

fun getVeiculoOutputModel(veiculo: Veiculo) : VeiculoOutputModel{
    return VeiculoOutputModel(
        matricula = veiculo.matricula,
        modelo = veiculo.modelo,
        categoria = veiculo.categoria
    )
}

fun getMultipleVeiculoModel(veiculos: List<Veiculo>) : MultipleVeiculoOutputModel{
    val result = mutableListOf<VeiculoOutputModel>()

    for (veiculo in veiculos) {
        result.add(getVeiculoOutputModel(veiculo))
    }

    return MultipleVeiculoOutputModel(veiculos = result, number = result.size)
}
