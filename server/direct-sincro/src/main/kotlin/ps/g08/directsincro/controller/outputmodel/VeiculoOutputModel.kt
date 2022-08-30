package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.Veiculo

data class VeiculoOutputModel(
    val matricula: String,
    val modelo: String,
    val categoria: String
)

data class VeiculoAllOutputModel(
    val matricula: String,
    val modelo: String,
    val categoria: String,
    val contraordenacoes: MultipleContraordenacaoOutputModel,
    val delegacoes: List<DelegacaoVeiculoOutputModel>
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

fun getVeiculoAllOutputModel(veiculo: Veiculo) : VeiculoAllOutputModel{
    return VeiculoAllOutputModel(
        matricula = veiculo.matricula,
        modelo = veiculo.modelo,
        categoria = veiculo.categoria,
        contraordenacoes = getMultipleContraordenacaoOutputModel(veiculo.contraordenacoes),
        delegacoes = getAllDelegacaoVeiculoOutputModel(veiculo.delegacoes)
    )
}

fun getMultipleVeiculoModel(veiculos: List<Veiculo>) : MultipleVeiculoOutputModel{
    val result = mutableListOf<VeiculoOutputModel>()

    for (veiculo in veiculos) {
        result.add(getVeiculoOutputModel(veiculo))
    }

    return MultipleVeiculoOutputModel(veiculos = result, number = result.size)
}
