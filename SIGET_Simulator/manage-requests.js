
import matriculas from './Data/matriculas.js'
import fetch from 'node-fetch'


/**
 * Roots need to be refactored
 */
 const SCOT_URL = 'http://localhost:4000/scot/notificacoes'
 const DIRECT_SINCRO_URL = 'http://localhost:8080/api/eventos'

/**
 * TODO: requests should retry until servers are up again
 * All events are send to SCOT simulator and only the events which
 * have matricula registered in Direct-Sincro are send to Direct-Sincro
 * @param {array} data 
 */
 async function sendEvents(data) {
    let directSincroSubscriptions = []
    console.log(data)
    data.forEach(element => {
      const matricula = element.evento.dadosDoVeiculo.matricula
      const check = matriculas.find(m => m.matricula === matricula)
      if (check != undefined) {
        directSincroSubscriptions.push(element)
      }
    });
    /*
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
    */
    const SCOT_Requests = await Promise.all(
      data.map(async evento => {
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

  export default sendEvents