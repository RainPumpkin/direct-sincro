'use strict'

import app from 'express'
const router = app.Router()
import veiculos from './data/scot-data.js'

router.get('/', getHome)
router.get('/about', getAbout)
router.get('/events', getEvents)

function getEvents(req, res, next) { 
    let allEvents = veiculos.getAllInformation()
    return res.render('contraordenacoes', {'eventos' : allEvents})
}

function getHome(req, res, next) {
    res.render('home')
}

function getAbout(req, res, next) {
    res.render('about')
}

export default router