//import logo from './logo.svg';
//import './App.css';
import { UserContainer } from "./Components/UserContext"

import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Login } from "./Pages/LoginPage"
import { Home } from "./Pages/HomePage"
import { Register } from "./Pages/RegisterPage"
import { LoginVerifier } from "./Components/LoginCheck"
import { VeiculosPage } from "./Pages/VeiculosPage"
import { VeiculoPage } from "./Pages/VeiculoPage"
import { NotificacoesPage } from "./Pages/NotificacoesPage"
import { ContraOrdenacaoPage } from "./Pages/ContraOrdenacaoPage"
import { DelegacoesPage } from "./Pages/DelegacoesPage"
import { Navbar } from "./Components/Navbar"
import { AdicionarVeiculo } from "./Pages/AdicionarVeiculoPage"
import { Delegar } from "./Pages/DelegarFormPage"

 const App = () => {
  return(
    <UserContainer>
      <BrowserRouter> 
        <Navbar/>
        <div style={{margin: "auto", width: "90%"}}>
          <Routes>
            <Route path="/login" element={<Login />}/>
            <Route path="/" element={<LoginVerifier><Home /></LoginVerifier>}/>
            <Route path="/register" element={<Register />}/>
            <Route path="/veiculos" element={<LoginVerifier><VeiculosPage/></LoginVerifier>}/>
            <Route path="/veiculo/:matricula" element={<LoginVerifier><VeiculoPage/></LoginVerifier>}/>
            <Route path="/veiculo/:matricula/contraordenacoes/:numeroAuto" element={<LoginVerifier><ContraOrdenacaoPage/></LoginVerifier>}/>
            <Route path="/notificacoes" element={<LoginVerifier><NotificacoesPage/></LoginVerifier>}/>
            <Route path="/delegacoes" element={<LoginVerifier><DelegacoesPage/></LoginVerifier>}/>
            <Route path="/adicionarveiculo" element={<LoginVerifier><AdicionarVeiculo/></LoginVerifier>}/>
            <Route path="/delegarform/:matricula" element={<LoginVerifier><Delegar/></LoginVerifier>}/>
          </Routes>
        </div>
      </BrowserRouter>
    </UserContainer>
  )
}
//rotas
//<Route path="PATH" element={<LoginVerifier><OurPage /></LoginVerifier>}/>
export default App;