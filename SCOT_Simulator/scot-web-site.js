'use strict'

import app from 'express'
const router = app.Router()
import veiculos from './data/scot-data.js'

router.get('/', getHome)

router.get('/about', getAbout)

router.get('/events', getEvents)

function getEvents(req, res, next) { 
    veiculos
        .getAllCarsEvents()
        .then(events => res.render('contraordenacoes', {'events' : events}))
        .catch(next)
}

function getHome(req, res, next) {
    res.render('home')
}

function getAbout(req, res, next) {
    res.render('about')
}

export default router