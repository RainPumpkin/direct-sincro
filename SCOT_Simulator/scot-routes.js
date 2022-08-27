import app from 'express'
const router = app.Router()
import scot_data from './data/scot-data.js'


router.post('/notificacoes', insertNotifications)

router.put('/pagamento', makePayment)

function insertNotifications(req, res, next) {
    const evento = req.body.evento
    console.log(evento)
    if (evento == null) {
        res.status(400)
        res.send('Por favor insira a informacao do evento no body do pedido')
        next
    }
    scot_data
        .addEvent(evento)
        .then((evt) => {res.status(201); res.json(evt)})
        .catch(next)
}

function makePayment(req, res, next) {
    const numeroAuto = req.body.numeroAuto
    if (numeroAuto == null) {
        res.status(400)
        res.send('Por favor insira o número auto')
        next
    }
    scot_data
        .payEvent(numeroAuto)
        .then((evt) => {res.status(200); res.json(evt);})
        .catch(next)
}

export default router