import app from 'express'
const router = app.Router()
//import vehicles from '../Data/veículosSubscritos.json' assert {type: 'json'}

router.post('/veiculo', insertVehicle)


let matriculas = [
    {"matricula" : "23-GF-87"},
    {"matricula": "JH-08-78"}
]
/**
 * TODO: LOOK IN THE MEMORY ASYNCHRONOUS
 */
function insertVehicle(req, res, next) {
    try {
        console.log(`Matricula retrieved from request : ${req.body}`)
        const matricula = req.body.matricula
        if (matricula == null) {
            res.status(400)
            res.send('Por favor insira a matrícula no body do pedido')
        }
        const veiculo = matriculas.find(m => m == matricula)
        if (veiculo != null) {
            res.status(409)
            res.send('A matrícula já existe no simulador siget')
        }
        veiculo = {
            "matricula" : matricula
        }
        matriculas.push(veiculo)
        res.status(201)
        res.json(veiculo)
    } catch (e) {
        res.status(500)
        res.send(e.message);
    }
}

export default router
export {matriculas}
