import app from 'express'
const router = app.Router()
import fs from 'fs'
import { promisify } from "util";
const readFile = promisify(fs.readFile);
import matriculas from './Data/matriculas.js'

//read IMT file data
const IMT_data = JSON.parse(
    await function () {
        return readFile('./Data/IMT.json', 'utf-8');    
    }()
); 

router.post('/veiculo', insertVehicle)

router.delete('/veiculo', deleteVehicle)

function insertVehicle(req, res, next) {
    try {
        console.log(`\nMatricula received from Direct-Sincro : ${JSON.stringify(req.body)}`)
        const matricula = req.body.matricula
        const nif = req.body.nif
        if (matricula == undefined || nif == undefined) {
            res.status(400)
            res.send('Por favor insira a matrícula e nif no body do pedido')
            next
        }
        //check if matricula is already in the system
        const veiculo = matriculas.find(m => m.matricula === matricula)
        if (veiculo != undefined) {
            res.status(409)
            res.send('A matrícula já existe no simulador siget')
            next
        }
        //simulate request to IMT to confirm citizen vehicles
        console.log(`\nMatrículas from IMT simulation: \n ${JSON.stringify(IMT_data)}\n`)
        const citizen = IMT_data.find(citizen => citizen.nif === nif)
        if (citizen == undefined) {
            res.status(400)
            res.send('Por favor insira um nif válido')
            next
        }
        const checkVehicleAuthorization = citizen.matriculas.find(mat => mat === matricula)
        if (checkVehicleAuthorization == undefined) {
            res.status(400)
            res.send('Por favor insira apenas veículos associados ao seu NIF')
            next
        }
        const insertVehicle = {
            "matricula" : matricula
        }
        matriculas.push(insertVehicle)
        console.log(`\nTodas as matrículas no SIGET: ${JSON.stringify(matriculas)}`)
        res.status(201)
        res.json(veiculo)
        next
    } catch (e) {
        res.status(500)
        res.send(e.message);
        next
    }
}

function deleteVehicle(req, res, next) {
    try {
        console.log(`\nMatricula received from Direct-Sincro : ${JSON.stringify(req.body)}`)
        const matricula = req.body.matricula
        if (matricula == undefined) {
            res.status(400)
            res.send('Por favor insira a matrícula no body do pedido')
            next
        }
        const size = matriculas.length
        matriculas = matriculas.filter(n => {
            return n.matricula !== matricula
        })
        if (matriculas.length === size) {
            res.status(404)
            res.send('O recurso pretendido não se encontra na aplicação')
            next
        }
        console.log(`\nTodas as matrículas no SIGET: ${JSON.stringify(matriculas)}`)
        res.status(204)
        res.send("O pedido teve sucesso, o conteudo foi removido")
        next
    } catch (e) {
        res.status(500)
        res.send(e.message);
        next
    }
}

export default router
export {matriculas}
