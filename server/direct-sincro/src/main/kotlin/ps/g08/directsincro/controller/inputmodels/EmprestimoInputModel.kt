package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.common.getTimestamp

data class EmprestimoInputModel(
    val usuario: String,
    val dataInicio: Long,
    val dataFim: Long,
    val estado: String
)


fun getEmprestimoFromInputModel(input : EmprestimoInputModel) : EmprestimoMatricula{
    return EmprestimoMatricula(
        dataInicio = input.dataInicio,
        dataFim = input.dataFim,
        usuario = input.usuario,
        estado = input.estado
    )
}
