
import matriculas from './Data/matriculas.js'
import fetch from 'node-fetch'


const SCOT_URL = 'http://localhost:4000/scot/notificacoes'
const DIRECT_SINCRO_URL = 'http://localhost:8080/api/contraordenacoes'

/**
 * Recebe todos os eventos de trânsito e procura os veículos subscritos em Direct-Sincro 
 * para depois inserir num array dedicado para pedidos ao Direct-Sincro
 * @param {array} data 
 */
async function filterRequests(data) {
  let directSincroSubscriptions = []
  data.forEach(obj => {
    const matricula = obj.evento.dadosDoVeiculo.matricula
    const check = matriculas.find(m => m.matricula === matricula)
    if (check != undefined) {
      obj.subscritoNoDirectSincro = "sim"
      directSincroSubscriptions.push(obj)
    } else {
      obj.subscritoNoDirectSincro = "não"
    }
  });
  return directSincroSubscriptions
}

/**
 * Recebe os array com os eventos de trânsito e processa os pedidos para o sistema pretendido
 * @param {array} events 
 * @param {string} url description
 */
async function makeRequests(events, url) {
  const responses = await Promise.all(
    events.map(async evento => {
      console.log(`\nRequests = ${url} \n ${JSON.stringify(evento)}`)
      return await fetch(url, {
        method: 'post',
        body: JSON.stringify(evento),
        headers: {'Content-Type': 'application/json'}
        })
        .then(res => res.json())
        .catch(error => {
            console.error('There was an error!', error);
        });
    })
  );
  console.info(`\nResponse from ${url}:\n ${JSON.stringify(responses, null, "  ")}`)
}

/**
 * Controlador 
 * @param {array} data de contraordenações simuladas 
 */
async function sendEvents(data) {
  const directSincroRequests = await filterRequests(data)
  makeRequests(directSincroRequests, DIRECT_SINCRO_URL)
  makeRequests(data, SCOT_URL)
}

export default sendEvents