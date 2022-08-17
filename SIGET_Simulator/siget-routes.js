import app from 'express'
const router = app.Router()
import matriculas from './Data/matriculas.js'
//in case file memory is the most appropriate way
//import vehicles from '../Data/veículosSubscritos.json' assert {type: 'json'}

//TODO: make routes async

router.post('/veiculo', insertVehicle)
router.delete('/veiculo', deleteVehicle)

function insertVehicle(req, res, next) {
    try {
        console.log(`\nMatricula received from Direct-Sincro : ${JSON.stringify(req.body)}`)
        const matricula = req.body.matricula
        if (matricula == null) {
            res.status(400)
            res.send('Por favor insira a matrícula no body do pedido')
            next
        }
        const veiculo = matriculas.find(m => m.matricula === matricula)
        if (veiculo != null) {
            res.status(409)
            res.send('A matrícula já existe no simulador siget')
            next
        }
        const insertVeiculo = {
            "matricula" : matricula
        }
        matriculas.push(insertVeiculo)
        console.log(`\nTodas as matrículas no SIGET: ${JSON.stringify(matriculas)}`)
        res.status(201)
        res.json(veiculo)
        next
    } catch (e) {
        res.status(500)
        res.send(e.message);
        next
    }
}

function deleteVehicle(req, res, next) {
    try {
        console.log(`\nMatricula received from Direct-Sincro : ${JSON.stringify(req.body)}`)
        const matricula = req.body.matricula
        if (matricula == null) {
            res.status(400)
            res.send('Por favor insira a matrícula no body do pedido')
            next
        }
        const size = matriculas.length
        matriculas = matriculas.filter(n => {
            return n.matricula !== matricula
        })
        if (matriculas.length === size) {
            res.status(404)
            res.send('O recurso pretendido não se encontra na aplicação')
            next
        }
        console.log(`\nTodas as matrículas no SIGET: ${JSON.stringify(matriculas)}`)
        res.status(204)
        res.send("O pedido teve sucesso, o conteudo foi removido")
        next
    } catch (e) {
        res.status(500)
        res.send(e.message);
        next
    }
}

export default router
export {matriculas}
