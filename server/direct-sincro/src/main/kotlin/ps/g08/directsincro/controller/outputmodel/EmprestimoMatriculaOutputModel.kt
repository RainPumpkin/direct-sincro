package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.EmprestimoMatricula
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class EmprestimoMatriculaOutputModel(
    val usuario: String?,
    val datainicio: Timestamp,
    val datafim: Timestamp,
    val estado: String
)

fun getEmprestimoMatriculaOutputModel(emprestimoMatricula: EmprestimoMatricula) : EmprestimoMatriculaOutputModel {
    return EmprestimoMatriculaOutputModel(
        usuario = emprestimoMatricula.usuario,
        datainicio = getTimestamp(emprestimoMatricula.dataInicio),
        datafim = getTimestamp(emprestimoMatricula.dataFim),
        estado = emprestimoMatricula.estado
    )
}