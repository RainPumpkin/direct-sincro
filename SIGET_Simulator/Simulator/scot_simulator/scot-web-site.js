'use strict'

const router = require('express').Router()
const veiculos = require('./data/scot-data')

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

module.exports = router