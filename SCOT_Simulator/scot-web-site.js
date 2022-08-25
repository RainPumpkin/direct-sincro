'use strict'

import app from 'express'
const router = app.Router()
import veiculos from './data/scot-data.js'

router.get('/events', getEvents)

router.get('/home', getHome)

function getEvents(req, res, next) { 
    veiculos
        .getAllCarsEvents()
        .then(events => res.render('contraordenacoes', {'events' : events}))
        .catch(next)
}

function getHome(req, res, next) {
    res.render('home')
}

export default router