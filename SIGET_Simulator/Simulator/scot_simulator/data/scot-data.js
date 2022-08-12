//grupos e coisas guardadas no filesystem 
let data = [
    {   
        matricula : "XM-23-DA",
        eventos : [
            {
                dadosDoVeiculo: {
                    matricula : "XM-23-DA",
                    categoria : "AUTOMÓVEL LIGEIRO",
                    tipoDeInfracao : "PASSAGEIROS",
                    pais : "PORTUGAL"
                },
                dadosDaInfracao : {
                    numeroAuto : "753456776",
                    veiculoAutuado : "XM-23-DA",
                    dataHora : "2022-05-10 19:20",
                    local : "Rua da Praia, São Pedro da Afurada",
                    normaInfrigida : "27.º n.º 1",
                    distrito : "Porto",
                    descricaoSumaria : "Cond. aut.lig. local., +de 20 Km/h até 40 Km/h",
                    dataLimiteDefesa : "2021-12-14",
                    estadoDoPagamento : "Paga",
                    valorDaCoima : 120,
                    gravidade : "Grave",
                    entidadeAutuante : "DESTACAMENTO DE TRÂNSITO DE PORTO",
                    dataNotificacao : "2022-05-10"
                }
            }
        ]
    }
]//Array<Matriculas>

function addCarPlate(matricula){
    let matriculaNova = {
        matricula,
        eventos : []
    }
    let exist = data.forEach(matricula_eventos => {
        const check = matricula_eventos.find(m => m.matricula === matricula)
    });
    if(exist == undefined) {
        data.push(matriculaNova)
    }
} 

function addEvent(evento){
    let matricula = evento.dadosDoVeiculo.matricula
    data.forEach(matricula_eventos => {
        const veiculo = matricula_eventos.find(m => m.matricula === matricula)
        if(veiculo != undefined) {
            veiculo.eventos.push(evento)
            console.log(`Evento adicionado ao veiculo : ${veiculo}`)
        }});

}

function getAllInformation(){
    return data
}

module.exports = {
    addCarPlate,
    addEvent,
    getAllInformation
}