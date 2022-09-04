import app from 'express'
const router = app.Router()
import scot_data from './data/scot-data.js'


router.post('/notificacoes', insertNotifications)

router.post('/pagamento', makePayment)

router.post('/delegacao', startDelegation)

router.post('/delegacao/:matricula/', completeDelegation)

function insertNotifications(req, res, next) {
    const evento = req.body.evento
    console.log(evento)
    if (evento == undefined) {
        res.status(400)
        res.send('Por favor insira a informacao do evento no body do pedido')
        next
    }
    scot_data
        .addEvent(evento)
        .then((evt) => {res.status(201).json(evt)})
        .catch(next)
}

function makePayment(req, res, next) {
    const numeroAuto = req.body.numeroAuto
    const matricula = req.body.matricula
    console.log(`\nPayment request at numero de auto = ${numeroAuto}`)
    if (numeroAuto == undefined) {
        res.status(400)
        res.send('Por favor insira o número auto')
        next
    }
    scot_data
        .payEvent(numeroAuto, matricula)
        .then((evt) => {res.status(200).json(evt);})
        .catch(next)
}

function startDelegation(req, res, next) {
    const nif = req.body.nif
    const matricula = req.body.matricula
    console.log(`\nDelegação da matricula = ${matricula} ao nif = ${nif}`)
    if (nif == undefined || matricula == undefined) {
        res.status(400)
        res.send(`Por favor insira a matricula = ${matricula} e o nif = ${nif} corretamente.`)
        next
    }
    scot_data
        .makeDelegation(nif, matricula)
        .then(() => {res.status(201).json('Delegação do veículo foi registada no simulador SCOT com sucesso.');})
        .catch(next)
}


function completeDelegation(req, res, next) {
    const nif = req.body.nif
    const matricula = req.params.matricula
    console.log(`\nEncerramento da delegação da matricula = ${matricula} ao nif = ${nif}`)
    if (nif == undefined || matricula == undefined) {
        res.status(400)
        res.send(`Por favor insira a matricula = ${matricula} e o nif = ${nif} corretamente.`)
        next
    }
    scot_data
        .endDelegation(nif, matricula)
        .then(() => {res.status(200).json('Delegação do veículo foi terminada no simulador SCOT com sucesso.');})
        .catch(next)
}

export default router