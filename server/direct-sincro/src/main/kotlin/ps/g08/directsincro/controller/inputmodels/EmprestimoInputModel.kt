package ps.g08.directsincro.controller.inputmodels

import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.getEpoch
import ps.g08.directsincro.common.getTimestamp

data class EmprestimoInputModel(
    val usuario: String,
    val datainicio: Long,
    val datafim: Long,
    val estado: String
)


fun getEmprestimoFromInputModel(input : EmprestimoInputModel) : EmprestimoMatricula{
    return EmprestimoMatricula(
        dataInicio = input.datainicio,
        dataFim = input.datafim,
        usuario = input.usuario,
        estado = input.estado
    )
}
