//grupos e coisas guardadas no filesystem 
let data = [
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
                    normaInfrigida: "27.º n.º 1",
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
]//Array<Matriculas>

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
    return Promise.resolve(data)
}

async function getVehicle(matricula) {
    let veiculo = data.find(car => car.matricula == matricula)
    return Promise.resolve(veiculo)
}

async function getVehicles(){
    let veiculos = data.map(matricula => {
        return {matricula: matricula.matricula}
    })
    return Promise.resolve(veiculos)
}

async function addVehicle(matricula) {
    let checkMatricula = getVehicle(matricula)
    if (checkMatricula == undefined) {
        let newMatricula = {
            matricula,
            eventos: []
        }
        data.push(newMatricula)
        Promise.resolve(newMatricula)
    } else {
        Promise.reject(erro('O Veiculo já está inserido no simulador SCOT.', 409))
    }
}

/**
 * Recebe um objeto evento, e insere na memória do Simulador SCOT
 * returns o evento inserido
 * @param {object} evento 
 */
async function addEvent(evento) {
    let matricula = evento.dadosDoVeiculo.matricula
        return getVehicle(matricula)
            .then(veiculo => {
                if (veiculo == undefined) {
                    return erro('Input inválido, o veículo não existe.', 400)
                } else {
                    veiculo.eventos.push(evento)
                    return Promise.resolve(evento)
                }
            })
}

/**
 * Atualiza a informação de pagamento de uma contraodernação
 * Returns o evento atualizado
 * @param {string} numeroAuto de uma contraordenação 
 */

/*
async function payEvent(numeroAuto, matricula) { 
    let veiculo = await getVehicle(matricula)
    return veiculo.eventos.find(e => e.dadosDaInfracao.numeroAuto === numeroAuto)
        .then((evento) => {
            console.log(`asdfsadasdasdasdasdas ${JSON.stringify(evento)}`)
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
*/

async function payEvent(numeroAuto, matricula) { 
    return getVehicle(matricula)
            .then((veiculo) => veiculo.eventos.find(e => e.dadosDaInfracao.numeroAuto == numeroAuto))
            .then((evento) => {
                console.log(`asdfsadasdasdasdasdas ${JSON.stringify(evento)}`)
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
    addVehicle,
    addEvent,
    getEvents,
    getVehicles,
    payEvent
}