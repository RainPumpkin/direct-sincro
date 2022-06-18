package ps.g08.directsincro.controller.outputmodel

import ps.g08.directsincro.common.EmprestimoUsuario
import ps.g08.directsincro.common.getTimestamp
import java.sql.Timestamp

data class EmprestimoUsuarioOutputModel(
    val matricula: String,
    val datainicio: Timestamp,
    val datafim: Timestamp,
    val estado: String
)

fun getEmprestimoUsuarioOutputModel(emprestimoUsuario: EmprestimoUsuario) : EmprestimoUsuarioOutputModel {
    return EmprestimoUsuarioOutputModel(
        matricula = emprestimoUsuario.matricula,
        datainicio = getTimestamp(emprestimoUsuario.dataInicio),
        datafim = getTimestamp(emprestimoUsuario.dataFim),
        estado = emprestimoUsuario.estado
    )
}

fun getAllEmprestimosUsuarioOutputModel(emprestimosUsuario: List<EmprestimoUsuario>): List<EmprestimoUsuarioOutputModel>{
    val emprestimos = mutableListOf<EmprestimoUsuarioOutputModel>()
    for (emprestimo in emprestimosUsuario){
        emprestimos.add(getEmprestimoUsuarioOutputModel(emprestimo))
    }
    return emprestimos
}