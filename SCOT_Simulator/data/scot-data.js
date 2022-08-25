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
                    estadoDoPagamento: "Paga",
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

/* Error builder */
function erro(msg, code) {
    return { message: msg, status: code }
}

function getVehicle(matricula) {
    let veiculo = data.find(car => car.matricula == matricula)
    return Promise.resolve(veiculo)
}

function getVehicles(){
    let veiculos = data.map(matricula => {
        return {matricula: matricula.matricula}
    })
    return Promise.resolve(veiculos)
}

function addVehicle(matricula) {
    let checkMatricula = getVehicle(matricula)
    if (checkMatricula == undefined) {
        let newMatricula = {
            matricula,
            eventos: []
        }
        data.push(newMatricula)
        Promise.resolve(newMatricula)
    } else {
        Promise.reject(erro('Car template is already inserted in SCOT', 409))
    }
}

function addEvent(evento) {
    let matricula = evento.dadosDoVeiculo.matricula
    getVehicle(matricula)
        .then(veiculo => {
            if (veiculo == undefined) {
                return Promise.reject(erro('Invalid input, car doesnt exist', 400))
            } else {
                veiculo.eventos.push(evento)
                return Promise.resolve(evento)
            }
        })
}

function getEvents() {
    return Promise.resolve(data)
}

export default {
    addVehicle,
    addEvent,
    getEvents,
    getVehicles
}