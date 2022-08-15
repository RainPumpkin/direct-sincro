import app from 'express'
const router = app.Router()
import scot_data from './data/scot-data.js'


router.post('/notificacoes', insertNotifications)

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
        .then(() => res.status(201), res.json(evento))
        .catch(next)
}

export default router