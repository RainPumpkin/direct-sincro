package ps.g08.directsincro.service

import org.springframework.stereotype.Component
import ps.g08.directsincro.common.Veiculo
import ps.g08.directsincro.database.VeiculoDatabase
import ps.g08.directsincro.service.mappers.VeiculoMapper

@Component
class VeiculoAlugadoService(private val db: VeiculoDatabase, private val mapper: VeiculoMapper) {

    fun getAllVeiculosAlugados(nif : String) : List<Veiculo>{
        //TODO fix mudar a query para uma query de join à tabela veiculos e emprestimo
        //TODO com base em timestamp do  System.currentTimeMillis() /1000 para ter em segundos

        /*Query:
            Select * from
            join de veiculo e emprestimo no parametro veiculo/matricula
            where usuario = {nif}
         */
        return mapper.multiple(db.getAll(nif))
    }
}