import app from 'express'
const router = app.Router()
const scot_data = require('./data/scot-data')

router.post('/notificacoes', insertNotifications)


function insertNotifications(req, res, next) {
    try {
        const evento = req.body.evento
        if (evento == null) {
            res.status(400)
            res.send('Por favor insira a informacao do evento no body do pedido')
            next
        }
        scot_data.addEvent(evento)
        res.status(201)
        res.json(evento)
        next
    } catch (e) {
        res.status(500)
        res.send(e.message);
        next
    }
}

export default router