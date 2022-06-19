import fetch from 'node-fetch'
import { readFile } from "fs"
import app from "./siget-server.js"
import { matriculas } from './siget-routes.js'


/**
 * Roots need to be refactored
 */
//const SCOT = 'http://localhost:4000/scot/notificacoes'
//const DIRECT_SINCRO = 'http://localhost:8080/api/notificacoes'
const SCOT_URL = 'http://scot.requestcatcher.com/'
const DIRECT_SINCRO_URL = 'http://direct_sincro.requestcatcher.com/'


function importEvents() {
  readFile('../Data/eventos.json','utf-8',function(err, jsonData){
    if (err) throw err;  
    const data = JSON.parse(jsonData)
    prepareRequests(data)
  }); 
}

/**
 * TODO: requests should retry until servers are up again
 * @param {array} data 
 */
async function prepareRequests(data) {
  let directSincroSubscriptions = []
  let SCOT = []
  console.log(data)
  data.forEach(element => {
    const matricula = element.evento.dadosDoVeiculo.matricula
    const check = matriculas.find(m => m.matricula === matricula)
    if (check != undefined) {
      directSincroSubscriptions.push(element)
    } else {
      SCOT.push(element)
    }
  });

  const DirectSincro_Requests = await Promise.all(
    directSincroSubscriptions.map(async evento => {
      console.log(`DirectSincro_Requests = ${JSON.stringify(evento)}`)
      await fetch(DIRECT_SINCRO_URL, {
        method: 'post',
        body: JSON.stringify(evento),
        headers: {'Content-Type': 'application/json'}
      });
    })
  );
  console.log(`DirectSincro_responses -> ${DirectSincro_Requests}\n`)

  const SCOT_Requests = await Promise.all(
    SCOT.map(async evento => {
      console.log(`SCOT_Requests = ${JSON.stringify(evento)}`)
      await fetch(SCOT_URL, {
        method: 'post',
        body: JSON.stringify(evento),
        headers: {'Content-Type': 'application/json'}
      });
    })
  );
  console.log(`SCOT_responses -> ${SCOT_Requests}\n`)
}

importEvents()