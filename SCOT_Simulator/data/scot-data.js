let eventosData = [
    {
        matricula: "XM-23-DA",
        eventos: [
            {
                dadosDoVeiculo: {
                    matricula: "XM-23-DA",
                    categoria: "AUTOMÓVEL LIGEIRO",
                    tipoDeInfracao: "PASSAGEIROS",
                    pais: "PORTUGAL"
                },
                dadosDaInfracao: {
                    numeroAuto: "753456776",
                    veiculoAutuado: "XM-23-DA",
                    dataHora: "2022-05-10 19:20",
                    local: "Rua da Praia, São Pedro da Afurada",
                    normaInfringida: "27.º n.º 1",
                    distrito: "Porto",
                    descricaoSumaria: "Cond. aut.lig. local., +de 20 Km/h até 40 Km/h",
                    dataLimiteDefesa: "2021-12-14",
                    estadoDoPagamento: "Por pagar",
                    valorDaCoima: 120,
                    gravidade: "Grave",
                    entidadeAutuante: "DESTACAMENTO DE TRÂNSITO DE PORTO",
                    dataNotificacao: "2022-05-10"
                }
            }
        ]
    },
    { matricula: "WN-23-DA", eventos: [] },
    { matricula: "10-AG-AG", eventos: [] },
    { matricula: "KL-38-FG", eventos: [] },
    { matricula: "03-42-LM", eventos: [] }
]//Array<matricula, Array<eventos>>

let delegacoesData = [
    {
        matricula : "XM-23-DA",
        delegacoes : [
            {
                nif : 271357375,
                start : "4/9/2022 @ 14:40:33",
                end : null
            }
        ] 
        
    }
]//Array<matricula, Array<delegacoes>>


function getCurrentTime() {
    const currentdate = new Date(); 
    return currentdate.getDate() + "/"
            + (currentdate.getMonth()+1)  + "/" 
            + currentdate.getFullYear() + " @ "  
            + currentdate.getHours() + ":"  
            + currentdate.getMinutes() + ":" 
            + currentdate.getSeconds();
}


/**
 * Recebe mensagem e estatus code do erro, para construir um objeto erro
 * @param {string} msg 
 * @param {number} code 
 * @returns erro
 */
function erro(msg, code) {
    return { message: msg, status: code }
}

async function getEvents() {
    return Promise.resolve(eventosData)
}

async function getDelegations() {
    return Promise.resolve(delegacoesData)
}

async function getVehicle(matricula) {
    let veiculo = eventosData.find(car => car.matricula == matricula)
    return Promise.resolve(veiculo)
}

async function getDelegationVehicle(matricula) {
    let veiculo = delegacoesData.find(car => car.matricula == matricula)
    return Promise.resolve(veiculo)
}

async function getVehicles(){
    let veiculos = eventosData.map(matricula => {
        return {matricula: matricula.matricula}
    })
    return Promise.resolve(veiculos)
}

/**
 * Recebe um objeto evento, e insere na memória do Simulador SCOT
 * retorna o evento inserido
 * @param {object} evento 
 */
async function addEvent(evento) {
    let matricula = evento.dadosDoVeiculo.matricula
        return getVehicle(matricula)
            .then(veiculo => {
                if (veiculo == undefined) {
                    return Promise.reject(erro('Input inválido, o veículo não existe.', 400))
                } else {
                    veiculo.eventos.push(evento)
                    return Promise.resolve(evento)
                }
            })
}

/**
 * Recebe o nif do subscritor ao qual a matrícula ficará delegada
 * @param {string} nif 
 * @param {string} matricula 
 */
async function makeDelegation(nif, matricula) {
    return getDelegationVehicle(matricula)
        .then((veiculo) => {
            //se o veículo nunca tiver sido delegado
            if (veiculo == undefined) {
                delegacoesData.push({
                    matricula,
                    delegacoes : [
                        {
                            nif,
                            start : getCurrentTime(),
                            end : null
                        }
                    ]
                })
                return Promise.resolve()
            }
            //procurar por delegacao pendente
            const deleg = veiculo.delegacoes.find(d => d.end == null)
            if (deleg == undefined) {
                veiculo.delegacoes.push({
                    nif, 
                    start : getCurrentTime(),
                    end : null
                })
                return Promise.resolve()
            } else {
                return Promise.reject(erro('Ainda existe uma delegação associada a este veículo.', 409))
            }
        })
}

/**
 * Remove delegação da memória do simulador SCOT
 * @param {string} nif 
 * @param {string} matricula 
 */
async function endDelegation(nif, matricula) {
    return getDelegationVehicle(matricula)
        .then((veiculo) => {
            if (veiculo == undefined) {
                return Promise.reject(erro('Input inválido, o veículo não existe.', 400))
            }
            //procurar por delegacao pendente
            const deleg = veiculo.delegacoes.find(d => d.end == null)
            if (deleg == undefined) {
                return Promise.reject(erro(`Não existe nenhuma delegação deste nif ${nif} para esta matrícula.`, 400))
            } else if (deleg.end == null) {
                deleg.end = getCurrentTime()
                return Promise.resolve()
            } else {
                return Promise.reject(erro(`Esta delegação já foi terminada.`, 405))
            }
        })
}

/**
 * Atualiza a informação de pagamento de uma contraodernação
 * Retorna o evento atualizado
 * @param {string} numeroAuto de uma contraordenação 
 */
async function payEvent(numeroAuto, matricula) { 
    return getVehicle(matricula)
            .then((veiculo) => veiculo.eventos.find(e => e.dadosDaInfracao.numeroAuto == numeroAuto))
            .then((evento) => {
                if (evento == undefined) {
                    return Promise.reject(erro('Input inválido, a contraordenação não existe no simulador SCOT.', 400))
                }
                if (evento.dadosDaInfracao.estadoDoPagamento == 'Por pagar') {
                    evento.dadosDaInfracao.estadoDoPagamento = 'Pago'
                    return Promise.resolve(evento)
                } else {
                    return Promise.reject(erro('A contraordenação com o estado de pagamento incorreto.', 400))
                }
            })
}


export default {
    addEvent,
    getEvents,
    getVehicles,
    payEvent,
    makeDelegation,
    endDelegation,
    getDelegations
}