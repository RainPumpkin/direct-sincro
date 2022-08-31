//import logo from './logo.svg';
//import './App.css';
import { UserContainer } from "./Components/UserContext"

import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Login } from "./Pages/LoginPage"
import { Home } from "./Pages/HomePage"
import { LoginVerifier } from "./Components/LoginCheck"
import { VeiculosPage } from "./Pages/VeiculosPage"
import { VeiculoPage } from "./Pages/VeiculoPage"

 const App = () => {
  return(
    <UserContainer>
      <BrowserRouter> 
        <div style={{margin: "auto", width: "90%"}}>
          <Routes>
            <Route path="/login" element={<Login />}/>
            <Route path="/" element={<LoginVerifier><Home /></LoginVerifier>}/>
            <Route path="/veiculos" element={<LoginVerifier><VeiculosPage/></LoginVerifier>}/>
            <Route path="/veiculo/:matricula" element={<LoginVerifier><VeiculoPage/></LoginVerifier>}/>
          </Routes>
        </div>
      </BrowserRouter>
    </UserContainer>
  )
}
//rotas
//<Route path="PATH" element={<LoginVerifier><OurPage /></LoginVerifier>}/>
export default App;