'use strict'

import app from 'express'
const router = app.Router()
import data from './data/scot-data.js'

router.get('/events', getEvents)

router.get('/home', getHome)

router.get('/vehicles', getVehicles)

function getEvents(req, res, next) { 
    data
        .getEvents()
        .then(events => res.render('contraordenacoes', {'events' : events}))
        .catch(next)
}

function getHome(req, res, next) {
    res.render('home')
}

function getVehicles(req, res, next) {
    data
        .getVehicles()
        .then(vehicles => res.render('veiculos', {'vehicles' : vehicles}))
        .catch(next)
}

export default router