package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class EmprestimoMatriculaOutputModel(
    val usuario: String?,
    val datainicio: Long,
    val datafim: Long,
    val estado: String
)

fun getEmprestimoMatriculaOutputModel(emprestimoMatricula: EmprestimoMatricula) : EmprestimoMatriculaOutputModel {
    return EmprestimoMatriculaOutputModel(
        usuario = emprestimoMatricula.usuario,
        datainicio = emprestimoMatricula.dataInicio,
        datafim = emprestimoMatricula.dataFim,
        estado = emprestimoMatricula.estado
    )
}